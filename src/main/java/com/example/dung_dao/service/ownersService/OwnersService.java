package com.example.dung_dao.service.ownersService;

import com.example.dung_dao.model.Owners;
import com.example.dung_dao.repo.IOwnersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnersService  implements IOwnersService{
    @Autowired
    private IOwnersRepo ownersRepo;

    @Override
    public Iterable<Owners> findAll() {
        return ownersRepo.findAll();
    }

    @Override
    public Optional<Owners> findById(Long id) {
        return ownersRepo.findById(id);
    }

    @Override
    public Owners save(Owners owners) throws Exception {
        return ownersRepo.save(owners);
    }

    @Override
    public void remove(Long id) {
        ownersRepo.deleteById(id);
    }
}
