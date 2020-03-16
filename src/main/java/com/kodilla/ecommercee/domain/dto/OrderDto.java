package com.kodilla.ecommercee.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto implements Serializable {

    private Long id;

    private String number;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate orderDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate requiredDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate shippedDate;

    private String comments;

    private int status;

    private BigDecimal totalCost;

    private Long cartId;

    private Long userId;
}
