package org.project.model;

import org.project.model.enums.Visibility;

public class User {
    private Integer ID;
    private String name;
    private String email;
    private String password;
    private Visibility visibility;

    public User() {}

    public User(Integer ID, String name, String email, String password, Visibility visibility) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.visibility = visibility;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }
}
