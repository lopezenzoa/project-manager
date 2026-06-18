package org.project.builders;

import org.project.model.Task;
import org.project.model.User;
import org.project.model.enums.State;

import java.time.LocalDate;

public class TaskBuilder {
    private Integer id;
    private Integer projectId;
    private String title;
    private String description;
    private User responsible;
    private LocalDate creationDate;
    private LocalDate expectedDeadline;
    private State state;

    public TaskBuilder setId(Integer id) {
        this.id = id;
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

    public TaskBuilder setResponsible(User responsible) {
        this.responsible = responsible;
        return this;
    }

    public TaskBuilder setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public TaskBuilder setExpectedDeadline(LocalDate expectedDeadline) {
        this.expectedDeadline = expectedDeadline;
        return this;
    }

    public TaskBuilder setState(State state) {
        this.state = state;
        return this;
    }

    public Task build() {
        return new Task(id, projectId, title, description, responsible, creationDate, expectedDeadline, state);
    }
}
