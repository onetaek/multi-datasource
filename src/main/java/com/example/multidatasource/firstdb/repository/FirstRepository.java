package com.example.multidatasource.firstdb.repository;

import com.example.multidatasource.firstdb.entity.FirstTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirstRepository extends JpaRepository<FirstTable, Long> {
}
