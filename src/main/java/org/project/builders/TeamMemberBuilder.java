package org.project.builders;

import org.project.model.TeamMember;
import org.project.model.enums.Role;

import java.util.HashSet;

public class TeamMemberBuilder {
    private Integer teamMemberId;
    private String name;
    private String email;
    private String password;
    private Boolean isActive;
    private HashSet<Integer> ongoingProjects;
    private Role role;

    public TeamMemberBuilder setTeamMemberId(Integer teamMemberId) {
        this.teamMemberId = teamMemberId;
        return this;
    }

    public TeamMemberBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public TeamMemberBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public TeamMemberBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public TeamMemberBuilder setIsActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public TeamMemberBuilder setOngoingProjects(HashSet<Integer> ongoingProjects) {
        this.ongoingProjects = ongoingProjects;
        return this;
    }

    public TeamMemberBuilder setRole(Role role) {
        this.role = role;
        return this;
    }

    public TeamMember build() {
        return new TeamMember(teamMemberId, name, email, password, isActive, ongoingProjects, role);
    }
}
