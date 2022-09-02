package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    List<Product> findAll();

    @Override
    Optional<Product> findById(Long productId);

    @Override
    Product save(Product product);

    @Override
    void deleteById(Long productId);

    List<Product> findByGroup(Group group);
}