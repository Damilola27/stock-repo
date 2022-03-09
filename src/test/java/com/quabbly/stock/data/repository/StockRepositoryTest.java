package com.quabbly.stock.data.repository;

import com.quabbly.stock.data.model.Stock;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
@Slf4j
@Sql(scripts = "/db/insert.sql")
class StockRepositoryTest {
    @Autowired
    StockRepository stockRepository;
    Stock stock = new Stock();
    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Save stock to database")
    void saveStockToDataBaseTest(){
        stock.setId(1L);
        stock.setName("Plar");
        stock.setPrice(200000);
        stock.setQuantity(20L);
        stockRepository.save(stock);

        assertThat(stockRepository.count()).isEqualTo(4);

        assertThat(stock.getId()).isNotNull();
        assertThat(stock.getName()).isEqualTo("Plar");
        assertThat(stock.getPrice()).isEqualTo(200000);
        assertThat(stock.getDateCreated()).isNotNull();
        log.info("Stock created -> {}", stock);
    }

    @Test
    @DisplayName("Find stock by id in the database")
    void findStockByIdTest(){
        Stock foundStock = stockRepository.findById(20L).orElse(null);

        assertNotNull(foundStock);
        assertThat(foundStock.getId()).isNotNull();
        assertThat(foundStock.getName()).isEqualTo("LSA");
        log.info("found stock is ->{}", foundStock);
    }

    @Test
    @DisplayName("Find All stocks in database")
    void findAllStocksInDatabaseTest(){
        List<Stock> stockList = stockRepository.findAll();

        assertThat(stockList.size()).isEqualTo(3);
        assertThat(stockList.get(0).getName()).isEqualTo("LSA");

        log.info("List of stocks in database ->{}",stockList);

    }

    @Test
    @DisplayName("Update stocks in database")
    void  updateStocksInDatabaseTest(){
        Stock savedStock = stockRepository.findById(21L).orElse(null);
        assertNotNull(savedStock);
        assertThat(savedStock.getName()).isEqualTo("WSA");

        Stock updatedStock = stockRepository.findById(21L).orElse(null);
        assertNotNull(updatedStock);
        updatedStock.setName("SSA");
        stockRepository.save(updatedStock);

        assertThat(updatedStock.getName()).isEqualTo("SSA");
        log.info("Updated Stock is ->{} ", updatedStock);

    }

    @Test
    @DisplayName("Delete stock in Database")
    void DeleteStockInDataBaseTest(){
        List<Stock> stockList = stockRepository.findAll();
        assertNotNull(stockList);
        stockRepository.deleteById(22L);
        assertThat(stockRepository.count()).isEqualTo(2);
        stockList = stockRepository.findAll();
        log.info("stocks left -> {} ", stockList);
    }

}