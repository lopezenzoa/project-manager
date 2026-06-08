package org.project.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.project.model.enums.Status;

import java.util.*;

@Getter
@Setter
@ToString
public class Project {
    private Integer projectId;
    private Admin admin;
    private Leader leader;
    private HashMap<Integer, TeamMember> team;
    private LinkedList<Task> tasks;
    private String name;
    private String creationDate;
    private String deadline;
    private Boolean isActive;

    public Project(Integer projectId, Admin admin, Leader leader, HashMap<Integer, TeamMember> team, LinkedList<Task> tasks, String name, String creationDate, String deadline, Boolean isActive) {
        this.projectId = projectId;
        this.admin = admin;
        this.leader = leader;
        this.team = team;
        this.tasks = tasks;
        this.name = name;
        this.creationDate = creationDate;
        this.deadline = deadline;
        this.isActive = isActive;
    }
}
