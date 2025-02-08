package com.expensetracker.expenseservice.deserializer;

import com.expensetracker.expenseservice.dto.ExpenseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.util.Map;

public class ExpenseDeserializer implements Deserializer<ExpenseDto> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {}

    @Override
    public ExpenseDto deserialize(String s, byte[] bytes) {
        ObjectMapper objectMapper = new ObjectMapper();
        ExpenseDto expenseDto = null;
        try {
            expenseDto = objectMapper.readValue(bytes,ExpenseDto.class);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return expenseDto;
    }

    @Override
    public void close() {

    }
}
