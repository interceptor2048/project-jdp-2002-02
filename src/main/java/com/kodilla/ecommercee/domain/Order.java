package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name="status")
    private int status;

    @Column(name="orderDate")
    private LocalDate orderDate;

    @Column(name="requiredDate")
    private LocalDate requiredDate;

    @Column(name="shippedDate")
    private LocalDate shippedDate;

    @Column(name="comments")
    private String comments;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cart_id", referencedColumnName = "id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Order (Cart cart, User user ) {
        this.cart = cart;
        this. user = user;
    }
}
