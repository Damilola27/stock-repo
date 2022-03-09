package com.quabbly.stock.data.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double price;
    private Long quantity;
    private LocalDateTime dateCreated = LocalDateTime.now();
    private LocalDateTime dateUpdated;

    public Stock(Long id, String name, double price, Long quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
