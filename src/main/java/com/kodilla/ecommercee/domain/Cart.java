package com.kodilla.ecommercee.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "carts")
public class Cart {

    @GeneratedValue
    @Id
    @NotNull
    @Column(name = "id")
    private long id;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_name")
    private User user;

    @OneToMany(mappedBy = "cartId", fetch = FetchType.EAGER)
    private List<Product> products;

}
