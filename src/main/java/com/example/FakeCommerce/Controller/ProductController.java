package com.example.FakeCommerce.Controller;


import com.example.FakeCommerce.DTO.CreateProductDto;
import com.example.FakeCommerce.DTO.GetProductResponseDto;
import com.example.FakeCommerce.DTO.GetProductWithDetailsResponseDto;
import com.example.FakeCommerce.schema.Product;
import com.example.FakeCommerce.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<GetProductResponseDto> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    public GetProductResponseDto getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody CreateProductDto product){
        return productService.createProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
    @GetMapping("/search")
    public List<Product> getByCategory(@RequestParam("categoryName") String category){

        return productService.getByCategory(category);
    }

    @GetMapping("/{id}/details")
    public GetProductWithDetailsResponseDto getProductWithDetailsById(@PathVariable Long id){
        return productService.getProductWithDetailsById(id);
    }

    @GetMapping("/details")
    public List<GetProductWithDetailsResponseDto> getAllProductsWithDetails() {
        return productService.getProductWithDetailsResponseDtoList();

    }
}
