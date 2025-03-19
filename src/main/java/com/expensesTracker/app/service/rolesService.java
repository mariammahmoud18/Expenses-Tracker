package com.expensesTracker.app.service;

import com.expensesTracker.app.DTO.rolesDTO;
import com.expensesTracker.app.DTOMappers.rolesMapper;
import com.expensesTracker.app.entities.Roles;
import com.expensesTracker.app.repository.rolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class rolesService {
    @Autowired
    private rolesRepository repo;

    public List<rolesDTO> getAllRoles(){
        List<Roles> roles = repo.findAll();
        return roles.stream().map(rolesMapper::toDTO).collect(Collectors.toList());
    }
    public Optional<rolesDTO> getRoleById(int id){
        return repo.findById(id).map(rolesMapper::toDTO);
    }

    public rolesDTO saveRole(rolesDTO roleDTO) {
        if (roleDTO.getName() == null) {
            throw new RuntimeException("Role name cannot be null");
        }

        Roles role = rolesMapper.toEntity(roleDTO);
        Roles savedRole = repo.save(role);
        return rolesMapper.toDTO(savedRole);
    }

    public void deleteRole(int id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Role with ID " + id + " not found");
        }
        repo.deleteById(id);
    }


}
