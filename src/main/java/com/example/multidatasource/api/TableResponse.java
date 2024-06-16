package com.example.multidatasource.api;

import com.example.multidatasource.firstdb.entity.FirstTable;
import com.example.multidatasource.seconddb.entity.SecondTable;
import com.example.multidatasource.thirddb.entity.ThirdTable;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TableResponse {

    private Long id;
    private String stringColumn;
    private Integer integerColumn;
    private BigDecimal bigDecimalColumn;
    private LocalDateTime localDateTimeColumn;

    public static TableResponse fromFirstEntity(FirstTable firstTable) {
        if (firstTable == null) return null;
        return TableResponse.builder()
                .id(firstTable.getId())
                .stringColumn(firstTable.getStringColumn())
                .integerColumn(firstTable.getIntegerColumn())
                .bigDecimalColumn(firstTable.getBigDecimalColumn())
                .localDateTimeColumn(firstTable.getLocalDateTimeColumn())
                .build();
    }

    public static TableResponse fromSecondEntity(SecondTable secondTable) {
        if (secondTable == null) return null;
        return TableResponse.builder()
                .id(secondTable.getId())
                .stringColumn(secondTable.getStringColumn())
                .integerColumn(secondTable.getIntegerColumn())
                .bigDecimalColumn(secondTable.getBigDecimalColumn())
                .localDateTimeColumn(secondTable.getLocalDateTimeColumn())
                .build();
    }

    public static TableResponse fromThirdEntity(ThirdTable thirdTable) {
        if (thirdTable == null) return null;
        return TableResponse.builder()
                .id(thirdTable.getId())
                .stringColumn(thirdTable.getStringColumn())
                .integerColumn(thirdTable.getIntegerColumn())
                .bigDecimalColumn(thirdTable.getBigDecimalColumn())
                .localDateTimeColumn(thirdTable.getLocalDateTimeColumn())
                .build();
    }

}
