package com.nagarro.nagp_assignment.dao;

import com.nagarro.nagp_assignment.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
}
