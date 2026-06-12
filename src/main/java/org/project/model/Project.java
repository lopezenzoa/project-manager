package org.project.model;

import lombok.*;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Project {
    private Integer id;
    private HashMap<Integer, User> team;
    private LinkedList<Task> tasks;
    private String name;
    private LocalDate creationDate;
    private LocalDate expectedDeadline;
    private Boolean isActive;
}
