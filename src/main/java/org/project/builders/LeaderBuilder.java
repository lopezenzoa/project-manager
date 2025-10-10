package org.project.builders;

import org.project.model.Leader;
import org.project.model.TeamMember;
import org.project.model.enums.Active;

import java.util.HashSet;

public class LeaderBuilder {
    private Integer ID;
    private String name;
    private String email;
    private String password;
    private Active active;
    private HashSet<Integer> ongoingProjects;
    private HashSet<TeamMember> dependants;

    public LeaderBuilder setID(Integer ID) {
        this.ID = ID;
        return this;
    }

    public LeaderBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public LeaderBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public LeaderBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public LeaderBuilder setActive(Active active) {
        this.active = active;
        return this;
    }

    public LeaderBuilder setOngoingProjects(HashSet<Integer> ongoingProjects) {
        this.ongoingProjects = ongoingProjects;
        return this;
    }

    public LeaderBuilder setDependants(HashSet<TeamMember> dependants) {
        this.dependants = dependants;
        return this;
    }

    public Leader build() {
        return new Leader(ID, name, email, password, active, ongoingProjects, dependants);
    }
}
