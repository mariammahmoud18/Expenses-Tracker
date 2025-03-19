package com.expensesTracker.app.controller;

import com.expensesTracker.app.DTO.rolesDTO;
import com.expensesTracker.app.DTOMappers.rolesMapper;
import com.expensesTracker.app.DTO.rolesDTO;
import com.expensesTracker.app.service.rolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tracker")
public class rolesController {
    @Autowired
    private rolesService service;

    @GetMapping("/roles")
    public List<rolesDTO> getAll() {
        return service.getAllRoles();
    }

    @GetMapping("/roles/{id}")
    public Optional<rolesDTO> getById(@PathVariable int id) {
        return service.getRoleById(id);
    }

    @DeleteMapping("/roles/{id}")
    public void deleteById(@PathVariable int id) {
        service.deleteRole(id);
    }

    @PostMapping("/roles")
    public rolesDTO createRole(@RequestBody rolesDTO roleDTO) {
        rolesDTO savedRole = service.saveRole(roleDTO);
        return savedRole;
    }
}
