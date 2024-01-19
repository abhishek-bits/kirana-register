package com.changejar.controller;

import com.changejar.dto.BaseRequestDTO;
import com.changejar.dto.CustomerAccountDTO;
import com.changejar.entity.Response;
import com.changejar.service.CustomerAccountService;
import com.changejar.util.LocalDateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/account")
public class CustomerAccountController {

    @Autowired
    private CustomerAccountService customerAccountService;

    @PostMapping("/save")
    ResponseEntity<Response> customerOnboard(@RequestBody CustomerAccountDTO customerAccountDTO) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTimeUtils.getLocalDateTime(System.currentTimeMillis()).toString())
                        .data(Map.of("userAccount", customerAccountService.save(customerAccountDTO)))
                        .message("User Account created successfully.")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build());
    }

    @PostMapping
    ResponseEntity<Response> getAllCustomerAccounts(@RequestBody BaseRequestDTO baseRequestDTO) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTimeUtils.getLocalDateTime(System.currentTimeMillis()).toString())
                        .data(Map.of("userAccounts", customerAccountService.getUserAccounts(baseRequestDTO)))
                        .message("User Accounts retrieved successfully.")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }
}
