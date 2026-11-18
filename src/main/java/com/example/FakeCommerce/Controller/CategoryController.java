package com.example.FakeCommerce.Controller;

import com.example.FakeCommerce.DTO.CreateCategoryRequestDto;
import com.example.FakeCommerce.repositories.CategoryRepository;
import com.example.FakeCommerce.schema.Category;
import com.example.FakeCommerce.services.CategoryService;
import com.example.FakeCommerce.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<Category>> createCategory(@RequestBody CreateCategoryRequestDto category){

        return ResponseEntity.ok(ApiResponse.success(categoryService.createCategory(category),"Category created successfully"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Category>>> getAllCategories(){
        return ResponseEntity.ok(ApiResponse.success(categoryService.getAllCategories(),"Categories retrieved successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(ApiResponse.success("Category deleted successfully","Category deleted successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.success(categoryService.getCategoryById(id),"Category retrieved successfully"));
    }



}
