package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dao.CartDao;
import com.kodilla.ecommercee.dao.UserDao;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    CartDao cartDao;
    UserDao userDao;

    public OrderMapper(CartDao cartDao, UserDao userDao) {
        this.userDao = userDao;
        this.cartDao = cartDao;
    }

    private BigDecimal calculateCost(final Order order) {
        if(order.getCart() == null) {
                return BigDecimal.ZERO;
        }
        if(order.getCart().getOrderItems() == null) {
            return BigDecimal.ZERO;
        }

        return BigDecimal.valueOf(order.getCart().getOrderItems().stream()
                .mapToDouble(s->s.getProduct().getPrice().doubleValue() * s.getQuantity())
                .sum()).setScale(2);
    }

    private Cart fetchCartById(final Long id) {
        if (id == null) {
            return null;
        }
        Optional<Cart> cartOptional = cartDao.findById(id);
        if(cartDao.findById(id).isPresent()) {
            return cartOptional.get();
        }
        return null;
    }

    private User fetchUserById(final Long id) {
        if (id == null) {
            return null;
        }
        Optional<User> userOptional = userDao.findById(id);
        if(userOptional.isPresent()) {
            return userOptional.get();
        }
        return null;
    }

    public OrderDto toDto(final Order order) {
        if(order == null) {
            return null;
        }
        OrderDto orderBean = new OrderDto();
        orderBean.setId(order.getId());
        orderBean.setRequiredDate(order.getRequiredDate());
        orderBean.setOrderDate(order.getOrderDate());
        orderBean.setShippedDate((order.getShippedDate()));
        orderBean.setNumber((order.getNumber()));
        orderBean.setStatus((order.getStatus()));
        orderBean.setComments(order.getComments());
        if(order.getCart() == null) {
            orderBean.setCartId(null);
        }
        else {
            orderBean.setCartId(order.getCart().getId());
        }
        if(order.getUser() == null) {
            orderBean.setUserId(null);
        }
        else {
            orderBean.setUserId(order.getUser().getId());
        }
        orderBean.setTotalCost(calculateCost(order));
        return orderBean;
    }

    public Order toEntity(OrderDto order) {
        if(order == null) {
            return null;
        }
        Order orderBean = new Order();
        orderBean.setId(order.getId());
        orderBean.setRequiredDate(order.getRequiredDate());
        orderBean.setShippedDate((order.getShippedDate()));
        orderBean.setOrderDate((order.getOrderDate()));
        orderBean.setNumber((order.getNumber()));
        orderBean.setStatus((order.getStatus()));
        orderBean.setComments(order.getComments());
        orderBean.setCart(fetchCartById(order.getCartId()));
        orderBean.setUser(fetchUserById(order.getUserId()));
        return orderBean;
    }

    public List<OrderDto> toDto(final List<Order> orderList) {
        return orderList.stream().map(order ->
                toDto(order)
        )
                .collect(Collectors.toList());
    }

    public List<Order> toEntity(final List<OrderDto> orderList) {
        return orderList.stream().map(order ->
               toEntity(order)
        )
                .collect(Collectors.toList());
    }
}
