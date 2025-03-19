package com.expensesTracker.app.service;

import com.expensesTracker.app.DTO.categoryDTO;
import com.expensesTracker.app.DTOMappers.categoryMapper;
import com.expensesTracker.app.entities.Category;
import com.expensesTracker.app.repository.categoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class categoryService {
    @Autowired
    private categoryRepository repo;

    public List<categoryDTO> getAllCategories(){
        return repo.findAll().stream().map(categoryMapper::toDTO).collect(Collectors.toList());
    }
    public Optional<categoryDTO> getCategoryById(int id){
        return repo.findById(id).map(categoryMapper::toDTO);
    }
    public categoryDTO saveCategory(categoryDTO category){
        if (category.getName() == null) {
            throw new RuntimeException("Category name cannot be null");
        }
        Category category1 = categoryMapper.toEntity(category);
        return categoryMapper.toDTO(repo.save(category1));
    }
    public void deleteCatgeory(int id){
        repo.deleteById(id);
    }

}
