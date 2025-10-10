package org.project.builders;

import org.project.model.Task;
import org.project.model.TeamMember;
import org.project.model.enums.Active;
import org.project.model.enums.Status;

public class TaskBuilder {
    private Integer ID;
    private Integer projectID;
    private String title;
    private String description;
    private TeamMember responsible;
    private String creationDate;
    private String deadline;
    private Status status;
    private Active active;

    public TaskBuilder setID(Integer ID) {
        this.ID = ID;
        return this;
    }

    public TaskBuilder setProjectID(Integer projectID) {
        this.projectID = projectID;
        return this;
    }

    public TaskBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public TaskBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public TaskBuilder setResponsible(TeamMember responsible) {
        this.responsible = responsible;
        return this;
    }

    public TaskBuilder setCreationDate(String creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public TaskBuilder setDeadline(String deadline) {
        this.deadline = deadline;
        return this;
    }

    public TaskBuilder setStatus(Status status) {
        this.status = status;
        return this;
    }

    public TaskBuilder setActive(Active active) {
        this.active = active;
        return this;
    }

    public Task build() {
        return new Task(ID, projectID, title, description, responsible, creationDate, deadline, status, active);
    }
}
