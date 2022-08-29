package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.transaction.Transactional;

import java.util.List;

@Transactional
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findAll();
}
