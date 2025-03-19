package com.expensesTracker.app.controller;

import com.expensesTracker.app.DTO.expensesDTO;
import com.expensesTracker.app.entities.Expenses;
import com.expensesTracker.app.entities.Roles;
import com.expensesTracker.app.service.expensesService;
import com.expensesTracker.app.service.rolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

}
