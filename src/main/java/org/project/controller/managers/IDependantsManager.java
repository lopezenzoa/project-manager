package org.project.controller.managers;

import org.project.model.User;

import java.util.HashSet;

public interface IDependantsManager<T extends User> {
    boolean addDependant(T dependant);
    boolean removeDependant(T dependant);
    boolean removeDependant(Integer ID);
    T searchDependantByID(Integer ID);
    HashSet<Integer> getDependantsIDs();
}
