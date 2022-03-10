package com.quabbly.stock.service;

import com.quabbly.stock.data.model.Stock;
import com.quabbly.stock.dto.request.StockCreateRequest;
import com.quabbly.stock.dto.request.StockUpdateRequest;
import com.quabbly.stock.exceptions.StockException;

import java.util.List;

public interface StockService {
    Stock createStock(StockCreateRequest request);
    Stock  updateStock(StockUpdateRequest request, Long id) throws StockException;
    List<Stock> getAllStocks();
    Stock getStockById(Long id) throws StockException;
    void deleteStockById(Long id);

}
