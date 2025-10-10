package org.project.view;

import org.project.model.Project;
import org.project.model.Task;
import org.project.model.TeamMember;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;

public interface IProjectView {
    void printProject(Project project);
    void printTeamMembers(HashMap<UUID, TeamMember> team);
    void printPendingTasks(LinkedList<Task> tasks);
    void printFinishedTasks(LinkedList<Task> tasks);
}
