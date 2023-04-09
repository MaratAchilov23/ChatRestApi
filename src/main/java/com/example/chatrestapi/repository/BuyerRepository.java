package com.example.chatrestapi.repository;

import com.example.chatrestapi.models.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer,Long> {
    Optional<Buyer> findByName(String name);
    @Query(value = "SELECT * FROM Buyer WHERE balance < 10", nativeQuery = true)
    List<Buyer> findAllByBalanceLessThanTen();



}
