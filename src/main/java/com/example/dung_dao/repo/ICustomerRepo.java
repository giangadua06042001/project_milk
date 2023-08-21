package com.example.dung_dao.repo;

import com.example.dung_dao.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface ICustomerRepo extends JpaRepository<Customers,Long> {
    Optional<Customers>findByCustomerName(String customerName);
    Optional<Customers>findByEmail(String email);
}
