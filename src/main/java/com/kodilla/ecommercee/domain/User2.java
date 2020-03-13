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
@NoArgsConstructor
@Builder
@Entity
@Table(name="users2")
public class User2 {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="status")
    private String status;

    @Column(name="userKey")
    private int userKey;

    public User2(String name, String status) {
        this.name = name;
        this.status = status;
        this.userKey = Integer.parseInt(RandomStringUtils.random(5, false, true));
    }

    @OneToMany(
            targetEntity= Order2.class,
            mappedBy="user",
            cascade=CascadeType.ALL,
            fetch=FetchType.LAZY
    )
    private List<Order2> orders = new ArrayList<>();
}
