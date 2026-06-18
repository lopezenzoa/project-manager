package org.project.view;

import org.project.model.Task;
import org.project.model.User;

public class TaskView {
    public void printTask(Task task) {
        System.out.println(task);
    }
    public void printResponsible(User responsible) {
        System.out.println(responsible);
    }
}
