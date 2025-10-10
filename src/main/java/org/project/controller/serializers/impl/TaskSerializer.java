package org.project.controller.serializers.impl;

import org.json.JSONException;
import org.json.JSONObject;
import org.project.controller.serializers.ITaskSerializer;
import org.project.model.Task;
import org.project.model.TeamMember;
import org.project.model.enums.Active;
import org.project.model.enums.Status;

public class TaskSerializer implements ITaskSerializer {
    private final UserSerializer serializer;

    public TaskSerializer() {
        this.serializer = new TeamMemberSerializer();
    }

    /**
     * Creates a new task using as a base a JSONObject.
     * @param taskJSON is the JSONObject used as starting point.
     * */
    @Override
    public Task deserialize(JSONObject taskJSON) {
        try {
            Integer taskID = taskJSON.getInt("ID");
            Integer projectID = taskJSON.getInt("projectID");
            String title = taskJSON.getString("title");
            String description = taskJSON.getString("description");
            TeamMember responsible = (TeamMember) serializer.deserialize(taskJSON.getJSONObject("responsible"));
            String creationDate = taskJSON.getString("creationDate");
            String deadline = taskJSON.getString("deadline");
            Status status = Status.valueOf(taskJSON.getString("status"));
            Active active = Active.valueOf(taskJSON.getString("active"));

            return new Task(taskID, projectID, title, description, responsible, creationDate, deadline, status, active);
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

            taskJSON.put("ID", task.getID());
            taskJSON.put("projectID", task.getProjectID());
            taskJSON.put("title", task.getTitle());
            taskJSON.put("description", task.getDescription());
            taskJSON.put("responsible", serializer.serialize(task.getResponsible()));
            taskJSON.put("creationDate", task.getCreationDate());
            taskJSON.put("deadline", task.getDeadline());
            taskJSON.put("status", task.getStatus());
            taskJSON.put("active", task.getActive());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return taskJSON;
    }
}
