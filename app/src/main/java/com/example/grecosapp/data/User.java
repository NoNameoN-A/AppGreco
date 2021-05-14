package com.example.grecosapp.data;

public class User {

    String email;
    String photo;
    String listId;
    String uid;

    public User(String email, String photo, String listId, String uid) {
        this.email = email;
        this.photo = photo;
        this.listId = listId;
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                ", listId='" + listId + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
