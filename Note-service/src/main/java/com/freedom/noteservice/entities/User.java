package com.freedom.noteservice.entities;

import com.freedom.noteservice.enums.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @Column(columnDefinition = "varchar(255) UNIQUE NOT NULL CHECK (email <> '')")
    private String email;
    @Column(columnDefinition = "varchar(255) NOT NULL CHECK (password <> '' AND LENGTH(password) >= 8)")
    private String password;
    @Column
    private LocalDateTime createdTime;
    @Column
    private LocalDateTime updatedTime;
    @Column(columnDefinition = "varchar(255) DEFAULT 'USER'")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        createdTime = LocalDateTime.now();
        updatedTime = LocalDateTime.now();
        role = Role.USER;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
