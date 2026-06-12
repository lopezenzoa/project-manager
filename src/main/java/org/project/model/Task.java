package org.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.project.model.enums.State;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Task {
    private Integer id;
    private Integer projectId;
    private String title;
    private String description;
    private User responsible;
    private LocalDate creationDate;
    private LocalDate expectedDeadline;
    private State state;
}
