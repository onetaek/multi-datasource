package com.example.multidatasource.seconddb.repository;

import com.example.multidatasource.firstdb.entity.FirstTable;
import com.example.multidatasource.seconddb.entity.SecondTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecondRepository extends JpaRepository<SecondTable, Long> {
}
