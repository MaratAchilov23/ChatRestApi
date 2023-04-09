package com.example.chatrestapi.repository;

import com.example.chatrestapi.models.Order;
import com.example.chatrestapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface OrderRepository extends JpaRepository<Order,Long> {
   Optional<Order> findByBuyer(String buyerName);

    Optional<Order> findByTotalOrderAmount(Double totalAmount);

    List<Order> findByTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Order>findByPaidFor(boolean paidFor);
 //   List<Order> findOrdersByProductTitle(String productTitle);

    List<Order>findByProducts(Product product);

    List<Order>findAll();

}
