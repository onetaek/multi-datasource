package com.example.multidatasource.thirddb.repository;

import com.example.multidatasource.thirddb.entity.ThirdTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThirdRepository extends JpaRepository<ThirdTable, Long> {
}
