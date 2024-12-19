package com.example.skillwillproject27.Request;

import com.example.skillwillproject27.Enums.Roles;
import lombok.Getter;
import lombok.Setter;


public class RegistrationRequest {

    private String username;
    private String password;
    private Roles role;

    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
