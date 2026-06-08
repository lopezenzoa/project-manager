package org.project.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.project.model.enums.Role;

import java.util.HashSet;

@Getter
@Setter
@ToString
public class TeamMember extends User {
    private HashSet<Integer> ongoingProjectsIds;
    private Role role;

    public TeamMember(Integer userId, String name, String email, String password, Boolean isActive, HashSet<Integer> ongoingProjectsIds, Role role) {
        super(userId, name, email, password, isActive);
        this.ongoingProjectsIds = ongoingProjectsIds;
        this.role = role;
    }
}
