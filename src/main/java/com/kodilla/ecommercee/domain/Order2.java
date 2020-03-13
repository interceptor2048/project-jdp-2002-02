package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="orders2")
public class Order2 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name="status")
    private int status;

    @Column(name="orderDate")
    private LocalDate orderDate;

    @Column(name="requiredDate")
    private LocalDate requiredDate;

    @Column(name="shippedDate")
    private LocalDate shippedDate;

    @Column(name="comments")
    private String comments;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="cartId")
    private Cart2 cart;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User2 user;

    public Order2(int status, LocalDate orderDate, LocalDate requiredDate, LocalDate shippedDate, String comments) {
        this.number = RandomStringUtils.random(10, false, true);
        this.status = status;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.comments = comments;
    }
}
