package org.project.controller.managers.impl;

import org.project.controller.managers.IDependantsManager;
import org.project.model.User;

import java.util.HashSet;

public class DependantsManager implements IDependantsManager<User> {
    private final HashSet<User> dependants;

    public DependantsManager() {
        this.dependants = new HashSet<>();
    }

    /**
     * Adds a new dependant to the dependants' list of the user.
     * @param dependant is the new dependant.
     * @return a boolean value depending on if the dependant could be added or not.
     * */
    @Override
    public boolean addDependant(User dependant) {
        if (!dependants.contains(dependant))
            return dependants.add(dependant);
        return false;
    }

    /**
     * Removes a dependant from the dependants' list of the user.
     * @param dependant is the dependant that want to delete.
     * @return a boolean value depending on if the dependant could be removed or not.
     * */
    @Override
    public boolean removeDependant(User dependant) {
        return dependants.remove(dependant);
    }

    /**
     * Removes a dependant from the dependants' list of the user given its ID.
     * @param ID is the dependant that want to delete.
     * @return a boolean value depending on if the dependant could be removed or not.
     * */
    @Override
    public boolean removeDependant(Integer ID) {
        User toDelete = searchDependantByID(ID);

        if (toDelete != null)
            return dependants.remove(toDelete);
        else
            return false;
    }

    /**
     * Searches a dependant in the dependant's list of the user.
     * @param ID is the ID of the dependant that want to search.
     * @return a T object if the ID corresponds with a dependant or null otherwise.
     * */
    @Override
    public User searchDependantByID(Integer ID) {
        for (User dependant : dependants)
            if (dependant.getID().equals(ID))
                return dependant;
        return null;
    }

    /**
     * Returns a collection of dependants IDs.
     * @return a HashSet made of dependants IDs.
     * */
    @Override
    public HashSet<Integer> getDependantsIDs() {
        HashSet<Integer> dependantsIDs = new HashSet<>();

        for (User dependant : dependants)
            dependantsIDs.add(dependant.getID());

        return dependantsIDs;
    }
}
