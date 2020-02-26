package com.kodilla.ecommercee.domain;


import lombok.*;
import javax.validation.constraints.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@Builder
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

    @NotNull
    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group productGroup;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

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
