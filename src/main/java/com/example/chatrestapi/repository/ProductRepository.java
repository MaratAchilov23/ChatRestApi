package com.example.chatrestapi.repository;

import com.example.chatrestapi.models.Order;
import com.example.chatrestapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product>findByTitle(String title);

    Optional<Product>findByPrice(double price);

    Optional<List<Product>>findByPriceBetween(double firstPrice, double secondPrice);

    Product findByDescription(String description);




}
