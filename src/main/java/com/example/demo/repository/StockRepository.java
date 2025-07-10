package com.example.demo.repository;

import com.example.demo.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    @Query(value = "SELECT * FROM stocks WHERE id = :id", nativeQuery = true)
    Optional<Stock> findByIdNative(@Param("id") Long id);
}
