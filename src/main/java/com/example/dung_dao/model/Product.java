package com.example.dung_dao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long productId;
    private String nameProduct;
    private double price;
    private int quantity;
    private Date dateCreated;
    private Date updateCreated;
    private String painted;
    private double weight;
    private String pack;
    private String url;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductUser> productUsers = new HashSet<>();

}
