package com.example.dung_dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Orders {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long orderId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer_id")
    private Customers customers;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    private Date createdAt;
    private Date updatedAt;
}