package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Transactional
@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {

    @Override
    List<Order> findAll();

    @Override
    Optional<Order> findById(Long longId);

    @Override
    Order save(Order order);
}
