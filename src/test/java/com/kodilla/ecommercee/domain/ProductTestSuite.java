package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductTestSuite {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GroupRepository groupRepository;

    @Test
    void addProductTest() {
        //Given
        Group group = new Group("Group_name", "Group_description");
        Product product = new Product(group, "Product", "Product_description", new BigDecimal(10));

        //When
        groupRepository.save(group);
        productRepository.save(product);

        //Then
        assertEquals(1, productRepository.findAll().size());
        assertTrue(productRepository.findById(product.getId()).isPresent());

        //CleanUp
        productRepository.deleteAll();
        groupRepository.deleteAll();
    }

    @Test
    void getAllProductsTest() {
        //Given
        Group group = new Group("Group_name", "Group_description");
        Product product1 = new Product(group, "Product1", "Product_description1", new BigDecimal(10));
        Product product2 = new Product(group, "Product2", "Product_description2", new BigDecimal(100));

        //When
        groupRepository.save(group);
        productRepository.save(product1);
        productRepository.save(product2);

        //Then
        assertEquals(2, productRepository.findAll().size());

        //CleanUp
        productRepository.deleteAll();
        groupRepository.deleteAll();
    }

    @Test
    void deleteProductTest() {
        //Given
        Group group = new Group("Group_name", "Group_description");
        groupRepository.save(group);
        Product product = new Product(group, "Product", "Product_description", new BigDecimal(10));
        productRepository.save(product);

        //When
        Long id = product.getId();
        productRepository.deleteById(id);

        //Then
        assertEquals(0, productRepository.findAll().size());
        assertEquals(Optional.empty(), productRepository.findById(id));

        //CleanUp
        productRepository.deleteAll();
        groupRepository.deleteAll();
    }

    @Test
    void shouldFindGroupInProduct() {
        //Given
        Group group = new Group("Group_name", "Group_description");
        groupRepository.save(group);
        Product product = new Product(group, "Product", "Product_description", new BigDecimal(10));

        //When
        productRepository.save(product);

        //Then
        assertEquals(group, product.getGroup());

        //CleanUp
        productRepository.deleteAll();
        groupRepository.deleteAll();
    }

    @Test
    void shouldDeleteProductAndLeftGroup() {
        //Given
        Group group = new Group("Group_name", "Group_description");
        groupRepository.save(group);
        Product product = new Product(group, "Product", "Product_description", new BigDecimal(10));
        productRepository.save(product);

        //When
        Long productId = product.getId();
        Long groupId = group.getId();
        productRepository.deleteById(productId);

        //Then
        assertEquals(0, productRepository.findAll().size());
        assertEquals(Optional.empty(), productRepository.findById(productId));
        assertTrue(groupRepository.existsById(group.getId()));
        assertEquals(1, groupRepository.findAll().size());

        //CleanUp
        productRepository.deleteAll();
        groupRepository.deleteAll();
    }
}