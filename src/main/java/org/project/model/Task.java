package org.project.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.project.model.enums.Status;

@Getter
@Setter
@ToString
public class Task {
    private Integer taskId;
    private Integer projectId;
    private String title;
    private String description;
    private TeamMember responsible;
    private String creationDate;
    private String deadline;
    private Status status;

    public Task(Integer taskId, Integer projectId, String title, String description, TeamMember responsible, String creationDate, String deadline, Status status) {
        this.taskId = taskId;
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.responsible = responsible;
        this.creationDate = creationDate;
        this.deadline = deadline;
        this.status = status;
    }
}
