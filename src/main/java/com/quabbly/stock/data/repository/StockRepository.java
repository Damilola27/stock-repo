package com.quabbly.stock.data.repository;

import com.quabbly.stock.data.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository <Stock, Long> {

}
