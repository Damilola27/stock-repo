package com.quabbly.stock.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quabbly.stock.data.model.Stock;
import com.quabbly.stock.dto.request.StockCreateRequest;
import com.quabbly.stock.dto.request.StockUpdateRequest;
import com.quabbly.stock.exceptions.StockException;
import com.quabbly.stock.service.StockService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class StockControllerTest {
    @MockBean
    private StockService stockService;

    ObjectMapper mapper = new ObjectMapper();


    @Autowired
    MockMvc mockMvc;

    @Test
    void createStockControllerTest() throws Exception {
        StockCreateRequest request = new StockCreateRequest("JWA", 10000, 50L);
        Stock stock = new Stock(30L, "JWA", 10000, 50L, LocalDateTime.now(), null);
        when(stockService.createStock(request)).thenReturn(stock);

        String params = mapper.writeValueAsString(request);

        mockMvc.perform(post("/api/v1/stocks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(params)
        ).andExpect(status().is(201)).andDo(print());
    }

    @Test
    void getStockByIdControllerTest() throws StockException, Exception {
        Stock foundStock = new Stock(30L, "SSP", 32552, 20L);
        when(stockService.getStockById(30L)).thenReturn(foundStock);

        mockMvc.perform(get("/api/v1/stocks/30")).
                andExpect(status().is(200))
                .andDo(print());
    }

    @Test
    void getStockThatDoesNotNotExistTest() throws StockException, Exception {
        when(stockService.getStockById(20L)).thenThrow(new StockException("Stock does not exists"));

        mockMvc.perform(get("/api/v1/stocks/20"))
                .andExpect(status().is(404))
                .andDo(print());

    }
    @Test
    void getAllStocks() throws Exception {
        Stock stock1 = new Stock(20L, "JWA", 10000, 40L, LocalDateTime.now(), null);
        Stock stock2 = new Stock(30L, "BWA", 20000, 50L, LocalDateTime.now(), null);
        Stock stock3 = new Stock(40L, "KWA", 40000, 60L, LocalDateTime.now(), null);
        Stock stock4 = new Stock(50L, "LWA", 40000, 60L, LocalDateTime.now(), null);
        List<Stock> stockList = Arrays.asList(stock1, stock2, stock3, stock4);

       when(stockService.getAllStocks()).thenReturn(stockList);
        mockMvc.perform(get("/api/v1/stocks"))
                .andExpect(status().is(200))
                .andDo(print());


    }
    @Test
    void updateStocks() throws StockException, Exception {
        StockUpdateRequest request = new StockUpdateRequest();
        request.setName("SSP");
        LocalDateTime dateCreated = LocalDateTime.now();
        Stock updatedStock = new Stock(30L, "SSP", 10000, 50L, dateCreated, dateCreated.plusDays(2));

        when(stockService.updateStock(request, 30L)).thenReturn(updatedStock);

        String params = mapper.writeValueAsString(request);

        mockMvc.perform(put("/api/v1/stocks/30")
                .contentType(MediaType.APPLICATION_JSON)
                .content(params))
                .andExpect(status().is(200))
                .andDo(print());
    }
    @Test
    void updateStockThatDoesNotExistTest() throws StockException, Exception {
        StockUpdateRequest request = new StockUpdateRequest();
        request.setName("SSP");
        when(stockService.updateStock(request, 20L)).thenThrow( new StockException("This stock does not exist"));
        String params = mapper.writeValueAsString(request);

        mockMvc.perform(put("/api/v1/stocks/20")
                .contentType(MediaType.APPLICATION_JSON)
                .content(params))
                .andExpect(status().is(404)).andDo(print());
    }
    @Test
    void deleteStockTest() throws Exception {
        doNothing().when(stockService).deleteStockById(20L);

        mockMvc.perform(delete("/api/v1/stocks/20"))
                .andExpect(status().is(204))
                .andDo(print());
    }
}