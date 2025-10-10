package org.project.controller;

import org.project.model.User;

public interface IUserController extends CRUD<User> {
    void addDependants();
    void print();
    void printDependants();
}
