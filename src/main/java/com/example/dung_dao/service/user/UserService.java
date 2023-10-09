package com.example.dung_dao.service.user;

import com.example.dung_dao.model.User;
import com.example.dung_dao.repo.IUserRepo;
import com.example.dung_dao.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepo userRepo;
    @Autowired
    private IProductService productService;

    @Override
    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }


    @Override
    public void remove(Long id) {


    }

    @Override
    public User saveUser(User user) throws Exception {
        if (userRepo.findUsersByUserName(user.getUserName()).isPresent()) {
            throw new Exception("Tên này đã được đăng ký");
        }
        if (userRepo.findUsersByEmail(user.getEmail()).isPresent()) {
            throw new Exception("Email này đã được đăng ký");
        }
        if (userRepo.findUsersByNumber(user.getNumber()).isPresent()) {
            throw new Exception("Số điện thoại này đã dược đăng ký");
        }
        return userRepo.save(user);
    }

    @Override
    public Optional<User> findUserByUserName(String name) {
        return userRepo.findUsersByUserName(name);
    }

    @Override
    public boolean isValidUser(String email, String password) {
     Optional<User>user=userRepo.findUsersByEmail(email);
     if(user.isPresent()){
         if(user.get().getPassword().equals(password)){
             return true;
         }
     }
     return false;
    }

}
