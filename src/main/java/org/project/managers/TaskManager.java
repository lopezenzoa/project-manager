package org.project.managers;

import lombok.AllArgsConstructor;
import org.project.model.Task;

import java.util.HashSet;
import java.util.LinkedList;

@AllArgsConstructor
public class TaskManager {
    private final LinkedList<Task> tasks;

    /**
     * Returns a collection of tasks Ids.
     * @return a HashSet made of tasks Ids.
     * */
    public HashSet<Integer> getTasksIds() {
        HashSet<Integer> taskIds = new HashSet<>();

        for (Task task : tasks)
            taskIds.add(task.getId());

        return taskIds;
    }

    /**
     * Searches a task in the list of project's tasks.
     * @param Id is the identifier of the task that want to search.
     * @return a Task object if the identifier corresponds with a task or null otherwise.
     * */
    public Task searchTaskById(Integer Id) {
        for (Task task : tasks)
            if (task.getId().equals(Id))
                return task;
        return null;
    }

    /**
     * Returns a collection of tasks titles.
     * @return a HashSet made of tasks titles.
     * */
    public HashSet<String> getTasksTitles() {
        HashSet<String> titles = new HashSet<>();

        for (Task task : tasks)
            titles.add(task.getTitle());

        return titles;
    }

    /**
     * Adds a task to the list of project's tasks.
     * @param task is the object that want to add.
     * @return a boolean value depending on if the task could be added or not.
     * */
    public boolean addTask(Task task) {
        if (!tasks.contains(task))
            return tasks.add(task);
        return false;
    }

    /**
     * Removes a task from the list of project's tasks.
     * @param task is the object that want to remove.
     * @return a boolean value depending on if the task could be removed or not.
     * */
    public boolean removeTask(Task task) {
        return tasks.remove(task);
    }

    /**
     * Removes a task from the list of project's tasks given its Id.
     * @param Id is the identifier of the task that want to remove.
     * @return a boolean value depending on if the task could be removed or not.
     * */
    public boolean removeTask(Integer Id) {
        Task toDelete = searchTaskById(Id);

        if (toDelete != null)
            return tasks.remove(toDelete);

        return false;
    }

    /**
     * Checks if a task exists in the list of project's tasks.
     * @param task is the object that want to check its existence.
     * @return a boolean value depending on if the task exists or not.
     * */
    public boolean checkTask(Task task) {
        return tasks.contains(task);
    }

    /**
     * Checks if a task exists in the list of project's tasks given its Id.
     * @param Id is the identifier of the task that want to check its existence.
     * @return a boolean value depending on if the task exists or not.
     * */
    public boolean checkTask(Integer Id) {
        return searchTaskById(Id) != null;
    }
}
