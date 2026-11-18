package com.example.FakeCommerce.services;

import java.util.List;

import com.example.FakeCommerce.DTO.CreateProductDto;
import com.example.FakeCommerce.DTO.GetProductResponseDto;
import com.example.FakeCommerce.DTO.GetProductWithDetailsResponseDto;
import com.example.FakeCommerce.exception.ResourceNotFoundException;
import com.example.FakeCommerce.schema.Category;
import org.springframework.stereotype.Service;

import com.example.FakeCommerce.repositories.ProductRepository;
import com.example.FakeCommerce.schema.Product;

import lombok.RequiredArgsConstructor;

import javax.lang.model.element.NestingKind;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public List<GetProductResponseDto> getAllProducts() {
        List<Product> products  = productRepository.findAll();

        return products.stream().map(product -> GetProductResponseDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .image(product.getImage())
                .rating(product.getRating())
                .build()).collect(toList());
    }

    public GetProductResponseDto getProductById(Long id) {
        return productRepository.findById(id).map(product -> GetProductResponseDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .image(product.getImage())
                .rating(product.getRating())
                .build())
            .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public GetProductWithDetailsResponseDto getProductWithDetailsById(Long id){
        Product p = productRepository.findProductWithDetailsById(id);
        return GetProductWithDetailsResponseDto.builder()
                .id(p.getId())
                .title(p.getTitle())
                .description(p.getDescription())
                .price(p.getPrice())
                .image(p.getImage())
                .rating(p.getRating())
                .category(p.getCategory().getName())
                .build();
    }

    public Product createProduct(CreateProductDto product) {
        Category category = categoryService.getCategoryById(product.getCategoryId());

        Product p = Product.builder()
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .image(product.getImage())
                .category(category)
                .rating(product.getRating())
                .build();
        return productRepository.save(p);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<String> getAllCategories() {
        return productRepository.findAllCategories();
    }

    public List<GetProductWithDetailsResponseDto> getProductWithDetailsResponseDtoList(){
        List<Product> products = productRepository.findAllProductsWithDetails();
        return products.stream().map(product -> GetProductWithDetailsResponseDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .image(product.getImage())
                .rating(product.getRating())
                .category(product.getCategory().getName())
                .build()).collect(toList());
    }


}
