package com.expensesTracker.app.controller;

import com.expensesTracker.app.DTO.expensesDTO;
import com.expensesTracker.app.DTO.rolesDTO;
import com.expensesTracker.app.DTO.usersDTO;
import com.expensesTracker.app.entities.Roles;
import com.expensesTracker.app.entities.Users;
import com.expensesTracker.app.service.rolesService;
import com.expensesTracker.app.service.usersService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tracker")
public class usersController {
    @Autowired
    private usersService service;

    //no reuqestbody is supported in GET
    @GetMapping("/users")
    public List<usersDTO> getAll(@RequestParam(required = false)  String username, @RequestParam(required = false)  String email){
        if(username!=null)
                return service.getUserByUsername(username);

        if(email!=null)
                return service.getUsersByEmail(email);
        return service.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public Optional<usersDTO> getById(@PathVariable int id){
        return service.getUserById(id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteById(@PathVariable int id){
        service.deleteUser(id);
    }

    @PostMapping("/users")
    public usersDTO createUser(@Valid @RequestBody usersDTO user){
        return service.saveUser(user);
    }

   /* @GetMapping("/users")
    public Users getByUsername(@RequestBody Users user){
        return service.getUserByUsername(user.getUsername());
    }

    @GetMapping("/users")
    public Users getByEmail(@RequestBody Users user){
        return service.getUserByUsername(user.getEmail());
    }*/

    @GetMapping("/users/{id}/roles")
    public List<rolesDTO> getUserRoles(@PathVariable int id){
        return service.getUserRoles(id);
    }

    @GetMapping("/users/{id}/expenses")
    public List<expensesDTO> getUserExpenses(@PathVariable int id){
        return service.getUserExpenses(id);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<usersDTO> updateUser(@PathVariable int id, @RequestBody @Valid usersDTO dto) {
        usersDTO updatedUser = service.updateUser(id, dto);
        return ResponseEntity.ok(updatedUser);
    }




}
