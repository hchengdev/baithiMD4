package com.baithimd4.service.implement;

import com.baithimd4.model.Category;
import com.baithimd4.repository.ICategoryRepository;
import com.baithimd4.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {
    private final ICategoryRepository iCategoryRepository;

    @Override
    public void save(Category category) {
        iCategoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        iCategoryRepository.deleteById(id);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return iCategoryRepository.findById(id);
    }

    @Override
    public Iterable<Category> findAll() {
        return iCategoryRepository.findAll();
    }

    @Override
    public void update(Category category) {

    }



}
