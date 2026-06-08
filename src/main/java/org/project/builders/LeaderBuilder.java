package org.project.builders;

import org.project.model.Leader;
import org.project.model.TeamMember;

import java.util.HashSet;

public class LeaderBuilder {
    private Integer leaderId;
    private String name;
    private String email;
    private String password;
    private Boolean isActive;
    private HashSet<Integer> ongoingProjects;
    private HashSet<TeamMember> dependants;

    public LeaderBuilder setLeaderId(Integer leaderId) {
        this.leaderId = leaderId;
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

    public LeaderBuilder setIsActive(Boolean isActive) {
        this.isActive = isActive;
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
        return new Leader(leaderId, name, email, password, isActive, ongoingProjects, dependants);
    }
}
