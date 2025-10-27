package org.project.controller.impl;

import org.project.controller.IUserController;
import org.project.model.User;
import org.project.view.IUserView;

import java.sql.*;

public abstract class UserController<T extends User> implements IUserController {
    private T model;
    private final IUserView<T> view;

    public UserController(T model, IUserView<T> view) {
        this.model = model;
        this.view = view;
    }

    public T getModel() { return model; }

    private void setModel(T model) {
        this.model = model;
    }

    @Override
    public void create() {
    }

    @Override
    public void read() {
    }

    @Override
    public void update(User newModel) {
    }

    @Override
    public void delete() {
    }

    /**
     * Auxiliary method to add dependants for Admins and Leaders in the intermediate tables of the 'PM_Java' database.
     * */
    public void addDependants() {

    }

    public void print() {
        view.printUser(model);
    }

    public void printDependants() {
        view.printDependants(model);
    }
}
