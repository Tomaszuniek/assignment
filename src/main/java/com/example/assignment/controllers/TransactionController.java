package com.example.assignment.controllers;

import com.example.assignment.dtos.TransactionDTO;
import com.example.assignment.serivces.TransactionService;
import com.example.assignment.serivces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class TransactionController {

    private TransactionService transactionService;
    private UserService userService;

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/processTransaction")
    public ResponseEntity<String> processTransaction(@RequestBody TransactionDTO transactionDTO) {
        int pointsAdded = transactionService.processTransaction(transactionDTO);

        return new ResponseEntity<>(pointsAdded + " points adder for user " + transactionDTO.getUserID(), HttpStatus.OK);
    }

    @PostMapping("/processBatch")
    public ResponseEntity<Map<Long, Integer>> processTransactions(@RequestBody List<TransactionDTO> transactionDTOs) {
        return new ResponseEntity<>(transactionService.processTransactions(transactionDTOs), HttpStatus.OK);
    }

    @GetMapping("/throwError")
    public ResponseEntity<String> throwError() throws IllegalAccessException {
        throw new IllegalAccessException();
    }
}
