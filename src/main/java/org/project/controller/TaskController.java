package org.project.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.project.controller.interfaces.Crudable;
import org.project.model.Task;
import org.project.model.enums.State;
import org.project.serializers.interfaces.Serializable;
import org.project.view.TaskView;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class TaskController implements Crudable<Task> {
    public Task model;
    public TaskView view;
    private final Serializable<Task> serializer;

    @Override
    public void create() throws IOException {
        if (!isModelPersisted()) {
            addModelToPersistence();
        }
    }

    @Override
    public void read() throws IOException {
        if (isModelPersisted()) {
            Task task = getModelFromPersistence();

            if (task != null)
                view.printTask(task);
        }
    }

    @Override
    public void update(Task newModel) throws IOException {
        if (isModelPersisted()) {
            Task model = getModelFromPersistence();

            if (model != null) {
                model.setProjectId(newModel.getProjectId());
                model.setTitle(newModel.getTitle());
                model.setDescription(newModel.getDescription());
                model.setResponsible(newModel.getResponsible());
                model.setCreationDate(newModel.getCreationDate());
                model.setExpectedDeadline(newModel.getExpectedDeadline());
                model.setState(newModel.getState());
            }

            setModel(model);

            updateModelInPersistence(model);
        }
    }

    @Override
    public void delete() throws IOException {
        if (isModelPersisted()) {
            model.setState(State.CANCELED);
            update(model);
        }
    }

    private Boolean isModelPersisted() throws IOException {
        Path fileName = Paths.get("/home/lopezenzoa/Documents/project-manager/src/main/resources/", "tasks.json");

        try (FileReader fileReader = new FileReader(fileName.toFile())) {
            String content = fileReader.readAllAsString();
            JSONArray tasksJSON = new JSONArray(content);

            for (Object object : tasksJSON) {
                Integer id = ((JSONObject) object).getInt("id");

                if (id.equals(model.getId()))
                    return true;
            }
        } catch (IOException e) {
            throw new IOException();
        }

        return false;
    }

    private Task getModelFromPersistence() throws IOException {
        Path fileName = Paths.get("/home/lopezenzoa/Documents/project-manager/src/main/resources/", "tasks.json");

        try (FileReader fileReader = new FileReader(fileName.toFile())) {
            String content = fileReader.readAllAsString();

            if (content.isEmpty())
                return null;

            JSONArray tasksJSON = new JSONArray(content);

            for (Object object : tasksJSON) {
                JSONObject taskJSON = ((JSONObject) object);
                Integer id = taskJSON.getInt("id");

                if (id.equals(model.getId()))
                    return serializer.deserialize(taskJSON);
            }
        } catch (IOException e) {
            throw new IOException();
        }

        return null;
    }

    private List<Task> getAllModelsFromPersistence() throws IOException {
        Path fileName = Paths.get("/home/lopezenzoa/Documents/project-manager/src/main/resources/", "tasks.json");

        try (FileReader fileReader = new FileReader(fileName.toFile())) {
            String content = fileReader.readAllAsString();
            JSONArray tasksJSON = new JSONArray(content);

            List<Task> tasks = new ArrayList<>();

            for (Object object : tasksJSON) {
                JSONObject taskJSON = ((JSONObject) object);
                tasks.add(serializer.deserialize(taskJSON));
            }

            return tasks;
        } catch (IOException e) {
            throw new IOException();
        }
    }

    private void addModelToPersistence() throws IOException {
        Path fileName = Paths.get("/home/lopezenzoa/Documents/project-manager/src/main/resources/", "tasks.json");

        List<Task> models = getAllModelsFromPersistence();
        JSONArray tasksJSON = new JSONArray();

        for (Task model : models) {
            JSONObject taskJSON = serializer.serialize(model);
            tasksJSON.put(taskJSON);
        }

        /* APPENDING THE NEW MODEL */
        JSONObject currentModelJSON = serializer.serialize(model);
        tasksJSON.put(currentModelJSON);

        try (FileWriter file = new FileWriter(fileName.toFile())) {
            file.write(tasksJSON.toString(4));
        } catch (IOException e) {
            throw new IOException();
        }
    }

    public void updateModelInPersistence(Task model) throws IOException {
        List<Task> tasks = getAllModelsFromPersistence();
        tasks.removeIf(task -> task.getId().equals(model.getId()));

        Path fileName = Paths.get("/home/lopezenzoa/Documents/project-manager/src/main/resources/", "tasks.json");

        JSONArray tasksJSON = new JSONArray();

        for (Task task : tasks) {
            JSONObject taskJSON = serializer.serialize(task);
            tasksJSON.put(taskJSON);
        }

        JSONObject newModelJSON = serializer.serialize(model);
        tasksJSON.put(newModelJSON);

        try (FileWriter file = new FileWriter(fileName.toFile())) {
            file.write(tasksJSON.toString(4));
        } catch (IOException e) {
            throw new IOException();
        }
    }
}
