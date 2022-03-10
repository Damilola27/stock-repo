package com.quabbly.stock.dto.request;


import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockCreateRequest {
    @NotNull(message = "name cannot be empty")
    private String name;
    @NotNull(message = "price cannot be empty")
    private double price;
    @NotNull(message = "quantity cannot be empty")
    private Long quantity;

}
