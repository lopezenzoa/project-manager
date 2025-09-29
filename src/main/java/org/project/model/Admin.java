package org.project.model;

import org.project.model.enums.Visibility;

import java.util.HashSet;

public class Admin extends User {
    private HashSet<Leader> dependants;

    public Admin() { super(); }

    public Admin(Integer ID, String name, String email, String password, Visibility visibility, HashSet<Leader> dependants) {
        super(ID, name, email, password, visibility);
        this.dependants = dependants;
    }

    public HashSet<Leader> getDependants() {
        return dependants;
    }

    public void setDependants(HashSet<Leader> dependants) {
        this.dependants = dependants;
    }
}
