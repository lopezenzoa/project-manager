package org.project.view;

import org.project.model.Project;
import org.project.model.Task;
import org.project.model.TeamMember;
import org.project.model.enums.Status;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;

public class ProjectView {
    public void printProject(Project project) {
        System.out.println(project);
    }

    public void printTeamMembers(HashMap<UUID, TeamMember> team) {
        System.out.println("Team");
        for (Map.Entry<UUID, TeamMember> entry : team.entrySet())
            System.out.println("  " + entry.getValue().getName() + " - " + entry.getValue().getRole() + "\n");
    }

    public void printPendingTasks(LinkedList<Task> tasks) {
        System.out.println("Pending Tasks");
        for (Task task : tasks)
            if (task.getStatus().equals(Status.PENDING))
                System.out.println(task);
    }

    public void printFinishedTasks(LinkedList<Task> tasks) {
        System.out.println("Finished Tasks");
        for (Task task : tasks)
            if (task.getStatus().equals(Status.PENDING))
                System.out.println(task);
    }
}
