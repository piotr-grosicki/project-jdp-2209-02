package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {

    @Override
    Group save(Group group);

    @Override
    List<Group> findAll();

    @Override
    Optional<Group> findById(Long groupId);

}