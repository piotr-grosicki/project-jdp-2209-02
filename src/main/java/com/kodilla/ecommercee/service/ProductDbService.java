package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDbService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(final long productId) throws ProductNotFoundException {
        return productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

    public Product saveProduct(final Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(final long productId) throws ProductNotFoundException {
        if (!productRepository.existsById(productId)){
            throw new ProductNotFoundException();
        }
        productRepository.deleteById(productId);
    }

    public List<Product> getProductsByGroup(final Group group) {
        return productRepository.findByGroup(group);
    }

}