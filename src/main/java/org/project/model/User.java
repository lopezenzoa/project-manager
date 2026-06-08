package org.project.model;

import lombok.*;

@Getter
@Setter
@ToString
public class User {
    private Integer userId;
    private String name;
    private String email;
    private String password;
    private Boolean isActive;

    public User(Integer userId, String name, String email, String password, Boolean isActive) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
    }
}
