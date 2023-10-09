package com.example.dung_dao.service.user;

import com.example.dung_dao.model.User;
import com.example.dung_dao.service.IGeneralService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User> {
   User saveUser(User user)throws Exception;
   Optional<User> findUserByUserName(String name);
//   Optional<User> checkUser(String email,String accountName);
   boolean isValidUser(String email,String password);
}
