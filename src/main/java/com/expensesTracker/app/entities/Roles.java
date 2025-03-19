package com.expensesTracker.app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleid")
    private int roleId;

    @Column(name="role_name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private List<Users> users = new ArrayList<>();

    public Roles(){};

    public Roles(String name, List<Users> usersId) {
        this.name = name;
        this.users = usersId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Users> getUsersId() {
        return users;
    }

    public void setUsersId(List<Users> usersId) {
        this.users = usersId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "userId=" + roleId +
                ", name='" + name + '\''+
                '}';
    }
}
