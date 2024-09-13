package com.baithimd4.service;

import com.baithimd4.model.Oder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;


public interface IOderService extends IGenericService<Oder> {
    Page<Oder> findAll(Pageable pageable);

    public Page<Oder> findByNgayMuaBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable pageable);

}
