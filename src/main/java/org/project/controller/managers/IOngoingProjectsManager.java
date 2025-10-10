package org.project.controller.managers;

import org.project.model.Project;

public interface IOngoingProjectsManager {
    boolean addOngoingProject(Integer projectID);
    boolean removeOngoingProject(Project project);
}
