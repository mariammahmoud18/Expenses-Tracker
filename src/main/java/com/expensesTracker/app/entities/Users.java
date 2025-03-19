package com.expensesTracker.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private int userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "userId", fetch = FetchType.EAGER)
    private List<Expenses> expensesId = new ArrayList<>();

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles", // Name of the join table
            joinColumns = @JoinColumn(name = "userid"), // Foreign key for Users
            inverseJoinColumns = @JoinColumn(name = "roleid") // Foreign key for Roles
             )
    private List<Roles> roles = new ArrayList<>();

    public Users(){}

    public Users(int id, String username, String email, String password, List<Expenses> expensesId, List<Roles> rolesId) {
        this.userId = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.expensesId = expensesId;
        this.roles = rolesId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Expenses> getExpensesId() {
        return expensesId;
    }

    public void setExpensesId(List<Expenses> expensesId) {
        this.expensesId = expensesId;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
