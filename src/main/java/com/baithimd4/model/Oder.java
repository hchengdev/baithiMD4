package com.baithimd4.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "oders")
@AllArgsConstructor
@NoArgsConstructor
public class Oder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Future
    private LocalDate ngayMua;

    private Long tongSoluong;

    @Min(value = 10000)
    private BigDecimal tongTien;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public void calculateTotal() {
        if (product != null && tongSoluong != null) {
            this.tongTien = BigDecimal.valueOf(product.getPrice()).multiply(BigDecimal.valueOf(tongSoluong));
        }
    }
}
