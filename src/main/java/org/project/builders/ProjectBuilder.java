package org.project.builders;

import org.project.model.*;
import org.project.model.enums.Status;

import java.util.HashMap;
import java.util.LinkedList;

public class ProjectBuilder {
    private Integer projectId;
    private Admin admin;
    private Leader leader;
    private HashMap<Integer, TeamMember> team;
    private LinkedList<Task> tasks;
    private String name;
    private String creationDate;
    private String deadline;
    private Status status;
    private Boolean isActive;

    public ProjectBuilder setProjectId(Integer projectId) {
        this.projectId = projectId;
        return this;
    }

    public ProjectBuilder setAdmin(Admin admin) {
        this.admin = admin;
        return this;
    }

    public ProjectBuilder setLeader(Leader leader) {
        this.leader = leader;
        return this;
    }

    public ProjectBuilder setTeam(HashMap<Integer, TeamMember> team) {
        this.team = team;
        return this;
    }

    public ProjectBuilder setTasks(LinkedList<Task> tasks) {
        this.tasks = tasks;
        return this;
    }

    public ProjectBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ProjectBuilder setCreationDate(String creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public ProjectBuilder setDeadline(String deadline) {
        this.deadline = deadline;
        return this;
    }

    public ProjectBuilder setStatus(Status status) {
        this.status = status;
        return this;
    }

    public ProjectBuilder setIsActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public Project build() {
        return new Project(projectId, admin, leader, team, tasks, name, creationDate, deadline, status, isActive);
    }
}
