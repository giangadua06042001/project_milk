package com.example.dung_dao;

import com.example.dung_dao.model.login.State;
import com.example.dung_dao.model.login.User;
import com.example.dung_dao.repo.login.UserRepo;
import jakarta.persistence.Table;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;


//@SpringBootTest
public class TestUserRepo {
    @Test
    public void addUser(){
        UserRepo userRepo=new UserRepo();
        User user=userRepo.addUser("Adua","adua@gmail.com","OX-12321am21321", State.PENDING);
        assertThat(user).isNotNull();
        System.out.println(user.getUserId());
        assertThat(user.getUserId()).isNotPositive();
    }
}
