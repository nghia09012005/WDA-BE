package com.example.WDA_backend.Dtos.Request;

public class ItemRequest {
    private String username;
    private String item;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public ItemRequest() {

    }

    public ItemRequest(String username, String item) {
        this.username = username;
        this.item = item;
    }

}
