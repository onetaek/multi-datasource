package com.example.multidatasource.api;

import com.example.multidatasource.firstdb.entity.FirstTable;
import com.example.multidatasource.firstdb.repository.FirstRepository;
import com.example.multidatasource.seconddb.entity.SecondTable;
import com.example.multidatasource.seconddb.repository.SecondRepository;
import com.example.multidatasource.thirddb.entity.ThirdTable;
import com.example.multidatasource.thirddb.repository.ThirdRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TestService {

    private final FirstRepository firstRepository;
    private final SecondRepository secondRepository;
    private final ThirdRepository thirdRepository;

    public Map<String, List<TableResponse>> findAllDbData() {
        List<TableResponse> firstTableList = firstRepository.findAll().stream().map(TableResponse::fromFirstEntity).toList();
        List<TableResponse> secondTableList = secondRepository.findAll().stream().map(TableResponse::fromSecondEntity).toList();
        List<TableResponse> thirdTableList = thirdRepository.findAll().stream().map(TableResponse::fromThirdEntity).toList();
        return Map.of(
                "firstDb", firstTableList,
                "secondDb", secondTableList,
                "thirdDb", thirdTableList
        );
    }

    public void createMulti(MultiCreateRequest testCreateRequest) {
        MultiCreateRequest.InnerMultiCreateRequest firstDbCreateRequest = testCreateRequest.getFirstDb();
        MultiCreateRequest.InnerMultiCreateRequest secondDbCreateRequest = testCreateRequest.getSecondDb();
        MultiCreateRequest.InnerMultiCreateRequest thirdDbCreateRequest = testCreateRequest.getThirdDb();

        Map<Integer, String> sequenceMap = Map.of(
                firstDbCreateRequest.getSequence(), "firstDb",
                secondDbCreateRequest.getSequence(), "secondDb",
                thirdDbCreateRequest.getSequence(), "thirdDb"
        );

        for (int i = 1; i <= 3 ; i++) {
            String dbName = sequenceMap.get(i);
            log.info("sequence: {}, dbName: {}", i, dbName);
            if ("firstDb".equals(dbName)) {
                FirstTable createTarget = getFirstTable(firstDbCreateRequest.getStringColumn(), firstDbCreateRequest.getIntegerColumn(), firstDbCreateRequest.getBigDecimalColumn(), firstDbCreateRequest.getLocalDateTimeColumn());
                firstRepository.save(createTarget);
                if (firstDbCreateRequest.getThrowError()) throw new RuntimeException("first DB exception");
            } else if ("secondDb".equals(dbName)) {
                SecondTable createTarget = getSecondTable(secondDbCreateRequest.getStringColumn(), secondDbCreateRequest.getIntegerColumn(), secondDbCreateRequest.getBigDecimalColumn(), secondDbCreateRequest.getLocalDateTimeColumn());
                secondRepository.save(createTarget);
                if (secondDbCreateRequest.getThrowError()) throw new RuntimeException("second DB exception");
            } else if ("thirdDb".equals(dbName)) {
                ThirdTable createTarget = getThirdTable(thirdDbCreateRequest.getStringColumn(), thirdDbCreateRequest.getIntegerColumn(), thirdDbCreateRequest.getBigDecimalColumn(), thirdDbCreateRequest.getLocalDateTimeColumn());
                thirdRepository.save(createTarget);
                if (thirdDbCreateRequest.getThrowError()) throw new RuntimeException("third DB exception");
            }
        }
    }

    private FirstTable getFirstTable(String stringColumn, Integer integerColumn, BigDecimal bigDecimalColumn, LocalDateTime localDateTimeColumn) {
        return FirstTable.builder()
                .stringColumn(stringColumn)
                .integerColumn(integerColumn)
                .bigDecimalColumn(bigDecimalColumn)
                .localDateTimeColumn(localDateTimeColumn)
                .build();
    }

    private SecondTable getSecondTable(String stringColumn, Integer integerColumn, BigDecimal bigDecimalColumn, LocalDateTime localDateTimeColumn) {
        return SecondTable.builder()
                .stringColumn(stringColumn)
                .integerColumn(integerColumn)
                .bigDecimalColumn(bigDecimalColumn)
                .localDateTimeColumn(localDateTimeColumn)
                .build();
    }

    private ThirdTable getThirdTable(String stringColumn, Integer integerColumn, BigDecimal bigDecimalColumn, LocalDateTime localDateTimeColumn) {
        return ThirdTable.builder()
                .stringColumn(stringColumn)
                .integerColumn(integerColumn)
                .bigDecimalColumn(bigDecimalColumn)
                .localDateTimeColumn(localDateTimeColumn)
                .build();
    }

    public void deleteAll() {
        firstRepository.deleteAll();
        secondRepository.deleteAll();
        thirdRepository.deleteAll();
    }
}
