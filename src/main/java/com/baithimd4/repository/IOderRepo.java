package com.baithimd4.repository;

import com.baithimd4.model.Oder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOderRepo extends JpaRepository<Oder, Long> {
    Page<Oder>findAll(Pageable pageable);
}
