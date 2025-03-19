package com.expensesTracker.app.repository;

import com.expensesTracker.app.entities.Category;
import com.expensesTracker.app.entities.Expenses;
import com.expensesTracker.app.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface expensesRepository extends JpaRepository<Expenses, Integer> {


}
