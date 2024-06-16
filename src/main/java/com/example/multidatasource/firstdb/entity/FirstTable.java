package com.example.multidatasource.firstdb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FirstTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String stringColumn;
    private Integer integerColumn;
    private BigDecimal bigDecimalColumn;
    private LocalDateTime localDateTimeColumn;
}
