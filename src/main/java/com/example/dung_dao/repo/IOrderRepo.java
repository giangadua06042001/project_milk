package com.example.dung_dao.repo;

import com.example.dung_dao.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepo extends JpaRepository<Orders,Long> {
}
