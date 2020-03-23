package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @Column(name="username")
    private String username;

    @Column(name="status")
    private int status;

    @Column(name="userKey")
    private String userKey;

    public User() {
        this.userKey = RandomStringUtils.random(5, false, true);
    }

    @OneToMany(
            targetEntity= Order.class,
            mappedBy="user",
            fetch=FetchType.LAZY
    )
    private List<Order> orders = new ArrayList<>();
}
