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
    private boolean congchieng;
    private boolean co;
    private boolean thu;
    private boolean tranh;
    private boolean quanho;
    private boolean trongdong;

    // Constructor mặc định cần thiết cho JPA
    public UserStats() {
    }

    public UserStats(Users user, int exp, double money) {
        this.user = user;
        this.exp = exp;
        this.money = money;
        this.congchieng = false;
        this.co = false;
        this.thu = false;
        this.tranh = false;
        this.quanho = false;
        this.trongdong = false;
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

    public boolean isCongchieng() {
        return congchieng;
    }

    public void setCongchieng(boolean congchieng) {
        this.congchieng = congchieng;
    }

    public boolean isCo() {
        return co;
    }

    public void setCo(boolean co) {
        this.co = co;
    }

    public boolean isThu() {
        return thu;
    }

    public void setThu(boolean thu) {
        this.thu = thu;
    }

    public boolean isTranh() {
        return tranh;
    }

    public void setTranh(boolean tranh) {
        this.tranh = tranh;
    }

    public boolean isQuanho() {
        return quanho;
    }

    public void setQuanho(boolean quanho) {
        this.quanho = quanho;
    }

    public boolean isTrongdong() {
        return trongdong;
    }

    public void setTrongdong(boolean trongdong) {
        this.trongdong = trongdong;
    }
}
