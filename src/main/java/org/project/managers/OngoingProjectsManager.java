package org.project.managers;

import lombok.AllArgsConstructor;
import org.project.model.Project;

import java.util.HashSet;

@AllArgsConstructor
public class OngoingProjectsManager {
    private final HashSet<Integer> ongoingProjects;

    /**
     * Adds a new project in the ongoing projects' list of the leader.
     * @param projectID is the ID of the project that want to add.
     * @return a boolean value depending on if the project could be added or not.
     * */
    public boolean addOngoingProject(Integer projectID) {
        if (!ongoingProjects.contains(projectID))
            return ongoingProjects.add(projectID);
        return false;
    }

    /**
     * Removes a project from the ongoing projects' list of the leader.
     * @param project is the ID of the project that want to remove.
     * @return a boolean value depending on if the project could be added or not.
     * */
    public boolean removeOngoingProject(Project project) {
        return ongoingProjects.remove(project.getId());
    }
}
