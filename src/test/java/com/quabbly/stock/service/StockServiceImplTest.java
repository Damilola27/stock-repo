package com.quabbly.stock.service;

import com.quabbly.stock.data.model.Stock;
import com.quabbly.stock.data.repository.StockRepository;
import com.quabbly.stock.dto.request.StockCreateRequest;
import com.quabbly.stock.dto.request.StockUpdateRequest;
import com.quabbly.stock.exceptions.StockException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Slf4j
class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;
    private ModelMapper modelMapper;
    private StockService stockService;


    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        stockService = new StockServiceImpl(stockRepository, modelMapper);

    }

    @Test
    void createStock() {
        StockCreateRequest request = new StockCreateRequest("JWA", 10000, 50L);
        Stock stock = new Stock(30L, "JWA", 10000, 50L, LocalDateTime.now(), null);
        when(stockRepository.save(any(Stock.class))).thenReturn(stock);
        stockService.createStock(request);
        verify(stockRepository, times(1)).save(any(Stock.class));
        assertThat(stock.getName()).isEqualTo("JWA");
        log.info("stock created ->{} ", stock);

    }

    @Test
    void updateStockTest() throws StockException {
        StockUpdateRequest request = new StockUpdateRequest();
        request.setName("SSP");
        LocalDateTime dateCreated = LocalDateTime.now();
        Stock stock = new Stock(30L, "JWA", 10000, 50L, dateCreated);
        Stock updatedStock = new Stock(30L, "SSP", 10000, 50L, dateCreated);
        when(stockRepository.findById(30L)).thenReturn(Optional.of(stock));
        when(stockRepository.save(any(Stock.class))).thenReturn(updatedStock);
        stockService.updateStock(request, 30L);
        verify(stockRepository, times(1)).findById(30L);
        verify(stockRepository, times(1)).save(any(Stock.class));
        assertThat(stock.getName()).isEqualTo("SSP");
        log.info("updated stock ->{}", stock);
    }

    @Test
    void updateStockThatDoesNotExist() {
        StockUpdateRequest request = new StockUpdateRequest();
        request.setName("SSP");
        when(stockRepository.findById(30L)).thenReturn(Optional.empty());

        StockException e = assertThrows(StockException.class, () -> stockService.updateStock(request, 30L));
        verify(stockRepository, times(1)).findById(30L);
        assertThat(e.getLocalizedMessage()).isEqualTo("Stock does not exist");

    }

    @Test
    void findStockById() throws StockException {
        Stock foundStock = new Stock(30L, "SSP", 32552, 20L);
        when(stockRepository.findById(30L)).thenReturn(Optional.of(foundStock));
        Stock stock = stockService.getStockById(30L);
        verify(stockRepository, times(1)).findById(30L);
        assertThat(stock.getName()).isEqualTo("SSP");


    }

    @Test
    void findStockThatDoesNotExist() {
        when(stockRepository.findById(30L)).thenReturn(Optional.empty());

        StockException e = assertThrows(StockException.class, () -> stockService.getStockById(30L));
        verify(stockRepository, times(1)).findById(30L);
        assertThat(e.getLocalizedMessage()).isEqualTo("Stock does not exist");
    }

    @Test
    void findAllStocksById() {
        Stock stock1 = new Stock(20L, "JWA", 10000, 40L, LocalDateTime.now(), null);
        Stock stock2 = new Stock(30L, "BWA", 20000, 50L, LocalDateTime.now(), null);
        Stock stock3 = new Stock(40L, "KWA", 40000, 60L, LocalDateTime.now(), null);
        Stock stock4 = new Stock(50L, "LWA", 40000, 60L, LocalDateTime.now(), null);
        List<Stock> stockList = Arrays.asList(stock1, stock2, stock3, stock4);
        when(stockRepository.findAll()).thenReturn(stockList);
        List<Stock> stocks = stockService.getAllStocks();
        verify(stockRepository, times(1)).findAll();
        log.info("Stocks found are ->{}", stocks);


    }
    @Test
    void deleteStockById(){
        doNothing().when(stockRepository).deleteById(30L);
        stockService.deleteStockById(30L);
        verify(stockRepository, times(1)).deleteById(30L);

    }
}