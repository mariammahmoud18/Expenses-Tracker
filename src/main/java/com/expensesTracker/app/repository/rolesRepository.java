package com.expensesTracker.app.repository;

import com.expensesTracker.app.entities.Category;
import com.expensesTracker.app.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;


public interface rolesRepository extends JpaRepository<Roles, Integer> {
    public Roles findByName(String name);

}
