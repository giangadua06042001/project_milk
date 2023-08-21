package com.example.dung_dao.repo;

import com.example.dung_dao.model.Owners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOwnersRepo extends JpaRepository<Owners,Long> {
}
