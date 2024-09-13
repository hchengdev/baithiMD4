package com.baithimd4.service;

import java.util.Optional;

public interface IGenericService<T> {
    void save(T t);

    void deleteById(Long id);

    Optional<T> findById(Long id);

    Iterable<T> findAll();

    void update(T t);

}
