package com.kodilla.ecommercee.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "price")
    @NotNull
    private double price;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group productGroup;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public Product(@NotNull String name, @NotNull double price, String description, Group productGroup, Cart cart) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.productGroup = productGroup;
        this.cart = cart;
    }

    public Product(@NotNull String name, @NotNull double price, String description, Group productGroup) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.productGroup = productGroup;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", productGroup=" + productGroup +
                ", cart=" + cart +
                '}';
    }
}
