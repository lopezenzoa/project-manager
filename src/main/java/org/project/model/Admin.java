package org.project.model;

import lombok.*;

import java.util.HashSet;

@Getter
@Setter
@ToString
public class Admin extends User {
    private HashSet<Leader> dependants;

    public Admin(Integer userId, String name, String email, String password, Boolean isActive, HashSet<Leader> dependants) {
        super(userId, name, email, password, isActive);
        this.dependants = dependants;
    }
}
