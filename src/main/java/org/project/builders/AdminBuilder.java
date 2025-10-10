package org.project.builders;

import org.project.model.Admin;
import org.project.model.Leader;
import org.project.model.enums.Active;

import java.util.HashSet;

public class AdminBuilder {
    private Integer ID;
    private String name;
    private String email;
    private String password;
    private Active active;
    private HashSet<Leader> dependants;

    public AdminBuilder setID(Integer ID) {
        this.ID = ID;
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

    public AdminBuilder setActive(Active active) {
        this.active = active;
        return this;
    }

    public AdminBuilder setDependants(HashSet<Leader> dependants) {
        this.dependants = dependants;
        return this;
    }

    public Admin build() {
        return new Admin(ID, name, email, password, active, dependants);
    }
}
