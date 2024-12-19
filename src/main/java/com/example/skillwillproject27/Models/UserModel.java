package com.example.skillwillproject27.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.core.Authentication;

import java.util.UUID;


@Entity
@Table(name = "users")
public class UserModel   {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "username"/*,unique = true*/)
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;

    public UserModel()
    {

    }

    public UserModel(String username, String role)
    {
        this.username = username;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
