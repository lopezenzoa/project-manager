package org.project.builders;

import org.project.model.Admin;
import org.project.model.Leader;

import java.util.HashSet;

public class AdminBuilder {
    private Integer adminId;
    private String name;
    private String email;
    private String password;
    private Boolean isActive;
    private HashSet<Leader> dependants;

    public AdminBuilder setAdminId(Integer adminId) {
        this.adminId = adminId;
        return this;
    }

    public AdminBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public AdminBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public AdminBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public AdminBuilder setIsActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public AdminBuilder setDependants(HashSet<Leader> dependants) {
        this.dependants = dependants;
        return this;
    }

    public Admin build() {
        return new Admin(adminId, name, email, password, isActive, dependants);
    }
}
