package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.jetbrains.annotations.NotNull;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "carts")
public class Cart {
    @GeneratedValue
    @Id
    @NotNull
    @Column(name = "id")
    private long id;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "username")
    private User user;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
    private List<Product> products;

    public Cart(User user) {
        this.user = user;
        this.products = new ArrayList<>();
    }
}
