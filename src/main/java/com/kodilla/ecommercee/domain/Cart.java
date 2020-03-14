package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carts")
public class Cart {

    @GeneratedValue
    @Id
    @NotNull
    @Column(name = "id")
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER )
    @JoinTable(
            name = ("join_carts_products"),
            joinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private List<Product> products = new ArrayList<>();
}
