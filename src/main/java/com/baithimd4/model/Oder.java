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
import java.time.LocalDateTime;

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
    private LocalDateTime ngayMua;

    private Long tongSoluong;

    @Min(value = 10000)
    private BigDecimal tongTien;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Long getTongTien() {
        return this.product.getPrice() * tongSoluong;
    }
}
