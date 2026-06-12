package org.project.model;

import lombok.*;
import org.project.model.enums.Role;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {
    private final Integer id;
    private String name;
    private String email;
    private String password;
    private Boolean isActive;
    private Role role;
}
