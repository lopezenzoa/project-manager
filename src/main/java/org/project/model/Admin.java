package org.project.model;

import org.project.model.enums.Active;

import java.util.HashSet;

public class Admin extends User {
    private HashSet<Leader> dependants;

    public Admin(Integer id, String name, String email, String password, Active active, HashSet<Leader> dependants) {
        super(id, name, email, password, active);
        this.dependants = dependants;
    }

    public HashSet<Leader> getDependants() {
        return dependants;
    }

    public void setDependants(HashSet<Leader> dependants) {
        this.dependants = dependants;
    }
}
