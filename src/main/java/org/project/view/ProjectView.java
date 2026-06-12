package org.project.view;

import org.project.model.Project;
import org.project.model.Task;
import org.project.model.User;
import org.project.model.enums.State;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ProjectView {
    public void printProject(Project project) {
        System.out.println(project);
    }

    public void printTeamMembers(HashMap<Integer, User> team) {
        System.out.println("Team");
        for (Map.Entry<Integer, User> entry : team.entrySet())
            System.out.println("  " + entry.getValue().getName() + " - " + entry.getValue().getRole() + "\n");
    }

    public void printPendingTasks(LinkedList<Task> tasks) {
        System.out.println("Pending Tasks");
        for (Task task : tasks)
            if (task.getState().equals(State.PENDING))
                System.out.println(task);
    }

    public void printFinishedTasks(LinkedList<Task> tasks) {
        System.out.println("Finished Tasks");
        for (Task task : tasks)
            if (task.getState().equals(State.PENDING))
                System.out.println(task);
    }
}
