package com.example.dung_dao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class Customers{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long customerId;
    @Column(unique = true)
    @NotBlank(message = "Username is required")
    private String customerName;
    @NotBlank(message = "Password is required")
    private String password;
    @Column(unique = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    private String address;
    private String phone;
    private Date dateCreated;
    private String birthday;
    private String avatar;
    private String background;
    private String fullName;
    private String role="customer";

    @OneToMany(mappedBy = "customers")
    private List<Orders> orders;



}
