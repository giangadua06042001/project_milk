package com.example.dung_dao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderItems {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long orderItemId;
    @ManyToOne
    private Orders order;
    @ManyToOne
    private Product product;
    private int quantity;
    private double totalPrice;
}
