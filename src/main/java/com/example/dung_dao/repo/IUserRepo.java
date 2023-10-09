package com.example.dung_dao.repo;

import com.example.dung_dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface IUserRepo extends JpaRepository<User, Long> {
    Optional<User>findUsersByEmail(String email);
    Optional<User>findUsersByUserName(String name);
    Optional<User>findUsersByNumber(String number);
    Optional<User>findUsersByPassword(String password);

}
