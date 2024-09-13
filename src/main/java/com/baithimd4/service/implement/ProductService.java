package com.baithimd4.service.implement;

import com.baithimd4.exeption.OurException;
import com.baithimd4.model.Product;
import com.baithimd4.repository.IProductRepo;
import com.baithimd4.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {
    private final IProductRepo productRepo;


    @Override
    public void save(Product product) {
        productRepo.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepo.findById(id);
        if (productRepo.findById(id).isPresent()) {
            productRepo.deleteById(id);
        } else {
            throw new OurException("Product with id " + id + " not found");
        }
    }

    @Override
    public Optional<Product> findById(Long id) {
        if (productRepo.findById(id).isPresent()) {
            return productRepo.findById(id);
        } else {
            throw new OurException("Product with id " + id + " not found");
        }
    }

    @Override
    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public void update(Long id, Product product) {
        Product existingProduct = productRepo.findById(id)
               .orElseThrow(() -> new OurException("Product with id " + id + " not found"));

        existingProduct.setTenSanPham(product.getTenSanPham());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setTinhTrang(product.getTinhTrang());
        existingProduct.setCategory(product.getCategory());

        productRepo.save(existingProduct);
    }


}
