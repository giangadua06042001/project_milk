package com.example.dung_dao.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String message;
    private Long userId;
    private String userName;
    private String fullName;
    private String role;
    private boolean isBlock;
}
