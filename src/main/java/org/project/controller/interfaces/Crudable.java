package org.project.controller.interfaces;

import org.project.model.User;

import java.io.IOException;

public interface Crudable<T> {
    void create() throws IOException;
    void read() throws IOException;
    void update(T newModel) throws IOException;
    void delete() throws IOException;
}
