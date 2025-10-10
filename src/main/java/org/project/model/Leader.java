package org.project.model;


import org.project.model.enums.Active;

import java.util.HashSet;

public class Leader extends User {
    private HashSet<Integer> ongoingProjects;
    private HashSet<TeamMember> dependants;

    public Leader(Integer id, String name, String email, String password, Active active, HashSet<Integer> ongoingProjects, HashSet<TeamMember> dependants) {
        super(id, name, email, password, active);
        this.ongoingProjects = ongoingProjects;
        this.dependants = dependants;
    }

    public HashSet<Integer> getOngoingProjects() {
        return ongoingProjects;
    }

    public void setOngoingProjects(HashSet<Integer> ongoingProjects) {
        this.ongoingProjects = ongoingProjects;
    }

    public HashSet<TeamMember> getDependants() {
        return dependants;
    }

    public void setDependants(HashSet<TeamMember> dependants) {
        this.dependants = dependants;
    }
}
