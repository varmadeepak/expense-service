package com.expensetracker.expenseservice.service;

import com.expensetracker.expenseservice.dto.ExpenseDto;
import com.expensetracker.expenseservice.entity.Expense;
import com.expensetracker.expenseservice.repository.ExpenseRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ExpenseService {
    private ExpenseRepository expenseRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }

    public boolean createExpense(ExpenseDto expenseDto){
        System.out.println("ExpenseDTO : " + expenseDto);
        setCurrency(expenseDto);
        try{
            Expense expense = Expense.builder()
                    .userId(expenseDto.getUserId())
                    .externalId(expenseDto.getExternalId())
                    .currency(expenseDto.getCurrency())
                    .merchant(expenseDto.getMerchant())
                    .amount(expenseDto.getAmount())
                    .createdAt(expenseDto.getCreatedAt())
                    .build();
            expenseRepository.save(expense);
            return true;
        }catch (Exception ex){
            System.out.println("Issue");
            return false;
        }
    }
    public boolean updateExpense(ExpenseDto expenseDto){
        Optional<Expense> expense = expenseRepository.findByUserIdAndExternalId(expenseDto.getUserId(),expenseDto.getExternalId());
        if(expense.isEmpty()) return false;
        Expense updatedExpense = expense.get();
        updatedExpense.setCurrency(Strings.isNotBlank(expenseDto.getCurrency()) ? expenseDto.getCurrency() : expense.get().getCurrency());
        updatedExpense.setMerchant(Strings.isNotBlank(expenseDto.getMerchant()) ? expenseDto.getMerchant() : expense.get().getMerchant());
        updatedExpense.setAmount(expenseDto.getAmount());
        expenseRepository.save(updatedExpense);
        return true;
    }
    public List<ExpenseDto> getExpenses(String userId){
        List<Expense> expenseList = expenseRepository.findByUserId(userId);
        return objectMapper.convertValue(expenseList, new TypeReference<List<ExpenseDto>>() {});

    }
    private void setCurrency(ExpenseDto expenseDto) {
        if(Objects.isNull(expenseDto.getCurrency())) {
            expenseDto.setCurrency("INR");
        }
    }
}
