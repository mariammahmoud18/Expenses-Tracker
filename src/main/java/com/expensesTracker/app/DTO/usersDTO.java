package com.expensesTracker.app.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public class usersDTO {
    private int userId;
    private String username;
    @Email(message = "Enter Correct Email format. ex: ***@***.com")
    private String email;
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character"
    )
    private String password;
    private List<expensesDTO> expenses;
    private List<rolesDTO> roles;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Integer> rolesID;

    public usersDTO(int userId, List<rolesDTO> roles, List<expensesDTO> expenses, String email, String username, String password) {
        this.userId = userId;
        this.roles = roles;
        this.expenses = expenses;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<rolesDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<rolesDTO> roles) {
        this.roles = roles;
    }

    public List<expensesDTO> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<expensesDTO> expenses) {
        this.expenses = expenses;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Integer> getRolesID() {
        return rolesID;
    }

    public void setRolesID(List<Integer> rolesID) {
        this.rolesID = rolesID;
    }


}
