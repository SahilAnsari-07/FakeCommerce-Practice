package com.example.FakeCommerce.DTO;

import com.example.FakeCommerce.schema.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {
    private Long id;
    private OrderStatus status;
    private Map<String, Integer> products; // Map of product title to quantity
}
