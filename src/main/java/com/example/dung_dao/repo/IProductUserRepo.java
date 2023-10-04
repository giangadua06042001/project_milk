package com.example.dung_dao.repo;

import com.example.dung_dao.model.ProductUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductUserRepo extends JpaRepository<ProductUser, Long> {
}
