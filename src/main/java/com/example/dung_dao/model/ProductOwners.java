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
public class ProductOwners{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long productOwnersId;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Owners owners;
}
