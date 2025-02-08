package com.expensetracker.expenseservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.sound.sampled.Port;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpenseDto {

    private String externalId;
    @JsonProperty(value = "amount")
    private String amount;
    @JsonProperty(value = "user_id")
    private String userId;
    @JsonProperty(value = "merchant")
    private String merchant;
    @JsonProperty(value = "currency")
    private String currency;
    @JsonProperty(value = "created_at")
    private Timestamp createdAt;

    public ExpenseDto(String serializedJson){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
            ExpenseDto expenseDto = objectMapper.readValue(serializedJson,ExpenseDto.class);
            this.externalId = expenseDto.externalId;
            this.amount = expenseDto.amount;
            this.userId = expenseDto.userId;
            this.merchant = expenseDto.merchant;
            this.currency = expenseDto.currency;
            this.createdAt = expenseDto.createdAt;
        }catch (Exception ex){
            throw new RuntimeException("Failed to deserialize : " +ex.getMessage());
        }
    }
}
