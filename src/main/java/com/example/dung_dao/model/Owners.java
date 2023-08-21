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
public class Owners {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long owners_id;
    private String fullName;
    private String accountName;
    private String email;
    private String password;
    private String phone;
    private String address;
    @OneToMany(mappedBy = "owners")
    private List<ProductOwners> productOwners;
}
