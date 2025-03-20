package com.expensesTracker.app.controller;

import com.expensesTracker.app.DTO.expensesDTO;
import com.expensesTracker.app.entities.Expenses;
import com.expensesTracker.app.entities.Roles;
import com.expensesTracker.app.service.expensesService;
import com.expensesTracker.app.service.rolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/tracker")
public class expensesController {
    @Autowired
    private expensesService service;

    @GetMapping("/expenses")
    public List<expensesDTO> getAll(){
        return service.getAllExpenses();
    }

    @GetMapping("/expenses/{id}")
    public Optional<expensesDTO> getById(@PathVariable int id){
        return service.getExpenseById(id);
    }

    @DeleteMapping("/expenses/{id}")
    public void deleteById(@PathVariable int id){
        service.deleteExpense(id);
    }

    @PostMapping("/expenses")
    public expensesDTO createExpense(@RequestBody expensesDTO expensesDTO){
        return service.saveExpense(expensesDTO);
    }

    @GetMapping("/expenses/{userId}/users")
    public ResponseEntity<Map<String, Double>> getExpensesByCategoryPerUser(@PathVariable int userId) {
        return ResponseEntity.ok(service.getExpensesByCategoryPerUser(userId));
    }

    @GetMapping("/expenses/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Double>> getExpensesByCategory() {
        return ResponseEntity.ok(service.getExpensesByCategory());
    }

}
