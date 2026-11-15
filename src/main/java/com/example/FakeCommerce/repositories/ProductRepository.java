package com.example.FakeCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.FakeCommerce.schema.Product;
import tools.jackson.databind.node.LongNode;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);

    @Query(nativeQuery = true , value = "SELECT DISTINCT category FROM products")
    List<String> findAllCategories();


    @Query("select p from Product p join fetch p.category where p.id = :id")
    Product findProductWithDetailsById(Long id);

    @Query("select p from Product p join fetch p.category")
    List<Product> findAllProductsWithDetails();
}
