package org.project.view.impl;

import org.project.model.Project;
import org.project.model.Task;
import org.project.model.TeamMember;
import org.project.model.enums.Status;
import org.project.view.IProjectView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public  class ProjectView implements IProjectView {
    @Override
    public void printProject(Project project) {
        System.out.println(project);
    }

    @Override
    public void printTeamMembers(HashMap<Integer, TeamMember> team) {
        System.out.println("Team");
        for (Map.Entry<Integer, TeamMember> entry : team.entrySet())
            System.out.println("  " + entry.getValue().getName() + " - " + entry.getValue().getRole() + "\n");
    }

    @Override
    public void printPendingTasks(LinkedList<Task> tasks) {
        System.out.println("Pending Tasks");
        for (Task task : tasks)
            if (task.getStatus().equals(Status.PENDING))
                System.out.println(task);
    }

    @Override
    public void printFinishedTasks(LinkedList<Task> tasks) {
        System.out.println("Finished Tasks");
        for (Task task : tasks)
            if (task.getStatus().equals(Status.PENDING))
                System.out.println(task);
    }
}
