package com.example.dung_dao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long userId;
    @Column(unique = true)
    @NotBlank(message = "tên đăng nhập không được để trống")
    private String userName;
    private String fullName;
    @Column(unique = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Nhập email đúng định dạng")
    private String email;
    private String number;
    private String address;
    private Date dateCreated;
    private Date updateCreated;
    private String avatar;
    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 6, max = 32, message = "Kí tự mật khẩu phải từ 6-32.")
    private String password;
    private String birthday;
    private String role;
    private boolean isBlock;


}
