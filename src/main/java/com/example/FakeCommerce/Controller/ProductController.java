package com.example.FakeCommerce.Controller;


import com.example.FakeCommerce.DTO.CreateProductDto;
import com.example.FakeCommerce.DTO.GetProductResponseDto;
import com.example.FakeCommerce.DTO.GetProductWithDetailsResponseDto;
import com.example.FakeCommerce.schema.Product;
import com.example.FakeCommerce.services.ProductService;
import com.example.FakeCommerce.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<GetProductResponseDto>>> getAllProducts(){
        return ResponseEntity.ok(ApiResponse.success(productService.getAllProducts(),"Products retrieved successfully"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GetProductResponseDto>> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.success(productService.getProductById(id),"Product retrieved successfully"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> createProduct(@RequestBody CreateProductDto product){
        return ResponseEntity.ok(ApiResponse.success(productService.createProduct(product),"Product created successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok(ApiResponse.success("Product deleted successfully","Product deleted successfully"));
    }
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Product>>> getByCategory(@RequestParam("categoryName") String category){

        return ResponseEntity.ok(ApiResponse.success(productService.getByCategory(category),"Products retrieved successfully"));
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<ApiResponse<GetProductWithDetailsResponseDto>> getProductWithDetailsById(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.success(productService.getProductWithDetailsById(id),"Product details retrieved successfully"));
    }

    @GetMapping("/details")
    public ResponseEntity<ApiResponse<List<GetProductWithDetailsResponseDto>>> getAllProductsWithDetails() {
        return ResponseEntity.ok(ApiResponse.success(productService.getProductWithDetailsResponseDtoList(),"Product details retrieved successfully"));

    }
}
