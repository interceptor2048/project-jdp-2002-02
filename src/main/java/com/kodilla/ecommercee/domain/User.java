package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="status")
    private String status;

    @Column(name="userKey")
    private int userKey;

    public User() {
        this.userKey = Integer.parseInt(RandomStringUtils.random(5, false, true));
    }

    @OneToMany(
            targetEntity= Order.class,
            mappedBy="user",
            cascade=CascadeType.ALL,
            fetch=FetchType.LAZY
    )
    private List<Order> orders = new ArrayList<>();
}
