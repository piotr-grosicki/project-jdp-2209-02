package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.GroupRepository;

import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupTestSuite {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProductRepository productRepository;
    private final String GROUP_NAME = "Laptops";
    private final String GROUP_DESC = "Laptops for work";


    @Test
    void testAddGroup() {
        Group group = new Group(GROUP_NAME, GROUP_DESC);
        Group group1 = new Group(GROUP_NAME + "2", GROUP_DESC + "2");

        groupRepository.save(group);
        groupRepository.save(group1);
        long id = group.getId();
        long id2 = group1.getId();

        assertNotEquals(0, id);
        assertNotEquals(0, id2);
        assertEquals(2, groupRepository.findAll().size());

        groupRepository.deleteAll();

    }

    @Test
    void testGroupSaveWithProducts() {
        //Given
        Group group = new Group(GROUP_NAME, GROUP_DESC);
        Product product = new Product(group, "name test 1","Name test 1", new BigDecimal("130.5"));
        Product product1 = new Product(group,"name test 2", "Descriptions test 2", new BigDecimal("150.5"));
        group.getProductList().add(product);
        group.getProductList().add(product1);

        product.setGroup(group);
        product1.setGroup(group);

        groupRepository.save(group);
        long id = group.getId();
        //When
        Group groupFromRepository = groupRepository.findById(group.getId()).orElse(null);
        List<String> nameOfGroup = groupFromRepository.getProductList().stream().map(Product::getName).collect(Collectors.toList());
        List<String> nameOfProduct = groupFromRepository.getProductList().stream().map(Product::getName).collect(Collectors.toList());
        //Then
        assertEquals(2, nameOfGroup.size());
        assertEquals(2, nameOfProduct.size());
        assertEquals("name test 1", nameOfProduct.get(0));
        assertEquals("name test 2", nameOfProduct.get(1));

        assertNotEquals(0, id);
        //Clean up
        productRepository.deleteAll();
        groupRepository.deleteById(id);
    }

    @Test
    void testGetGroupById() {
        Group group = new Group(GROUP_NAME, GROUP_DESC);

        groupRepository.save(group);
        long id = group.getId();

        assertTrue(groupRepository.existsById(id));

        groupRepository.deleteById(id);

        assertFalse(groupRepository.existsById(id));
    }
}