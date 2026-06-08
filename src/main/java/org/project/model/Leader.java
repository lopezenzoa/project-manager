package org.project.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;

@Getter
@Setter
@ToString
public class Leader extends User {
    private HashSet<Integer> ongoingProjects;
    private HashSet<TeamMember> dependants;

    public Leader(Integer userId, String name, String email, String password, Boolean isActive, HashSet<Integer> ongoingProjects, HashSet<TeamMember> dependants) {
        super(userId, name, email, password, isActive);
        this.ongoingProjects = ongoingProjects;
        this.dependants = dependants;
    }
}
