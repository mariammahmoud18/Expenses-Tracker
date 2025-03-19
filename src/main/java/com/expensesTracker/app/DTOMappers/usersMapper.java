package com.expensesTracker.app.DTOMappers;

import com.expensesTracker.app.DTO.expensesDTO;
import com.expensesTracker.app.DTO.rolesDTO;
import com.expensesTracker.app.DTO.usersDTO;
import com.expensesTracker.app.entities.Roles;
import com.expensesTracker.app.entities.Users;
import com.expensesTracker.app.repository.rolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;


import java.util.List;
import java.util.stream.Collectors;

public class usersMapper {


    public static usersDTO toDTO(Users user){
        List<expensesDTO> expenseDTOs = user.getExpensesId().stream().map(expense -> new expensesDTO(
                        expense.getExpenseId(),
                        expense.getDescription(),
                        expense.getAmount(),
                        expense.getDate(),
                        expense.getCategoryId().getName()
                ))
                .collect(Collectors.toList());

        List<rolesDTO> roleNames = user.getRoles().stream()
                .map(role -> new rolesDTO(role.getRoleId(), role.getName()))
                .collect(Collectors.toList());

        return new usersDTO(user.getUserId(),
                roleNames,
                expenseDTOs
                ,user.getEmail(),
                user.getUsername(),
                user.getPassword());
    }

    public static Users toEntity(usersDTO dto, rolesRepository rolesRepository){
        Users user = new Users();
        user.setUserId(dto.getUserId());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        if (dto.getRolesID() != null) {
            List<Roles> roles = dto.getRolesID().stream()
                    .map(rolesRepository::findById) // Correct way to call repository
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
            user.setRoles(roles);
        }


        return user;
    }
}
