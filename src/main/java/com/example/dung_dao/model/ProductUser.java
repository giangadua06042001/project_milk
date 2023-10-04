package com.example.dung_dao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductUser {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long productUserId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}