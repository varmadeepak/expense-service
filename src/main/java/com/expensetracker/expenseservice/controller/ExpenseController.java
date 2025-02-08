package com.expensetracker.expenseservice.controller;

import com.expensetracker.expenseservice.dto.ExpenseDto;
import com.expensetracker.expenseservice.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpenseController {
    private final ExpenseService expenseService;

    @Autowired
    ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @PostMapping("/expense/v1/addExpense")
    public ResponseEntity<Boolean> addExpenses(@RequestBody ExpenseDto expenseDto){
        try {
            expenseService.createExpense(expenseDto);
            return new ResponseEntity<>(true,HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/expense/v1/{userId}/getExpense")
    public ResponseEntity<List<ExpenseDto>> getAllExpenses(@PathVariable String userId){
        try {
            List<ExpenseDto> expenses = expenseService.getExpenses(userId);
            return new ResponseEntity<>(expenses, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
