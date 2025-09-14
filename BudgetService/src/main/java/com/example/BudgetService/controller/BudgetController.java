package com.example.BudgetService.controller;

import com.example.BudgetService.model.Budget;
import com.example.BudgetService.service.BudgetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    // 1️⃣ Create budget
    @PostMapping
    public ResponseEntity<Budget> createBudget(@RequestBody Budget budget) {
        return ResponseEntity.ok(budgetService.createBudget(budget));
    }

    // 2️⃣ Update budget
    @PutMapping("/{id}")
    public ResponseEntity<Budget> updateBudget(@PathVariable Long id, @RequestBody Budget budget) {
        return ResponseEntity.ok(budgetService.updateBudget(id, budget));
    }

    // 3️⃣ Get all budgets
    @GetMapping
    public ResponseEntity<List<Budget>> getAllBudgets() {
        return ResponseEntity.ok(budgetService.getAllBudgets());
    }

    // 4️⃣ Get budget by userId and month
    @GetMapping("/user/{userId}/{month}")
    public ResponseEntity<Budget> getBudgetByUserAndMonth(@PathVariable Long userId,
                                                          @PathVariable String month) {
        // Convert "YYYY-MM" to LocalDate (first day of month)
        LocalDate monthDate = LocalDate.parse(month + "-01");
        return budgetService.getBudgetByUserAndMonth(userId, monthDate)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
