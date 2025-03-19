package com.expensesTracker.app.repository;

import com.expensesTracker.app.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface categoryRepository extends JpaRepository<Category, Integer> {
}
