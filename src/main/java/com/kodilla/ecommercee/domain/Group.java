package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="product_groups")
public class Group {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "groupId",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    List<Product> products = new ArrayList<>();
}
