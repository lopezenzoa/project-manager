package org.project.serializers;

import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.project.builders.ProjectBuilder;
import org.project.model.*;
import org.project.serializers.interfaces.Serializable;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@AllArgsConstructor
public class ProjectSerializer implements Serializable<Project> {
    private final UserSerializer userSerializer;
    private final TaskSerializer taskSerializer;

    /**
     * Creates a new project using as a base a JSONObject.
     * @param projectJSON is the JSONObject used as starting point.
     * */
    @Override
    public Project deserialize(JSONObject projectJSON) {
        HashMap<Integer, User> team = new HashMap<>();
        LinkedList<Task> tasks = new LinkedList<>();

        try {
            Integer id = projectJSON.getInt("id");

            JSONArray teamJSON = projectJSON.getJSONArray("team");
            for (int i = 0; i < teamJSON.length(); i++) {
                JSONObject memberJSON = teamJSON.getJSONObject(i);
                Integer memberID = memberJSON.getInt("id");
                User member = userSerializer.deserialize(memberJSON.getJSONObject("member"));

                team.put(memberID, member);
            }

            JSONArray tasksJSON = projectJSON.getJSONArray("tasks");
            for (int i = 0; i < tasksJSON.length(); i++) {
                JSONObject taskJSON = tasksJSON.getJSONObject(i);
                tasks.add(taskSerializer.deserialize(taskJSON));
            }

            String name = projectJSON.getString("name");
            LocalDate creationDate = LocalDate.parse(projectJSON.getString("creationDate"));
            LocalDate expectedDeadline = LocalDate.parse(projectJSON.getString("expectedDeadline"));
            Boolean isActive = projectJSON.getBoolean("isActive");

            return new ProjectBuilder()
                    .setId(id)
                    .setTeam(team)
                    .setTasks(tasks)
                    .setName(name)
                    .setCreationDate(creationDate)
                    .setExpectedDeadline(expectedDeadline)
                    .setActive(isActive)
                    .build();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Serializes the class Project.
     * @return a JSONObject representation of the class.
     * */
    @Override
    public JSONObject serialize(Project project) {
        JSONObject projectJSON = null;

        try {
            projectJSON = new JSONObject();
            JSONArray teamJSON = new JSONArray();
            JSONArray tasksJSON = new JSONArray();

            projectJSON.put("id", project.getId());

            JSONObject memberJSON = new JSONObject();
            for (Map.Entry<Integer, User> entry : project.getTeam().entrySet()) {
                memberJSON.put("id", entry.getKey());
                memberJSON.put("member", userSerializer.serialize(entry.getValue()));

                teamJSON.put(memberJSON);
            }

            projectJSON.put("team", teamJSON);

            for (Task t : project.getTasks())
                tasksJSON.put(taskSerializer.serialize(t));

            projectJSON.put("tasks", tasksJSON);

            projectJSON.put("name", project.getName());
            projectJSON.put("creationDate", project.getCreationDate());
            projectJSON.put("expectedDeadline", project.getExpectedDeadline());
            projectJSON.put("isActive", project.getIsActive());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return projectJSON;
    }
}
