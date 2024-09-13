package com.baithimd4.service.implement;

import com.baithimd4.exeption.OurException;
import com.baithimd4.model.Oder;
import com.baithimd4.model.Product;
import com.baithimd4.repository.IOderRepo;
import com.baithimd4.repository.IProductRepo;
import com.baithimd4.service.IOderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OderService implements IOderService {
    private final IProductRepo productRepo;
    private final IOderRepo repository;
    @Override
    public void save(Oder oder) {
        repository.save(oder);
    }

    @Override
    public void deleteById(Long id) {
        if (repository.findById(id).isPresent())
            repository.deleteById(id);
        else
            throw new OurException("Order" + id + " not found");
    }

    @Override
    public Optional<Oder> findById(Long id) {
        if (repository.findById(id).isPresent())
            return repository.findById(id);
        else
            throw new OurException("Order " + id + " not found");
    }

    @Override
    public Iterable<Oder> findAll() {
        return repository.findAll();
    }

    @Override
    public void update(Oder oder) {
        repository.save(oder);
    }


    @Override
    public Page<Oder> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Oder> findByNgayMuaBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable pageable) {
        return repository.findByNgayMuaBetween(startDateTime, endDateTime, pageable);
    }
}
