package com.nagarro.nagp_assignment.controllers;

import com.nagarro.nagp_assignment.dao.RecordRepository;
import com.nagarro.nagp_assignment.entity.Record;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/records")
public class RecordController {
  @Autowired
  private RecordRepository recordRepository;

  @GetMapping("/greetings")
  public String greetings() {
    log.info("Request Received for greetings");
    return "Application is up";
  }

  @PostMapping("/fibonacci")
  public Integer fibonacci(@RequestParam int pos) {
    log.info("Request Received for fibonacci");
    if (pos <= 1) {
      return pos;
    }
    int a = 0, b = 1, c;
    for (int i = 2; i <= pos; i++) {
      c = a + b;
      a = b;
      b = c;
    }
    return b;
  }
  @GetMapping
  public List<Record> getAllRecords() {
    log.info("Request Received for getting records");
    return recordRepository.findAll();
  }

  @PostMapping
  public Record createRecord(@RequestBody Record record) {
    log.info("Request Received for creating record "+record);
    return recordRepository.save(record);
  }
}

