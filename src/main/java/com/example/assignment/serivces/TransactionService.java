package com.example.assignment.serivces;

import com.example.assignment.domain.Transaction;
import com.example.assignment.domain.User;
import com.example.assignment.dtos.TransactionDTO;
import com.example.assignment.repositories.TransactionRepository;
import com.example.assignment.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;
    public int processTransaction(TransactionDTO transactionDTO) {
        double amountSpent = transactionDTO.getAmount();

        User user = userRepository.findById(transactionDTO.getUserID())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: " + transactionDTO.getUserID()));


        int calculatedPoints = calculatePoints(amountSpent);
        int currentPoints = user.getPoints();

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.getAmount());

        user.setPoints(currentPoints + calculatedPoints);

        transaction.setUser(user);

        userRepository.save(user);


        return calculatedPoints;
    }

    public int calculatePoints(double amountSpent) {
        int points = 0;
        int over100 = (int) Math.floor(amountSpent - 100);
        int over50 = (int) Math.floor(amountSpent - 50);

        if(over100 > 0) points += over100 * 2;
        if(over50 > 0) points += over50;

        return points;
    }

    private Iterable<Transaction> findAll(){
        return transactionRepository.findAll();
    }
}
