package com.expensesTracker.app.DTO;

import com.expensesTracker.app.entities.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class expensesDTO {
    private int id;
    private String description;
    private float amount;
    private Date date;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int userId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int categoryId;
    private String catgeoryName;

    public expensesDTO() {}

    //for post methods
    public expensesDTO(String description, float amount, int userId, int categoryId) {
        this.description = description;
        this.amount = amount;
        this.userId = userId;
        this.categoryId = categoryId;
    }

    //for get methods
    public expensesDTO(int id, String description, float amount, Date date, String categoryName) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.catgeoryName = categoryName;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public float getAmount() { return amount; }
    public void setAmount(float amount) { this.amount = amount; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCatgeoryName() {
        return catgeoryName;
    }

    public void setCatgeoryName(String catgeoryName) {
        this.catgeoryName = catgeoryName;
    }
}
