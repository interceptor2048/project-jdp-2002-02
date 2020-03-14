package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="product_groups2")
public class Group2 {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    @OneToMany(
            targetEntity = Product2.class,
            mappedBy = "groupId",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    List<Product2> products = new ArrayList<>();
}
