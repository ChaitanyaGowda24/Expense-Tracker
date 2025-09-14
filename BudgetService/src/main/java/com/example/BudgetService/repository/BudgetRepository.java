package com.example.BudgetService.repository;

import com.example.BudgetService.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget,Long> {

    // Find budget by user and month
    Optional<Budget> findByUserIdAndMonth(Long userId, LocalDate month);
}
