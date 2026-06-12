package org.project.serializers;

import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.project.builders.TaskBuilder;
import org.project.model.Task;
import org.project.model.User;
import org.project.model.enums.State;
import org.project.serializers.interfaces.Serializable;

import java.time.LocalDate;

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
            Integer id = taskJSON.getInt("id");
            Integer projectId = taskJSON.getInt("projectID");
            String title = taskJSON.getString("title");
            String description = taskJSON.getString("description");
            User responsible = responsibleSerializer.deserialize(taskJSON.getJSONObject("responsible"));
            LocalDate creationDate = LocalDate.parse(taskJSON.getString("creationDate"));
            LocalDate expectedDeadline = LocalDate.parse(taskJSON.getString("expectedDeadline"));
            State state = State.valueOf(taskJSON.getString("state"));

            return new TaskBuilder()
                    .setId(id)
                    .setProjectId(projectId)
                    .setTitle(title)
                    .setDescription(description)
                    .setResponsible(responsible)
                    .setCreationDate(creationDate)
                    .setExpectedDeadline(expectedDeadline)
                    .setState(state)
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

            taskJSON.put("id", task.getId());
            taskJSON.put("projectId", task.getProjectId());
            taskJSON.put("title", task.getTitle());
            taskJSON.put("description", task.getDescription());
            taskJSON.put("responsible", responsibleSerializer.serialize(task.getResponsible()));
            taskJSON.put("creationDate", task.getCreationDate());
            taskJSON.put("expectedDeadline", task.getExpectedDeadline());
            taskJSON.put("state", task.getState());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return taskJSON;
    }
}
