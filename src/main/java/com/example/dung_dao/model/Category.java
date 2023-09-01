package com.example.dung_dao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long categoryId;

    // Các trường khác của Category
    private String nameCategory;
    private String avatarCategory;
    @OneToMany(mappedBy = "category")
    private List<Product> products;

}