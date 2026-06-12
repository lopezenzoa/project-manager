package org.project.builders;

import org.project.model.User;
import org.project.model.enums.Role;

public class UserBuilder {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Boolean isActive;
    private Role role;

    public UserBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setActive(Boolean active) {
        isActive = active;
        return this;
    }

    public UserBuilder setRole(Role role) {
        this.role = role;
        return this;
    }

    public User build() {
        return new User(id, name, email, password, isActive, role);
    }
}
