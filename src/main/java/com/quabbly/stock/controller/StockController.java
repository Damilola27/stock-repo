package com.quabbly.stock.controller;


import com.quabbly.stock.data.model.Stock;
import com.quabbly.stock.dto.request.StockCreateRequest;
import com.quabbly.stock.dto.request.StockUpdateRequest;
import com.quabbly.stock.dto.response.ApiErrorResponse;
import com.quabbly.stock.exceptions.StockException;
import com.quabbly.stock.service.StockService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks")
@AllArgsConstructor
public class StockController {
    private final StockService stockService;

    @PostMapping(path = "")
    public ResponseEntity<?> createStock(@Valid @RequestBody StockCreateRequest request) {
        return new ResponseEntity<>(stockService.createStock(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStocksById(@PathVariable("id") Long id) {
        try {
            Stock stock = stockService.getStockById(id);
            return new ResponseEntity<>(stock, HttpStatus.OK);
        } catch (StockException e) {
            return new ResponseEntity<>(new ApiErrorResponse(HttpStatus.NOT_FOUND.name(), e.getMessage(), HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllStocks() {
        List<Stock> stocks = stockService.getAllStocks();
        return new ResponseEntity<>(stocks, HttpStatus.OK);

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateStock(@PathVariable("id") Long id, @RequestBody StockUpdateRequest request) {
        try {
            Stock updatedStock = stockService.updateStock(request, id);
            return new ResponseEntity<>(updatedStock, HttpStatus.OK);
        } catch (StockException e) {
            return new ResponseEntity<>(new ApiErrorResponse(HttpStatus.NOT_FOUND.name(), e.getMessage(), HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStock(@PathVariable("id") Long id){
        stockService.deleteStockById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
