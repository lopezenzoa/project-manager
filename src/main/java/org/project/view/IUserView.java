package org.project.view;

import org.project.model.User;

public interface IUserView<T extends User> {
    void printUser(T user);
    void printDependants(T dependants);
    void printOngoingProjects(T user);
}
