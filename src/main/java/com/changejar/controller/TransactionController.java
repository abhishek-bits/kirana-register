package com.changejar.controller;

import com.changejar.dto.TransactionDTO;
import com.changejar.dto.BaseRequestDTO;
import com.changejar.entity.Response;
import com.changejar.exception.ResourceNotFoundException;
import com.changejar.service.TransactionService;
import com.changejar.util.LocalDateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/txn")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/save")
    ResponseEntity<Response> saveTransaction(@RequestBody TransactionDTO transactionDTO) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTimeUtils.getLocalDateTime(System.currentTimeMillis()).toString())
                        .data(Map.of("transaction", transactionService.save(transactionDTO)))
                        .message("Transaction recorded successfully.")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build());
    }

    @PostMapping
    ResponseEntity<Response> getAllTransactions(@RequestBody BaseRequestDTO baseRequestDTO) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTimeUtils.getLocalDateTime(System.currentTimeMillis()).toString())
                        .data(Map.of(
                                "transactions",
                                transactionService.getTransactions(baseRequestDTO)))
                        .message("Transactions retrieved successfully.")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }
}
