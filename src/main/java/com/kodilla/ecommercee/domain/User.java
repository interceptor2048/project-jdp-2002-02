package com.kodilla.ecommercee.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name =id)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="first_name")
    @NotNull
    private String first_name;

    @Column(name="last_name")
    private String last_name;

    @OneToMany(
            targetEntity=Order.class,
            mappedBy="user",
            cascade=CascadeType.ALL,
            fetch=FetchType.LAZY
    )
    private List<Order> orders = new ArrayList<>();
}
