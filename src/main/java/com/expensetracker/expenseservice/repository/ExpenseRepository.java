package com.expensetracker.expenseservice.repository;

import com.expensetracker.expenseservice.entity.Expense;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface ExpenseRepository extends CrudRepository<Expense,Long> {
    List<Expense> findByUserId(String userId);
    List<Expense> findByUserIdAndCreatedAtBetween(String userId, Timestamp startTime,Timestamp endTime);
    Optional<Expense> findByUserIdAndExternalId(String userId,String externalId);
}
