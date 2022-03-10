package com.quabbly.stock.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockCreateRequest {
    @NotNull(message = "Enter Stock Name")
    private String name;
    @NotNull(message = "Price can not be empty")
    private double price;
    @NotNull(message = "quantity can not be null")
    private Long quantity;

}
