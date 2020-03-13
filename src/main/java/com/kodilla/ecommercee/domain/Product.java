package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="description")
    @Size(max = 1000)
    private String description;

    @ManyToOne
    @JoinColumn(name="groupId")
    private Group groupId;
}
