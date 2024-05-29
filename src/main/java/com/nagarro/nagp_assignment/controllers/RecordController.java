package com.nagarro.nagp_assignment.controllers;

import com.nagarro.nagp_assignment.dao.RecordRepository;
import com.nagarro.nagp_assignment.entity.Record;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/records")
public class RecordController {
  @Autowired
  private RecordRepository recordRepository;

  @GetMapping
  public List<Record> getAllRecords() {
    return recordRepository.findAll();
  }

  @PostMapping
  public Record createRecord(@RequestBody Record record) {
    return recordRepository.save(record);
  }
}

