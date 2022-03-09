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
public class StockRequest {
    @NotNull(message = "Enter Stock Name")
    private String name;
    @NotNull(message = "Price cannot be empty")
    private double price;

}
