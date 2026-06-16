package org.project.model;

import lombok.*;
import org.project.model.enums.State;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
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
