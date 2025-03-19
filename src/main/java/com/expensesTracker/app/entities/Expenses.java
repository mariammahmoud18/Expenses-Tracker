package com.expensesTracker.app.entities;

import jakarta.persistence.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Date;

@Entity
@Table(name = "expenses")
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expenseid")
    private int expenseId;

    @Column(name = "amount", nullable = false)
    private float amount;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryid")
    private Category categoryId;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "userid")
    private Users userId;

    public Expenses(){};

    public Expenses(int expenseId,Category categoryId, Users userId, String description, Date date, float amount) {
        this.expenseId = expenseId;
        this.categoryId = categoryId;
        this.userId = userId;
        this.description = description;
        this.date = date;
        this.amount = amount;
    }

    @PrePersist
    public void setData(){
    if(date == null)
        date = new Date();
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    @Override
    public String toString() {
        return "Expenses{" +
                "expenseId=" + expenseId +
                ", amount=" + amount +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                ", userId=" + userId +
                '}';
    }
}
