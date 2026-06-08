package org.project.serializers;

import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.project.builders.TaskBuilder;
import org.project.model.Task;
import org.project.model.TeamMember;
import org.project.model.enums.Status;
import org.project.serializers.interfaces.Serializable;

@AllArgsConstructor
public class TaskSerializer implements Serializable<Task> {
    private final UserSerializer responsibleSerializer;

    /**
     * Creates a new task using as a base a JSONObject.
     * @param taskJSON is the JSONObject used as starting point.
     * */
    @Override
    public Task deserialize(JSONObject taskJSON) {
        try {
            Integer taskId = taskJSON.getInt("taskId");
            Integer projectId = taskJSON.getInt("projectID");
            String title = taskJSON.getString("title");
            String description = taskJSON.getString("description");
            TeamMember responsible = (TeamMember) responsibleSerializer.deserialize(taskJSON.getJSONObject("responsible"));
            String creationDate = taskJSON.getString("creationDate");
            String deadline = taskJSON.getString("deadline");
            Status status = Status.valueOf(taskJSON.getString("status"));

            return new TaskBuilder()
                    .setTaskId(taskId)
                    .setProjectId(projectId)
                    .setTitle(title)
                    .setDescription(description)
                    .setResponsible(responsible)
                    .setCreationDate(creationDate)
                    .setDeadline(deadline)
                    .setStatus(status)
                    .build();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Serializes the class Task.
     * @return a JSONObject representation of the class.
     * */
    @Override
    public JSONObject serialize(Task task) {
        JSONObject taskJSON = null;

        try {
            taskJSON = new JSONObject();

            taskJSON.put("taskId", task.getTaskId());
            taskJSON.put("projectId", task.getProjectId());
            taskJSON.put("title", task.getTitle());
            taskJSON.put("description", task.getDescription());
            taskJSON.put("responsible", responsibleSerializer.serialize(task.getResponsible()));
            taskJSON.put("creationDate", task.getCreationDate());
            taskJSON.put("deadline", task.getDeadline());
            taskJSON.put("status", task.getStatus());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return taskJSON;
    }
}
