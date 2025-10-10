package org.project.controller;

import org.project.model.Project;
import org.project.model.Task;

public interface IProjectController extends CRUD<Project> {
    void createTask(Task task);
    void createTeam();
}
