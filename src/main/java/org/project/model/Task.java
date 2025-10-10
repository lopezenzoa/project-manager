package org.project.model;

import org.project.model.enums.Status;
import org.project.model.enums.Active;

public class Task {
    private final Integer ID;
    private Integer projectID;
    private String title;
    private String description;
    private TeamMember responsible;
    private String creationDate;
    private String deadline;
    private Status status;
    private Active active;

    public Task(Integer ID, Integer projectID, String title, String description, TeamMember responsible, String creationDate, String deadline, Status status, Active active) {
        this.ID = ID;
        this.projectID = projectID;
        this.title = title;
        this.description = description;
        this.responsible = responsible;
        this.creationDate = creationDate;
        this.deadline = deadline;
        this.status = status;
        this.active = active;
    }

    public Integer getID() {
        return ID;
    }

    public Integer getProjectID() {
        return projectID;
    }

    public void setProjectID(Integer projectID) {
        this.projectID = projectID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TeamMember getResponsible() {
        return responsible;
    }

    public void setResponsible(TeamMember responsible) {
        this.responsible = responsible;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Active getActive() {
        return active;
    }

    public void setActive(Active active) {
        this.active = active;
    }
}
