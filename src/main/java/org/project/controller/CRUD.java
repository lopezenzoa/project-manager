package org.project.controller;

public interface CRUD<T> {
    void create();
    void read();
    void update(T newModel);
    void delete();
}
