package org.project.view;

import org.project.model.Task;
import org.project.model.TeamMember;

public interface ITaskView {
    void printTask(Task task);
    void printResponsible(TeamMember responsible);
}
