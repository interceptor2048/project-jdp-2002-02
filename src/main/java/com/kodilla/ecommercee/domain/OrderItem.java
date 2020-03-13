package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="orderItems")
public class OrderItem {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="quantity")
    private int quantity;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Product2 product;

    @ManyToOne
    @JoinColumn(name="cartId")
    private Cart2 cart;

    public OrderItem(int quantity) {
        this.quantity = quantity;
    }
}
