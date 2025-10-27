package org.project.controller.impl;

import org.project.controller.IProjectController;
import org.project.model.Project;
import org.project.model.Task;
import org.project.view.IProjectView;

public class ProjectController implements IProjectController {
    private Project model;
    private final IProjectView view;

    public ProjectController(Project model, IProjectView view) {
        this.model = model;
        this.view = view;
    }

    public Project getModel() {
        return model;
    }

    public void setModel(Project model) {
        this.model = model;
    }

    @Override
    public void create() {
    }

    @Override
    public void read() {
    }

    @Override
    public void update(Project newModel) {
    }

    @Override
    public void delete() {
    }

    /**
     * Creates a task into 'PM_Java' database only if the task didn't exist before.
     * @param task is the object ot type Task which is going to be created.
     * */
    @Override
    public void createTask(Task task) {
    }

    /**
     * Creates an initial team into 'PM_Java' database for the intermediate table 'Team'.
     * */
    @Override
    public void createTeam() {
    }

    public void print() {
        view.printProject(model);
    }

    public void printPendingTasks() {
        view.printPendingTasks(model.getTasks());
    }

    public void printFinishedTasks() {
        view.printFinishedTasks(model.getTasks());
    }

    public void printTeam() {
        view.printTeamMembers(model.getTeam());
    }
}
