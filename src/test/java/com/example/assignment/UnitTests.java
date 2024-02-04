package com.example.assignment;

import com.example.assignment.serivces.TransactionService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnitTests {

    private final TransactionService transactionService = new TransactionService();

    @Test
    void testCalculatePointsWithPositiveAmount() {
        double amount = 100;

        int result = transactionService.calculatePoints(amount);

        assertEquals(50, result);
    }

    @Test
    void testCalculatePointsWithZeroAmount() {
        double amount = 0;

        int result = transactionService.calculatePoints(amount);

        assertEquals(0, result);
    }

    @Test
    void testCalculatePointsWithNegativeAmount() {
        double amount = -50;
        int result = transactionService.calculatePoints(amount);

        assertEquals(0, result);
    }
}