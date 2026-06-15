package org.project.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.project.controller.interfaces.Crudable;
import org.project.model.Project;
import org.project.view.ProjectView;
import org.project.serializers.interfaces.Serializable;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ProjectController implements Crudable<Project> {
    private Project model;
    private final ProjectView view;
    private final Serializable<Project> serializer;

    @Override
    public void create() throws IOException {
        if (!isModelPersisted()) {
            addModelToPersistence();
        }
    }

    @Override
    public void read() throws IOException {
        if (isModelPersisted()) {
            Project project = getModelFromPersistence();

            if (project != null)
                view.printProject(project);
        }
    }

    @Override
    public void update(Project newModel) throws IOException {
        if (isModelPersisted()) {
            Project model = getModelFromPersistence();

            if (model != null) {
                model.setId(newModel.getId());
                model.setTeam(newModel.getTeam());
                model.setTasks(newModel.getTasks());
                model.setName(newModel.getName());
                model.setCreationDate(newModel.getCreationDate());
                model.setExpectedDeadline(newModel.getExpectedDeadline());
                model.setIsActive(newModel.getIsActive());
            }

            setModel(model);

            updateModelInPersistence(model);
        }
    }

    @Override
    public void delete() throws IOException {
        if (isModelPersisted()) {
            model.setIsActive(false);
            update(model);
        }
    }

    private Boolean isModelPersisted() throws IOException {
        Path fileName = Paths.get("/home/lopezenzoa/Documents/project-manager/src/main/resources/", "projects.json");

        try (FileReader fileReader = new FileReader(fileName.toFile())) {
            String content = fileReader.readAllAsString();
            JSONArray projectsJSON = new JSONArray(content);

            for (Object object : projectsJSON) {
                Integer id = ((JSONObject) object).getInt("id");

                if (id.equals(model.getId()))
                    return true;
            }
        } catch (IOException e) {
            throw new IOException();
        }

        return false;
    }

    private Project getModelFromPersistence() throws IOException {
        Path fileName = Paths.get("/home/lopezenzoa/Documents/project-manager/src/main/resources/", "projects.json");

        try (FileReader fileReader = new FileReader(fileName.toFile())) {
            String content = fileReader.readAllAsString();

            if (content.isEmpty())
                return null;

            JSONArray projectsJSON = new JSONArray(content);

            for (Object object : projectsJSON) {
                JSONObject projectJSON = ((JSONObject) object);
                Integer id = projectJSON.getInt("id");

                if (id.equals(model.getId()))
                    return serializer.deserialize(projectJSON);
            }
        } catch (IOException e) {
            throw new IOException();
        }

        return null;
    }

    private List<Project> getAllModelsFromPersistence() throws IOException {
        Path fileName = Paths.get("/home/lopezenzoa/Documents/project-manager/src/main/resources/", "projects.json");

        try (FileReader fileReader = new FileReader(fileName.toFile())) {
            String content = fileReader.readAllAsString();
            JSONArray projectsJSON = new JSONArray(content);

            List<Project> projects = new ArrayList<>();

            for (Object object : projectsJSON) {
                JSONObject projectJSON = ((JSONObject) object);
                projects.add(serializer.deserialize(projectJSON));
            }

            return projects;
        } catch (IOException e) {
            throw new IOException();
        }
    }

    private void addModelToPersistence() throws IOException {
        Path fileName = Paths.get("/home/lopezenzoa/Documents/project-manager/src/main/resources/", "projects.json");

        List<Project> models = getAllModelsFromPersistence();
        JSONArray projectsJSON = new JSONArray();

        for (Project model : models) {
            JSONObject projectJSON = serializer.serialize(model);
            projectsJSON.put(projectJSON);
        }

        /* APPENDING THE NEW MODEL */
        JSONObject currentModelJSON = serializer.serialize(model);
        projectsJSON.put(currentModelJSON);

        try (FileWriter file = new FileWriter(fileName.toFile())) {
            file.write(projectsJSON.toString(4));
        } catch (IOException e) {
            throw new IOException();
        }
    }

    public void updateModelInPersistence(Project model) throws IOException {
        List<Project> projects = getAllModelsFromPersistence();
        projects.removeIf(Project -> Project.getId().equals(model.getId()));

        Path fileName = Paths.get("/home/lopezenzoa/Documents/project-manager/src/main/resources/", "projects.json");

        JSONArray projectsJSON = new JSONArray();

        for (Project Project : projects) {
            JSONObject projectJSON = serializer.serialize(Project);
            projectsJSON.put(projectJSON);
        }

        JSONObject newModelJSON = serializer.serialize(model);
        projectsJSON.put(newModelJSON);

        try (FileWriter file = new FileWriter(fileName.toFile())) {
            file.write(projectsJSON.toString(4));
        } catch (IOException e) {
            throw new IOException();
        }
    }
}
