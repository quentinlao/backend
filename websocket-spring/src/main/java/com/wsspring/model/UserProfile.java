package com.wsspring.model;

import java.util.ArrayList;
import java.util.List;

public class UserProfile {
    private int id;
    private String email;
    private List<String> roles = new ArrayList<>();
    private String username;

    public UserProfile(int id, String email, List<String> roles, String username) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
