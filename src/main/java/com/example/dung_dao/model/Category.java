package com.example.dung_dao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long categoryId;
    private String name;
    private Date createDate;
    private Date updateCreate;
    private String url;
    @ManyToOne
    private Product product;

}
