package com.example.ExpenseService.service;

import com.example.ExpenseService.model.Expense;
import com.example.ExpenseService.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private final ExpenseRepository expenseRepository;

    @Autowired
    private RestTemplate restTemplate;

    public ExpenseService(ExpenseRepository expenseRepository,RestTemplate restTemplate) {
        this.expenseRepository = expenseRepository;
        this.restTemplate = restTemplate;
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

    public Expense addExpense(Expense expense) {
        String url = "http://localhost:8083/categories/" + expense.getCategoryId();

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Invalid categoryId: " + expense.getCategoryId());
            }
        } catch (Exception e) {
            throw new RuntimeException("Category not found: " + expense.getCategoryId());
        }

        return expenseRepository.save(expense);
    }

}
