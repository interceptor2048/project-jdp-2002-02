package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "price")
    @NotNull
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group groupId;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cartId;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", productGroup=" + groupId +
                ", cart=" + cartId +
                '}';
    }
}
