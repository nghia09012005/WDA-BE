package com.example.WDA_backend.Dtos.Request;

public class MoneyExpRequest {
    private String username;
    private String object;
    private int amount;

    public MoneyExpRequest() {
    }
    public MoneyExpRequest(String username, String object, int amount) {
        this.username = username;
        this.object = object;
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
