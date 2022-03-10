package com.quabbly.stock.data.repository;

import com.quabbly.stock.data.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository <Stock, Long> {

}
