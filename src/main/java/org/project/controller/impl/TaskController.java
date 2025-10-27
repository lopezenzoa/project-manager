package org.project.controller.impl;

import org.project.controller.ITaskController;
import org.project.model.Task;
import org.project.model.enums.Active;
import org.project.view.ITaskView;

public class TaskController implements ITaskController {
    public Task model;
    public final ITaskView view;

    public TaskController(Task model, ITaskView view) {
        this.model = model;
        this.view = view;
    }

    public Task getModel() {
        return model;
    }

    public void setModel(Task model) {
        this.model = model;
    }

    @Override
    public void create() {
    }

    @Override
    public void read() {
    }

    @Override
    public void update(Task newModel) {
    }

    @Override
    public void delete() {
    }

    public void print() {
        view.printTask(model);
    }
}
