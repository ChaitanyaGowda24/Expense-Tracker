package com.example.BudgetService.service;

import com.example.BudgetService.model.Budget;
import com.example.BudgetService.repository.BudgetRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;

    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    // 1️⃣ Create or save a budget
    public Budget createBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    // 2️⃣ Update existing budget
    public Budget updateBudget(Long id, Budget budgetDetails) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget not found with id: " + id));

        budget.setAmount(budgetDetails.getAmount());
        budget.setMonth(budgetDetails.getMonth());
        budget.setUserId(budgetDetails.getUserId());

        return budgetRepository.save(budget);
    }

    // 3️⃣ Get all budgets
    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    // 4️⃣ Get budget by userId and month
    public Optional<Budget> getBudgetByUserAndMonth(Long userId, LocalDate month) {
        return budgetRepository.findByUserIdAndMonth(userId, month);
    }

}
