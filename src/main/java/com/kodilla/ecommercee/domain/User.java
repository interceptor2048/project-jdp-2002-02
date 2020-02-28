package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @OneToMany(
        targetEntity=Order.class,
        mappedBy="user",
        cascade=CascadeType.ALL,
        fetch=FetchType.LAZY
    )
    private List<Order> orders = new ArrayList<>();
}
