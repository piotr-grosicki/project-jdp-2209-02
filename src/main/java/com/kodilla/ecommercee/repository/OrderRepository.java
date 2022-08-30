package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Order;
import lombok.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {

    @Override
    List<Order> findAll();

    @Override
    Optional<Order> findById(Long longId);

    @Override
    Order save(Order order);

    @Override
    void deleteById(Long orderId);
}
