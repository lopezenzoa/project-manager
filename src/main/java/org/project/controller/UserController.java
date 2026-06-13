package org.project.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.project.controller.interfaces.Crudable;
import org.project.model.User;
import org.project.serializers.interfaces.Serializable;
import org.project.view.UserView;

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
public class UserController implements Crudable<User> {
    private User model;
    private final UserView view;
    private final Serializable<User> serializer;

    @Override
    public void create() throws IOException {
        if (!isModelPersisted()) {
            addModelToPersistence();
        }
    }

    @Override
    public void read() throws IOException {
        if (isModelPersisted()) {
            User user = getModelFromPersistence();

            if (user != null)
                view.printUser(user);
        }
    }

    @Override
    public void update(User newModel) throws IOException {
         if (isModelPersisted()) {
             User model = getModelFromPersistence();
             
             if (model != null) {
                 model.setName(newModel.getName());
                 model.setEmail(newModel.getEmail());
                 model.setPassword(newModel.getPassword());
                 model.setIsActive(newModel.getIsActive());
                 model.setRole(newModel.getRole());   
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
        Path fileName = Paths.get("/home/lopezenzoa/Documents/project-manager/src/main/resources/", "users.json");

        try (FileReader fileReader = new FileReader(fileName.toFile())) {
            String content = fileReader.readAllAsString();
            JSONArray usersJSON = new JSONArray(content);

            for (Object object : usersJSON) {
                Integer id = ((JSONObject) object).getInt("id");

                if (id.equals(model.getId()))
                    return true;
            }
        } catch (IOException e) {
            throw new IOException();
        }

        return false;
    }

    private User getModelFromPersistence() throws IOException {
        Path fileName = Paths.get("/home/lopezenzoa/Documents/project-manager/src/main/resources/", "users.json");

        try (FileReader fileReader = new FileReader(fileName.toFile())) {
            String content = fileReader.readAllAsString();
            JSONArray usersJSON = new JSONArray(content);

            for (Object object : usersJSON) {
                JSONObject userJSON = ((JSONObject) object);
                Integer id = userJSON.getInt("id");

                if (id.equals(model.getId()))
                    return serializer.deserialize(userJSON);
            }
        } catch (IOException e) {
            throw new IOException();
        }

        return null;
    }

    private List<User> getAllModelsFromPersistence() throws IOException {
        Path fileName = Paths.get("/home/lopezenzoa/Documents/project-manager/src/main/resources/", "users.json");

        try (FileReader fileReader = new FileReader(fileName.toFile())) {
            String content = fileReader.readAllAsString();
            JSONArray usersJSON = new JSONArray(content);

            List<User> users = new ArrayList<>();

            for (Object object : usersJSON) {
                JSONObject userJSON = ((JSONObject) object);
                users.add(serializer.deserialize(userJSON));
            }

            return users;
        } catch (IOException e) {
            throw new IOException();
        }
    }

    private void addModelToPersistence() throws IOException {
        Path fileName = Paths.get("/home/lopezenzoa/Documents/project-manager/src/main/resources/", "users.json");

        List<User> models = getAllModelsFromPersistence();
        JSONArray usersJSON = new JSONArray();

        for (User model : models) {
            JSONObject userJSON = serializer.serialize(model);
            usersJSON.put(userJSON);
        }

        /* APPENDING THE NEW MODEL */
        JSONObject currentModelJSON = serializer.serialize(model);
        usersJSON.put(currentModelJSON);

        try (FileWriter file = new FileWriter(fileName.toFile())) {
            file.write(usersJSON.toString(4));
        } catch (IOException e) {
            throw new IOException();
        }
    }

    public void updateModelInPersistence(User model) throws IOException {
        List<User> users = getAllModelsFromPersistence();
        users.removeIf(user -> user.getId().equals(model.getId()));

        Path fileName = Paths.get("/home/lopezenzoa/Documents/project-manager/src/main/resources/", "users.json");

        JSONArray usersJSON = new JSONArray();

        for (User user : users) {
            JSONObject userJSON = serializer.serialize(user);
            usersJSON.put(userJSON);
        }

        JSONObject newModelJSON = serializer.serialize(model);
        usersJSON.put(newModelJSON);

        try (FileWriter file = new FileWriter(fileName.toFile())) {
            file.write(usersJSON.toString(4));
        } catch (IOException e) {
            throw new IOException();
        }
    }
}
