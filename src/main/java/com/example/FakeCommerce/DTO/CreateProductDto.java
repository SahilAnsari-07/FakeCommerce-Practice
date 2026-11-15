package com.example.FakeCommerce.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor

public class CreateProductDto {
    private String title;
    private String description;
    private BigDecimal price;
    private String image;
    private Long categoryId;
    private String rating;
}
