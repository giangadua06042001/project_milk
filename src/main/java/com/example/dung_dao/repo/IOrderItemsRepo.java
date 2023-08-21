package com.example.dung_dao.repo;

import com.example.dung_dao.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderItemsRepo extends JpaRepository<OrderItems,Long> {
}
