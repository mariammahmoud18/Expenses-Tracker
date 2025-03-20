package com.expensesTracker.app.service;

import com.expensesTracker.app.DTO.expensesDTO;
import com.expensesTracker.app.DTOMappers.expensesMapper;
import com.expensesTracker.app.entities.Category;
import com.expensesTracker.app.entities.Expenses;
import com.expensesTracker.app.entities.Users;
import com.expensesTracker.app.repository.categoryRepository;
import com.expensesTracker.app.repository.expensesRepository;
import com.expensesTracker.app.repository.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class expensesService {
    @Autowired
    private expensesRepository repo;

    @Autowired
    private usersRepository repo2;

    @Autowired
    private categoryRepository repo3;

    public List<expensesDTO> getAllExpenses(){
        return repo.findAll().stream().map(expensesMapper::toDTO).toList();
    }
    public Optional<expensesDTO> getExpenseById(int id){
        return repo.findById(id).map(expensesMapper::toDTO);
    }
    public expensesDTO saveExpense(expensesDTO expenseDTO){

        Optional<Users> optionalUser = repo2.findById(expenseDTO.getUserId());
        Optional<Category> optionalCategory = repo3.findById(expenseDTO.getCategoryId());
        Users user = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
        Category category = optionalCategory.orElseThrow(() -> new RuntimeException("Category not found"));
        Expenses expense = expensesMapper.toEntity(expenseDTO, user, category);

        Expenses savedExpense = repo.save(expense);

        return expensesMapper.toDTO(savedExpense);
    }
    public void deleteExpense(int id){
        repo.deleteById(id);
    }

    public Map<String, Double> getExpensesByCategoryPerUser(int userId) {
        List<Object[]> results = repo.getTotalExpensesByCategoryPerUser(userId);
        Map<String, Double> expensesByCategory = new HashMap<>();

        for (Object[] result : results) {
            String category = (String) result[0];
            Double totalAmount = (Double) result[1];
            expensesByCategory.put(category, totalAmount);
        }

        return expensesByCategory;
    }

    public Map<String, Double> getExpensesByCategory() {
        List<Object[]> results = repo.getTotalExpensesByCategory();
        Map<String, Double> expensesByCategory = new HashMap<>();

        for (Object[] result : results) {
            String category = (String) result[0];
            Double totalAmount = (Double) result[1];
            expensesByCategory.put(category, totalAmount);
        }

        return expensesByCategory;
    }





}
