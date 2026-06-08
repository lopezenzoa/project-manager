package org.project.builders;

import org.project.model.Task;
import org.project.model.TeamMember;
import org.project.model.enums.Status;

public class TaskBuilder {
    private Integer taskId;
    private Integer projectId;
    private String title;
    private String description;
    private TeamMember responsible;
    private String creationDate;
    private String deadline;
    private Status status;

    public TaskBuilder setTaskId(Integer taskId) {
        this.taskId = taskId;
        return this;
    }

    public TaskBuilder setProjectId(Integer projectId) {
        this.projectId = projectId;
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

    public Task build() {
        return new Task(taskId, projectId, title, description, responsible, creationDate, deadline, status);
    }
}
