package com.example.dung_dao.repo.login;

import com.example.dung_dao.model.login.State;
import com.example.dung_dao.model.login.User;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepo {
    public ConcurrentHashMap<User, Long> users = new ConcurrentHashMap<>();

    public User addUser(String name, String email, String hashedPassword, State state) {
        UUID uuid = UUID.randomUUID();
        long id = uuid.getMostSignificantBits();
        User user = User.builder()
                .userId(id)
                .name(name)
                .email(email)
                .hashedPassword(hashedPassword)
                .build();
        users.put(user, id);
        return user;
    }
}
