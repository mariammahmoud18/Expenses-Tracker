package com.expensesTracker.app.DTOMappers;

import com.expensesTracker.app.DTO.rolesDTO;
import com.expensesTracker.app.entities.Roles;

public class rolesMapper {

    public static rolesDTO toDTO(Roles roles){
        return new rolesDTO(roles.getRoleId(), roles.getName());
    }

    public static Roles toEntity(rolesDTO rolesDTO){
        Roles role = new Roles();
        role.setRoleId(rolesDTO.getRoleID());
        role.setName(rolesDTO.getName());
        return role;
    }
}
