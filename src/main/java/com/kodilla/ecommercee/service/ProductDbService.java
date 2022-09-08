package com.kodilla.ecommercee.service;

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

    public void saveProduct(final Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(final long productId) throws ProductNotFoundException {
        if (!productRepository.existsById(productId)){
            throw new ProductNotFoundException();
        }
        productRepository.deleteById(productId);
    }

    public Product updateProduct(final Product product) throws ProductNotFoundException {
        Product productToUpdate = getProductById(product.getId());
        if (product.getGroup() != null) {
            productToUpdate.setGroup(product.getGroup());
        }
        if (product.getName() != null) {
            productToUpdate.setName(product.getName());
        }
        if (product.getDescription() != null) {
            productToUpdate.setDescription(product.getDescription());
        }
        if (product.getPrice() != null) {
            productToUpdate.setPrice(product.getPrice());
        }
        if (product.getCarts() != null) {
            productToUpdate.setCarts(product.getCarts());
        }
        return productRepository.save(productToUpdate);
    }
}
