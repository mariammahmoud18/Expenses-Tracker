package com.expensesTracker.app.repository;

import com.expensesTracker.app.entities.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface expensesRepository extends JpaRepository<Expenses, Integer> {
    @Query("SELECT e.categoryId.name AS category, SUM(e.amount) AS totalAmount FROM Expenses e WHERE e.userId.id = :userId GROUP BY e.categoryId")
    List<Object[]> getTotalExpensesByCategoryPerUser(@Param("userId") int userId);

    @Query("SELECT e.categoryId.name AS category, SUM(e.amount) AS totalAmount FROM Expenses e GROUP BY e.categoryId")
    List<Object[]> getTotalExpensesByCategory();

}
