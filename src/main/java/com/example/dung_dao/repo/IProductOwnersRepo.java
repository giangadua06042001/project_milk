package com.example.dung_dao.repo;

import com.example.dung_dao.model.ProductOwners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductOwnersRepo  extends JpaRepository<ProductOwners,Long> {
}
