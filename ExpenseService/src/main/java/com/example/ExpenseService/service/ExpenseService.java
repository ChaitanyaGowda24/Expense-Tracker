package com.example.ExpenseService.service;

import com.example.ExpenseService.model.Expense;
import com.example.ExpenseService.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    // Create an expense
    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    // Get all expenses
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // Get expenses by user
    public List<Expense> getExpensesByUser(Long userId) {
        return expenseRepository.findByUserId(userId);
    }

    // Get single expense
    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    //Update an existing expense
    public Expense updateExpense(Long id, Expense expenseDetails) {
        return expenseRepository.findById(id)
                .map(existingExpense -> {
                    existingExpense.setAmount(expenseDetails.getAmount());
                    existingExpense.setDate(expenseDetails.getDate());
                    existingExpense.setDescription(expenseDetails.getDescription());
                    existingExpense.setCategoryId(expenseDetails.getCategoryId());
                    return expenseRepository.save(existingExpense);
                })
                .orElseThrow(() -> new RuntimeException("Expense not found with id " + id));
    }

    // Delete expense
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}
