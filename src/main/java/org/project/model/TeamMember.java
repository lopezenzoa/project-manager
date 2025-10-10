package org.project.model;


import org.project.model.enums.Role;
import org.project.model.enums.Active;

import java.util.HashSet;

public class TeamMember extends User {
    private HashSet<Integer> ongoingProjects;
    private Role role;

    public TeamMember(Integer id, String name, String email, String password, Active active, HashSet<Integer> ongoingProjects, Role role) {
        super(id, name, email, password, active);
        this.ongoingProjects = ongoingProjects;
        this.role = role;
    }

    public HashSet<Integer> getOngoingProjects() {
        return ongoingProjects;
    }

    public void setOngoingProjects(HashSet<Integer> ongoingProjects) {
        this.ongoingProjects = ongoingProjects;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
