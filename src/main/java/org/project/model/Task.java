package org.project.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.project.model.enums.State;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Task {
    private Integer taskId;
    private Integer projectId;
    private String title;
    private String description;
    private User responsible;
    private LocalDate creationDate;
    private LocalDate expectedDeadline;
    private State state;
}
