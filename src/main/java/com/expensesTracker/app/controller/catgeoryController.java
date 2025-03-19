package com.expensesTracker.app.controller;

import com.expensesTracker.app.DTO.categoryDTO;
import com.expensesTracker.app.entities.Category;
import com.expensesTracker.app.service.categoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tracker")
public class catgeoryController {
    @Autowired
    private categoryService service;

    @GetMapping("/category")
    public List<categoryDTO> getAll(){
        return service.getAllCategories();
    }

    @GetMapping("/category/{id}")
    public Optional<categoryDTO> getById(@PathVariable int id){
        return service.getCategoryById(id);
    }

    @DeleteMapping("/category/{id}")
    public void deleteById(@PathVariable int id){
        service.deleteCatgeory(id);
    }

    @PostMapping("/category")
    public categoryDTO createCatgeory(@RequestBody categoryDTO categoryDTO){
        return service.saveCategory(categoryDTO);
    }




}
