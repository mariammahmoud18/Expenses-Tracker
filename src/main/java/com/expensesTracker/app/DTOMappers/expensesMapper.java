package com.expensesTracker.app.DTOMappers;

import com.expensesTracker.app.DTO.expensesDTO;
import com.expensesTracker.app.entities.Category;
import com.expensesTracker.app.entities.Expenses;
import com.expensesTracker.app.entities.Users;
import com.expensesTracker.app.repository.categoryRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;

public class expensesMapper {

    public static expensesDTO toDTO(Expenses expense){
        return new expensesDTO(
                expense.getExpenseId(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getDate(),
                expense.getCategoryId().getName()
        );
    }

    public static Expenses toEntity(expensesDTO dto, Users user, Category category){
        Expenses expense = new Expenses();
        expense.setDescription(dto.getDescription());
        expense.setAmount(dto.getAmount());
        expense.setDate(dto.getDate());
        expense.setUserId(user);
        expense.setCategoryId(category);
        return expense;
    }
}
