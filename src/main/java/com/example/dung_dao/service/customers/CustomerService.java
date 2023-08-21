package com.example.dung_dao.service.customers;

import com.example.dung_dao.model.Customers;
import com.example.dung_dao.repo.ICustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService{
    @Autowired
    private ICustomerRepo customerRepo;
    @Override
    public Iterable<Customers> findAll() {
        return customerRepo.findAll();
    }

    @Override
    public Optional<Customers> findById(Long id) {
        return customerRepo.findById(id);
    }

    @Override
    public Customers save(Customers customers) throws Exception {
        if(customerRepo.findByCustomerName(customers.getCustomerName()).isPresent()){
          throw new Exception("Tên đăng nhập đã tồn tại");
        }
        if(customerRepo.findByEmail(customers.getEmail()).isPresent()){
            throw new Exception("Emai này đã được đăng ký");
        }
        return customerRepo.save(customers);
    }

    @Override
    public void remove(Long id) {
      customerRepo.deleteById(id);
    }
}
