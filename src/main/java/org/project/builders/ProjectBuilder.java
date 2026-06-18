package org.project.builders;

import org.project.model.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

public class ProjectBuilder {
    private Integer id;
    private HashMap<Integer, User> team;
    private LinkedList<Task> tasks;
    private String name;
    private LocalDate creationDate;
    private LocalDate expectedDeadline;
    private Boolean isActive;

    public ProjectBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public ProjectBuilder setTeam(HashMap<Integer, User> team) {
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

    public ProjectBuilder setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public ProjectBuilder setExpectedDeadline(LocalDate expectedDeadline) {
        this.expectedDeadline = expectedDeadline;
        return this;
    }

    public ProjectBuilder setActive(Boolean active) {
        isActive = active;
        return this;
    }

    public Project build() {
        return new Project(id, team, tasks, name, creationDate, expectedDeadline, isActive);
    }
}
