package com.example.khoanguyen.demofirebase;

public class Contact {
    private String name;
    private String phoneNumber;
    private String avatarUrl;

    public Contact(){

    }

    public Contact(String name, String phoneNumber, String avatarUrl) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setColor(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
