package com.example.FakeCommerce.services;

import com.example.FakeCommerce.DTO.CreateOrderDto;
import com.example.FakeCommerce.DTO.ListOfCreateOrderDto;
import com.example.FakeCommerce.DTO.OrderResponseDto;
import com.example.FakeCommerce.exception.ResourceNotFoundException;
import com.example.FakeCommerce.repositories.OrderProductRepository;
import com.example.FakeCommerce.repositories.OrderRepository;
import com.example.FakeCommerce.repositories.ProductRepository;
import com.example.FakeCommerce.schema.Order;
import com.example.FakeCommerce.schema.OrderProduct;
import com.example.FakeCommerce.schema.OrderStatus;
import com.example.FakeCommerce.schema.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;



    public OrderResponseDto createOrder(ListOfCreateOrderDto orders){
        Order savedOrder = orderRepository.save(Order.builder().status(OrderStatus.PENDING).build());

        List<OrderProduct> orderProducts = new ArrayList<>();
        List<Long> productIds = orders.getOrderList().stream().map(CreateOrderDto::getProductId).collect(Collectors.toList());
        List<Product> products = productRepository.findAllById(productIds);
        Map<Long, Product> productMap = products.stream().collect(Collectors.toMap(Product::getId, product -> product));

        for (Long id : productIds) {
            if (!productMap.containsKey(id)) {
                throw new ResourceNotFoundException("Product with ID " + id + " not found");
            }
        }

        for(CreateOrderDto order : orders.getOrderList()){
            OrderProduct orderProduct = OrderProduct.builder()
                    .order(savedOrder)
                    .product(productMap.get(order.getProductId()))
                    .quantity(order.getQuantity())
                    .build();
            orderProducts.add(orderProduct);
        }

        List<OrderProduct> savedOrderProducts = orderProductRepository.saveAll(orderProducts);

        OrderResponseDto response = OrderResponseDto.builder()
                .id(savedOrder.getId())
                .status(savedOrder.getStatus())
                .products(savedOrderProducts.stream().collect(Collectors.toMap(
                        op -> op.getProduct().getTitle(),
                        OrderProduct::getQuantity
                )))
                .build();

        return response;




    }
}
