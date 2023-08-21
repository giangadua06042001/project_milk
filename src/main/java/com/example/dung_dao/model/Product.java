package com.example.dung_dao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long productId;
    private String productName;
    private String description;
    private double price;
    private int quantity;
    private String avatar;
    @ManyToOne
    private Owners owners;
    @ManyToOne
    private Category category;
    private Date createdAt;
    private Date updatedAt;

    @OneToMany(mappedBy = "product")
    private List<Orders> orders;
    @OneToMany(mappedBy = "product")
    private List<ProductOwners> productOwners;
}
