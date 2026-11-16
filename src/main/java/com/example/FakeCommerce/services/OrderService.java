package com.example.FakeCommerce.services;

import com.example.FakeCommerce.DTO.CreateOrderDto;
import com.example.FakeCommerce.DTO.OrderResponseDto;
import com.example.FakeCommerce.repositories.OrderProductRepository;
import com.example.FakeCommerce.repositories.OrderRepository;
import com.example.FakeCommerce.repositories.ProductRepository;
import com.example.FakeCommerce.schema.Order;
import com.example.FakeCommerce.schema.OrderProduct;
import com.example.FakeCommerce.schema.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;



    public OrderResponseDto createOrder(List<CreateOrderDto> orders){
        Order savedOrder = orderRepository.save(Order.builder().status(OrderStatus.PENDING).build());

        List<OrderProduct> orderProducts = new ArrayList<>();

        for(CreateOrderDto order : orders){
            OrderProduct orderProduct = OrderProduct.builder()
                    .order(savedOrder)
                    .product(productRepository.findById(order.getProductId()).orElse(null))
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
