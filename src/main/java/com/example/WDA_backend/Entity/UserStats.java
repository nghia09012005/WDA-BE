package com.example.WDA_backend.Entity;
import jakarta.persistence.*;
@Entity
public class UserStats {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

    private int exp;  // Kinh nghiệm
    private double money;  // Tiền

    // Constructor mặc định cần thiết cho JPA
    public UserStats() {
    }

    public UserStats(Users user, int exp, double money) {
        this.user = user;
        this.exp = exp;
        this.money = money;
    }

    // Getters và Setters
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
