package com.example.dung_dao.service.product_user;

import com.example.dung_dao.model.ProductUser;
import com.example.dung_dao.repo.IProductUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductUserService implements IProductUserService {
    @Autowired
    private IProductUserRepo productUserRepo;
    @Override
    public Iterable<ProductUser> findAll() {
        return productUserRepo.findAll();
    }

    @Override
    public Optional<ProductUser> findById(Long id) {
        return productUserRepo.findById(id);
    }

    @Override
    public ProductUser save(ProductUser productUser) {
        return productUserRepo.save(productUser);
    }

    @Override
    public void remove(Long id) {
        productUserRepo.deleteById(id);
    }
}
