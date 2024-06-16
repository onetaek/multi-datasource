package com.example.multidatasource.api;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class MultiCreateRequest {

    private InnerMultiCreateRequest firstDb;
    private InnerMultiCreateRequest secondDb;
    private InnerMultiCreateRequest thirdDb;

    @Getter
    @Setter
    public static class InnerMultiCreateRequest {
        private Boolean throwError;
        private Integer sequence;
        private String stringColumn;
        private Integer integerColumn;
        private BigDecimal bigDecimalColumn;
        private LocalDateTime localDateTimeColumn;
    }



}
