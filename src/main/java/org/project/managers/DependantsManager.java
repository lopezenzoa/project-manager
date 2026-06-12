package org.project.managers;

import lombok.AllArgsConstructor;
import org.project.model.User;

import java.util.HashSet;

@AllArgsConstructor
public class DependantsManager {
    private final HashSet<User> dependants;

    /**
     * Adds a new dependant to the dependants list of the user.
     * @param dependant is the new dependant.
     * @return a boolean value depending on if the dependant could be added or not.
     * */
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
    public boolean removeDependant(User dependant) {
        return dependants.remove(dependant);
    }

    /**
     * Removes a dependant from the dependants' list of the user given its ID.
     * @param ID is the dependant that want to delete.
     * @return a boolean value depending on if the dependant could be removed or not.
     * */
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
    public User searchDependantByID(Integer ID) {
        for (User dependant : dependants)
            if (dependant.getId().equals(ID))
                return dependant;
        return null;
    }

    /**
     * Returns a collection of dependants IDs.
     * @return a HashSet made of dependants IDs.
     * */
    public HashSet<Integer> getDependantsIDs() {
        HashSet<Integer> dependantsIDs = new HashSet<>();

        for (User dependant : dependants)
            dependantsIDs.add(dependant.getId());

        return dependantsIDs;
    }
}
