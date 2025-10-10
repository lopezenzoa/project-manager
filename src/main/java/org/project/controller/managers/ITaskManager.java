package org.project.controller.managers;

import org.project.model.Task;

import java.util.HashSet;

public interface ITaskManager {
    HashSet<Integer> getTasksIDs();
    Task searchTaskByID(Integer ID);
    HashSet<String> getTasksTitles();
    boolean addTask(Task task);
    boolean removeTask(Task task);
    boolean removeTask(Integer ID);
    boolean checkTask(Task task);
    boolean checkTask(Integer ID);
}
