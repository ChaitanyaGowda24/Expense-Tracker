package com.example.ExpenseService.repository;

import com.example.ExpenseService.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    // Find all expenses for a specific user
    List<Expense> findByUserId(Long userId);
}
