package org.project.model;


import org.project.model.enums.Role;
import org.project.model.enums.Visibility;

import java.util.HashSet;

public class TeamMember extends User {
    private HashSet<Integer> ongoingProjects;
    private Role role;

    public TeamMember() { super(); }

    public TeamMember(Integer ID, String name, String email, String password, Visibility visibility, HashSet<Integer> ongoingProjects, Role role) {
        super(ID, name, email, password, visibility);
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
