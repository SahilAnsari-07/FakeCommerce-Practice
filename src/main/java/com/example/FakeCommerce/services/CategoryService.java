package com.example.FakeCommerce.services;


import com.example.FakeCommerce.DTO.CreateCategoryRequestDto;
import com.example.FakeCommerce.exception.ResourceNotFoundException;
import com.example.FakeCommerce.repositories.CategoryRepository;
import com.example.FakeCommerce.schema.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CategoryService {
    private final CategoryRepository categoryRepository;


    public Category createCategory(CreateCategoryRequestDto category){
        Category newCategory = Category.builder()
                .name(category.getName())
                .build();
        return categoryRepository.save(newCategory);
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
    public Category getCategoryById(Long id){
        return categoryRepository.findById((long) id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
