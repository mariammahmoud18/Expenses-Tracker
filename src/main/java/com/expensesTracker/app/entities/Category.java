package com.expensesTracker.app.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name = "catgory")
public class Category {
    @Id
    @Column(name = "categoryid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany (mappedBy = "categoryId")
    private List<Expenses> expenses = new ArrayList<>();

    public Category(){};

    public Category(int id,String name) {
        this.categoryId = id;
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                '}';
    }
}
