package com.quabbly.stock.service;

import com.quabbly.stock.data.model.Stock;
import com.quabbly.stock.data.repository.StockRepository;
import com.quabbly.stock.dto.request.StockCreateRequest;
import com.quabbly.stock.dto.request.StockUpdateRequest;
import com.quabbly.stock.exceptions.StockException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final ModelMapper modelMapper;


    @Override
    public Stock createStock(StockCreateRequest request) {
        Stock stock = modelMapper.map(request, Stock.class);
        stock = stockRepository.save(stock);
        return stock;
    }

    @Override
    public Stock updateStock(StockUpdateRequest request, Long id) throws StockException {
        Stock savedStock = stockRepository.findById(id).orElseThrow(() -> new StockException("Stock does not exist"));

        modelMapper.map(request, savedStock);

        savedStock.setDateUpdated(LocalDateTime.now());
        return stockRepository.save(savedStock);
    }

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public Stock getStockById(Long id) throws StockException {
        return stockRepository.findById(id).orElseThrow(()-> new StockException("Stock does not exist"));
    }

    @Override
    public void deleteStockById(Long id) {
        stockRepository.deleteById(id);
    }
}
