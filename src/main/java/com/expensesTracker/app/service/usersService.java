package com.expensesTracker.app.service;

import com.expensesTracker.app.DTO.expensesDTO;
import com.expensesTracker.app.DTO.rolesDTO;
import com.expensesTracker.app.DTO.usersDTO;
import com.expensesTracker.app.DTOMappers.expensesMapper;
import com.expensesTracker.app.DTOMappers.rolesMapper;
import com.expensesTracker.app.DTOMappers.usersMapper;
import com.expensesTracker.app.entities.Roles;
import com.expensesTracker.app.entities.Users;
import com.expensesTracker.app.repository.expensesRepository;
import com.expensesTracker.app.repository.rolesRepository;
import com.expensesTracker.app.repository.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class usersService {
    @Autowired
    private usersRepository repo;
    @Autowired
    private rolesRepository rolesRepo;
    @Autowired
    private expensesRepository expensesRepo;

    public List<usersDTO> getAllUsers(){
        return repo.findAll().stream().map(usersMapper::toDTO).toList();
    }
    public Optional<usersDTO> getUserById(int id){
        return repo.findById(id).map(usersMapper::toDTO);
    }
    public usersDTO saveUser(usersDTO usersDTO){
       Users user = usersMapper.toEntity(usersDTO, rolesRepo);
       Users savedUser = repo.save(user);
        return usersMapper.toDTO(savedUser);
    }
    public void deleteUser(int id){
        repo.deleteById(id);
    }
    public List<usersDTO> getUserByUsername(String username){
        return repo.findByUsernameContainingIgnoreCase(username).stream().map(usersMapper::toDTO).toList();
    }
    public List<usersDTO> getUsersByEmail(String email){
        return repo.findByEmailContainingIgnoreCase(email).stream().map(usersMapper::toDTO).toList();
    }
    public List<rolesDTO> getUserRoles(int id){
        return repo.findRolesByUserId(id).stream().map(rolesMapper::toDTO).toList();
    }

    public List<expensesDTO> getUserExpenses(int id) {
        Optional<Users> optionalUser = repo.findById(id);
        Users user = optionalUser.orElseThrow();
        return user.getExpensesId().stream().map(expensesMapper::toDTO).toList();


    }


    public usersDTO updateUser(int userId, usersDTO dto) {
        Users user = repo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if (dto.getUsername() != null) user.setUsername(dto.getUsername());
        if (dto.getPassword() != null) user.setPassword(dto.getPassword());

        if (dto.getRolesID() != null && !dto.getRolesID().isEmpty()) {
            List<Roles> roles = dto.getRolesID().stream()
                    .map(rolesRepo::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
            user.setRoles(roles);
        }

        repo.save(user);
        return usersMapper.toDTO(user);  // Convert back to DTO
    }
}
