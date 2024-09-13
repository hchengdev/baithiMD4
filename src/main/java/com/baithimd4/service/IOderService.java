package com.baithimd4.service;

import com.baithimd4.model.Oder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IOderService extends IGenericService<Oder> {
    Page<Oder> findAll(Pageable pageable);
}
