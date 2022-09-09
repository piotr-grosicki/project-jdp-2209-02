package com.kodilla.ecommercee.domain;


import com.kodilla.ecommercee.repository.*;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderTestSuite {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void createOrder() {
        //Given
        Order order = new Order(LocalDate.now(), true, OrderStatus.IN_DELIVERY, new BigDecimal("21.5"));
        //When
        orderRepository.save(order);
        //Then
        assertEquals(1, orderRepository.count());
        assertTrue(orderRepository.existsById(order.getId()));
        //CleanUp
        orderRepository.deleteById(order.getId());
    }

    @Test
    public void getAllOrders() {
        //Given
        Order order1 = new Order(LocalDate.now(), true, OrderStatus.IN_DELIVERY, new BigDecimal("21.5"));
        Order order2 = new Order(LocalDate.now(), true, OrderStatus.IN_DELIVERY, new BigDecimal("21.5"));
        //When
        orderRepository.save(order1);
        orderRepository.save(order2);
        //Then
        assertEquals(2,orderRepository.findAll().size());
        //CleanUp
        orderRepository.deleteById(order1.getId());
        orderRepository.deleteById(order2.getId());
    }

    @Test
    public void getOrderById() {
        //Given
        Order order = new Order(LocalDate.now(), true, OrderStatus.IN_DELIVERY, new BigDecimal("21.5"));
        //When
        orderRepository.save(order);
        //Then
        assertTrue(orderRepository.findById(order.getId()).isPresent());
        //CleanUp
        orderRepository.deleteById(order.getId());
    }

    @Test
    void testDeleteProductFromTheList(){
        User user = new User("User1", "user@gmail.com", "Warsaw", "00-231", "Mariacka", "2", 22L, false, new UUID(22222,3), LocalTime.now());
        userRepository.save(user);
        Group group = new Group("Food", "Food for pets");
        groupRepository.save(group);
        List<Product> products = new ArrayList<>();
        Product product = new Product(group, "Duck meat", "Clean meat for dogs", new BigDecimal(31.22));
        products.add(product);
        productRepository.save(product);
        Cart cart = new Cart(user, products);
        cartRepository.save(cart);
        Order order1 = new Order(user, cart, false, OrderStatus.PROCESSING, new BigDecimal(13.23));
        orderRepository.save(order1);

        long userId = user.getId();
        long groupId = group.getId();
        long productId = product.getId();
        long cartId = cart.getId();
        long orderId = order1.getId();
        products.remove(product);

        assertTrue(cartRepository.existsById(cartId));

        orderRepository.deleteById(orderId);
        cartRepository.deleteById(cartId);
        productRepository.deleteById(productId);
        userRepository.deleteById(userId);
        groupRepository.deleteById(groupId);

    }

    @Test
    void testCheckUserByOrder(){
        User user = new User("User1", "user@gmail.com", "Warsaw", "00-231"
                , "Mariacka", "2", 22L, false, new UUID(22222,3), LocalTime.now());
        userRepository.save(user);
        Group group = new Group("Food", "Food for pets");
        groupRepository.save(group);
        List<Product> products = new ArrayList<>();
        Product product = new Product(group, "Duck meat", "Clean meat for dogs", new BigDecimal(31.22));
        products.add(product);
        productRepository.save(product);
        Cart cart = new Cart(user, products);
        cartRepository.save(cart);
        Order order1 = new Order(user, cart, false, OrderStatus.PROCESSING, new BigDecimal(13.23));
        orderRepository.save(order1);

        long userId = user.getId();
        long groupId = group.getId();
        long productId = product.getId();
        long cartId = cart.getId();
        long orderId = order1.getId();

        assertAll(
                () -> assertEquals("User1", order1.getUser().getLogin()),
                () -> assertEquals("user@gmail.com", order1.getUser().getMail()),
                () -> assertEquals("Warsaw", order1.getUser().getCity()),
                () -> assertEquals("00-231", order1.getUser().getPostalNumber()),
                () -> assertEquals("Mariacka", order1.getUser().getStreet()),
                () -> assertEquals("2", order1.getUser().getStreetNumber()),
                () -> assertEquals(22L, order1.getUser().getApartmentNumber()),
                () -> assertFalse(order1.getUser().isBlocked())
        );

        orderRepository.deleteById(orderId);
        cartRepository.deleteById(cartId);
        productRepository.deleteById(productId);
        userRepository.deleteById(userId);
        groupRepository.deleteById(groupId);
    }

    @Test
    void testDeleteOrderLeaveRestEntities(){
        User user = new User("User1", "user@gmail.com", "Warsaw", "00-231"
                , "Mariacka", "2", 22L, false, new UUID(22222,3), LocalTime.now());
        userRepository.save(user);
        Group group = new Group("Food", "Food for pets");
        groupRepository.save(group);
        List<Product> products = new ArrayList<>();
        Product product = new Product(group, "Duck meat", "Clean meat for dogs", new BigDecimal(31.22));
        products.add(product);
        productRepository.save(product);
        Cart cart = new Cart(user, products);
        cartRepository.save(cart);
        Order order1 = new Order(user, cart, false, OrderStatus.PROCESSING, new BigDecimal(13.23));
        orderRepository.save(order1);

        long userId = user.getId();
        long groupId = group.getId();
        long productId = product.getId();
        long cartId = cart.getId();
        long orderId = order1.getId();

        orderRepository.deleteById(orderId);

        assertAll(
                () -> assertTrue(userRepository.existsById(userId)),
                () -> assertTrue(groupRepository.existsById(groupId)),
                () -> assertTrue(productRepository.existsById(productId)),
                () -> assertTrue(cartRepository.existsById(cartId))
        );


        cartRepository.deleteById(cartId);
        productRepository.deleteById(productId);
        userRepository.deleteById(userId);
        groupRepository.deleteById(groupId);
    }

    @Test
    void testDeleteUser(){
        User user = new User("User1", "user@gmail.com", "Warsaw", "00-231"
                , "Mariacka", "2", 22L, false, new UUID(22222,3), LocalTime.now());
        userRepository.save(user);
        Group group = new Group("Food", "Food for pets");
        groupRepository.save(group);
        List<Product> products = new ArrayList<>();
        Product product = new Product(group, "Duck meat", "Clean meat for dogs", new BigDecimal(31.22));
        products.add(product);
        productRepository.save(product);
        Cart cart = new Cart(user, products);
        cartRepository.save(cart);
        Order order1 = new Order(user, cart, false, OrderStatus.PROCESSING, new BigDecimal(13.23));
        orderRepository.save(order1);

        long userId = user.getId();
        long groupId = group.getId();
        long productId = product.getId();
        long cartId = cart.getId();
        long orderId = order1.getId();

        try {
            userRepository.deleteById(userId);
        } catch (Exception e) {
            assertAll(
                    () -> assertEquals(DataIntegrityViolationException.class, e.getClass()),
                    () -> assertEquals(cart, order1.getCart()),
                    () -> assertFalse(order1.isPaid()),
                    () -> assertEquals(order1.getOrderStatus(), OrderStatus.PROCESSING),
                    () -> assertEquals(new BigDecimal(13.23), order1.getTotalPrice())
            );
        }

        orderRepository.deleteById(orderId);
        cartRepository.deleteById(cartId);
        productRepository.deleteById(productId);
        groupRepository.deleteById(groupId);
    }

    @Test
    void testDeleteGroup(){
        User user = new User("User1", "user@gmail.com", "Warsaw", "00-231"
                , "Mariacka", "2", 22L, false, new UUID(22222,3), LocalTime.now());
        userRepository.save(user);
        Group group = new Group("Food", "Food for pets");
        groupRepository.save(group);
        List<Product> products = new ArrayList<>();
        Product product = new Product(group, "Duck meat", "Clean meat for dogs", new BigDecimal(31.22));
        products.add(product);
        productRepository.save(product);
        Cart cart = new Cart(user, products);
        cartRepository.save(cart);
        Order order1 = new Order(user, cart, false, OrderStatus.PROCESSING, new BigDecimal(13.23));
        orderRepository.save(order1);

        long userId = user.getId();
        long groupId = group.getId();
        long productId = product.getId();
        long cartId = cart.getId();
        long orderId = order1.getId();

        try{
            groupRepository.deleteById(groupId);
        } catch (Exception e) {
            assertAll(
                    () -> assertEquals(DataIntegrityViolationException.class, e.getClass()),
                    () -> assertEquals(user, order1.getUser()),
                    () -> assertEquals(cart, order1.getCart()),
                    () -> assertFalse(order1.isPaid()),
                    () -> assertEquals(order1.getOrderStatus(), OrderStatus.PROCESSING),
                    () -> assertEquals(new BigDecimal(13.23), order1.getTotalPrice())
            );
        }

        orderRepository.deleteById(orderId);
        cartRepository.deleteById(cartId);
        productRepository.deleteById(productId);
        userRepository.deleteById(userId);

    }

}
