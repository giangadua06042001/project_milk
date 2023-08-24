package com.example.dung_dao.service.product_owners;

import com.example.dung_dao.model.ProductOwners;
import com.example.dung_dao.repo.IProductOwnersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductOwnersService implements IProductOwnersService{
    @Autowired
    private IProductOwnersRepo productOwnersRepo;
    @Override
    public Iterable<ProductOwners> findAll() {
        return productOwnersRepo.findAll();
    }

    @Override
    public Optional<ProductOwners> findById(Long id) {
        return productOwnersRepo.findById(id);
    }

    @Override
    public ProductOwners save(ProductOwners productOwners) throws Exception {
        return productOwnersRepo.save(productOwners);
    }

    @Override
    public void remove(Long id) {
      productOwnersRepo.deleteById(id);
    }
}
