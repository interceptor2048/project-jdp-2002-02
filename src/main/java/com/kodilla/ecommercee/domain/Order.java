package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;
}
