package org.project.controller.serializers.impl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.project.builders.ProjectBuilder;
import org.project.controller.serializers.IProjectSerializer;
import org.project.controller.serializers.ITaskSerializer;
import org.project.controller.serializers.IUserSerializer;
import org.project.model.*;
import org.project.model.enums.Active;
import org.project.model.enums.Status;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ProjectSerializer implements IProjectSerializer {
    private final IUserSerializer userSerializer;
    private final ITaskSerializer taskSerializer;

    public ProjectSerializer(IUserSerializer userSerializer, ITaskSerializer taskSerializer) {
        this.userSerializer = userSerializer;
        this.taskSerializer = taskSerializer;
    }

    /**
     * Creates a new project using as a base a JSONObject.
     * @param projectJSON is the JSONObject used as starting point.
     * */
    @Override
    public Project deserialize(JSONObject projectJSON) {
        HashMap<Integer, TeamMember> team = new HashMap<>();
        LinkedList<Task> tasks = new LinkedList<>();

        try {
            Integer projectID = projectJSON.getInt("ID");
            Admin admin = (Admin) userSerializer.deserialize(projectJSON.getJSONObject("admin"));
            Leader leader = (Leader) userSerializer.deserialize(projectJSON.getJSONObject("leader"));

            JSONArray teamJSON = projectJSON.getJSONArray("team");
            for (int i = 0; i < teamJSON.length(); i++) {
                JSONObject memberJSON = teamJSON.getJSONObject(i);
                Integer memberID = memberJSON.getInt("ID");
                TeamMember member = (TeamMember) userSerializer.deserialize(memberJSON.getJSONObject("member"));

                team.put(memberID, member);
            }

            JSONArray tasksJSON = projectJSON.getJSONArray("tasks");
            for (int i = 0; i < tasksJSON.length(); i++) {
                JSONObject taskJSON = tasksJSON.getJSONObject(i);
                tasks.add(taskSerializer.deserialize(taskJSON));
            }

            String name = projectJSON.getString("name");
            String creationDate = projectJSON.getString("creationDate");
            String deadline = projectJSON.getString("deadline");
            Status status = Status.valueOf(projectJSON.getString("status"));
            Active active = Active.valueOf(projectJSON.getString("active"));

            return new ProjectBuilder()
                    .setID(projectID)
                    .setAdmin(admin)
                    .setLeader(leader)
                    .setTeam(team)
                    .setTasks(tasks)
                    .setName(name)
                    .setCreationDate(creationDate)
                    .setDeadline(deadline)
                    .setStatus(status)
                    .setActive(active)
                    .build();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    /**
     * Serializes the class Project.
     * @return a JSONObject representation of the class.
     * */
    public JSONObject serialize(Project project) {
        JSONObject projectJSON = null;

        try {
            projectJSON = new JSONObject();
            JSONArray teamJSON = new JSONArray();
            JSONArray tasksJSON = new JSONArray();

            projectJSON.put("ID", project.getID());
            projectJSON.put("admin", userSerializer.serialize(project.getAdmin()));
            projectJSON.put("leader", userSerializer.serialize(project.getLeader()));

            JSONObject memberJSON = new JSONObject();
            for (Map.Entry<Integer, TeamMember> entry : project.getTeam().entrySet()) {
                memberJSON.put("ID", entry.getKey());
                memberJSON.put("member", userSerializer.serialize(entry.getValue()));

                teamJSON.put(memberJSON);
            }

            projectJSON.put("team", teamJSON);

            for (Task t : project.getTasks())
                tasksJSON.put(taskSerializer.serialize(t));

            projectJSON.put("tasks", tasksJSON);
            projectJSON.put("name", project.getName());
            projectJSON.put("creationDate", project.getCreationDate());
            projectJSON.put("deadline", project.getDeadline());
            projectJSON.put("status", project.getStatus());
            projectJSON.put("active", project.getActive());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return projectJSON;
    }
}
