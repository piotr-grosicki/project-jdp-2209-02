package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.GroupRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupTestSuite {

    @Autowired
    private GroupRepository groupRepository;

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
    void testGetGroupById() {
        Group group = new Group(GROUP_NAME, GROUP_DESC);

        groupRepository.save(group);
        long id = group.getId();

        assertTrue(groupRepository.existsById(id));

        groupRepository.deleteById(id);

        assertFalse(groupRepository.existsById(id));
    }
}