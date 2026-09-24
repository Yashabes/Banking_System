package com.banking.account_service.controller;

import com.banking.account_service.dto.AccountResponse;
import com.banking.account_service.dto.CreateAccountRequest;
import com.banking.account_service.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/accounts")
@Slf4j
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(
            @Valid @RequestBody CreateAccountRequest request
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(request));
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountResponse> getAccount(
            @PathVariable String accountNumber
    ){
        return ResponseEntity.ok(accountService.getAccount(accountNumber));
    }

    @GetMapping("/{accountNumber}/balance")
    public ResponseEntity<BigDecimal> getBalance(
            @PathVariable String accountNumber
    ){
        return ResponseEntity.ok(accountService.getBalance(accountNumber));
    }

    @PutMapping("/{accountNumber}/block")
    public ResponseEntity<String> blockAccount(@PathVariable String accountNumber){
        accountService.blockAccount(accountNumber);
        return ResponseEntity.ok("Account blocked Successfully");
    }

    @PutMapping("/{accountNumber}/deduct")
    public ResponseEntity<String> deductBalance(
            @PathVariable String accountNumber,
            @RequestParam BigDecimal amount
    ){
        accountService.deductBalance(accountNumber,amount);
        return ResponseEntity.ok("Balance deducted Successfully");
    }

    /**
     * CALLED BY TRANSACTION IN TWO SCENARIOS
     * 1.FRAUD DETECTED -> REFUND SENDER
     * 2.TRANSACTION COMPLETED -> CREDIT RECEIVED
     */

    @PutMapping("/{accountNumber}/credit")
    public ResponseEntity<String> creditBalance(
            @PathVariable String accountNumber,
            @RequestParam BigDecimal amount
    ){
        accountService.creditBalance(accountNumber,amount);
        return ResponseEntity.ok("Balance credited Successfully");
    }



}
