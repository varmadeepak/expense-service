package com.expensetracker.expenseservice.consumer;


import com.expensetracker.expenseservice.dto.ExpenseDto;
import com.expensetracker.expenseservice.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ExpenseConsumer {
    private ExpenseService expenseService;

    @Autowired
    ExpenseConsumer(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}",groupId = "${spring.kafka.consumer.group-id}")
    public void listen(ExpenseDto expenseDto){
        try{
             expenseService.createExpense(expenseDto);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
