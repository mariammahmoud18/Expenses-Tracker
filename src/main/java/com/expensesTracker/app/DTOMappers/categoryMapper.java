package com.expensesTracker.app.DTOMappers;

import com.expensesTracker.app.DTO.categoryDTO;
import com.expensesTracker.app.entities.Category;

public class categoryMapper {

    public static categoryDTO toDTO(Category category){
        return new categoryDTO(category.getCategoryId(), category.getName());
    }

    public static Category toEntity(categoryDTO categoryDTO){
        return new Category(categoryDTO.getCategoryID(),categoryDTO.getName());
    }
}
