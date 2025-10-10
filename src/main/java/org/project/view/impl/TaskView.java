package org.project.view.impl;

import org.project.model.Task;
import org.project.model.TeamMember;
import org.project.view.ITaskView;

public class TaskView implements ITaskView {
    @Override
    public void printTask(Task task) {
        System.out.println(task);
    }

    @Override
    public void printResponsible(TeamMember responsible) {
        System.out.println(responsible);
    }
}
