package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.GroupRepository;

import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupTestSuite {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProductRepository productRepository;
    private String GROUP_NAME = "Laptops";
    private String GROUP_DESC = "Laptops for work";


    @Test
    void testAddGroup(){
        Group group = new Group(GROUP_NAME, GROUP_DESC);
        Group group1 = new Group("Oils", "Oils for heave machines");

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
    void testGroupSaveWithProducts(){

        Product product = new Product("Castrol 5W40",
                "Great oil for heavy machines 6 liters", new BigDecimal(130.5));
        Product product1 = new Product("Castrol 10W40", "Great oil for cars", new BigDecimal(100));
        Group group = new Group("Oils", "Oils for engines");
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product1);
        group.setProductList(productList);
        product.setGroup(group);
        product1.setGroup(group);

        groupRepository.save(group);
        long id = group.getId();


        assertNotEquals(0, id);

        groupRepository.deleteById(id);
        productRepository.deleteAll();
    }

    @Test
    void testGetGroupById(){
        Group group = new Group(GROUP_NAME, GROUP_DESC);

        groupRepository.save(group);
        long id = group.getId();

        assertTrue(groupRepository.existsById(id));

        groupRepository.deleteById(id);
    }

    @Test
    void testDeleteGroupAndLeaveProducts(){
        Product product = new Product("Castrol 5W40",
                "Great oil for heavy machines 6 liters", new BigDecimal(130.5));
        Product product1 = new Product("Castrol 10W40", "Great oil for cars", new BigDecimal(100));
        Group group = new Group("Oils", "Oils for engines");
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product1);
        group.setProductList(productList);
        product.setGroup(group);
        product1.setGroup(group);

        groupRepository.save(group);
        groupRepository.deleteById(group.getId());
        product.setGroup(null);
        product1.setGroup(null);
        List<Group> groups = groupRepository.findAll();

        assertEquals(2, group.getProductList().size());
        assertEquals(0, groups.size());

        productRepository.deleteAll();

    }

}