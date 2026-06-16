package org.project.managers;

import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.project.serializers.interfaces.Serializable;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PersistenceManager<T> {
    private final Path PATH_TO_FILE;
    private final Serializable<T> serializer;

    public PersistenceManager(String filename, Serializable<T> serializer) {
        this.PATH_TO_FILE = Paths.get("/home/lopezenzoa/Documents/project-manager/src/main/resources/", filename);
        this.serializer = serializer;
    }

    public Boolean isModelPersisted(Integer modelId) {
        try (FileReader fileReader = new FileReader(PATH_TO_FILE.toFile())) {
            String fileContent = fileReader.readAllAsString();
            JSONArray modelArrayJSON = new JSONArray(fileContent);

            if (modelArrayJSON.isEmpty())
                return false;

            for (Object modelJSON : modelArrayJSON) {
                Integer id = ((JSONObject) modelJSON).getInt("id");

                if (id.equals(modelId))
                    return true;
            }

            return false;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public T getModelFromPersistence(Integer modelId) {
        try (FileReader fileReader = new FileReader(PATH_TO_FILE.toFile())) {
            String fileContent = fileReader.readAllAsString();

            if (fileContent.isEmpty())
                return null;

            JSONArray modelArrayJSON = new JSONArray(fileContent);

            for (Object modelJSON : modelArrayJSON) {
                Integer id = ((JSONObject) modelJSON).getInt("id");

                if (id.equals(modelId))
                    return serializer.deserialize((JSONObject) modelJSON);
            }

            return null;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<T> getAllModelsFromPersistence() {
        try (FileReader fileReader = new FileReader(PATH_TO_FILE.toFile())) {
            String fileContent = fileReader.readAllAsString();

            if (fileContent.isEmpty())
                return new ArrayList<>();

            JSONArray modelArrayJSON = new JSONArray(fileContent);
            List<T> models = new ArrayList<>();

            for (Object modelJSON : modelArrayJSON) {
                models.add(serializer.deserialize(((JSONObject) modelJSON)));
            }

            return models;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public JSONArray getAllModelsJSONFromPersistence() {
        try (FileReader fileReader = new FileReader(PATH_TO_FILE.toFile())) {
            String fileContent = fileReader.readAllAsString();

            if (fileContent.isEmpty())
                return new JSONArray();

            return new JSONArray(fileContent);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public T addModelToPersistence(Integer modelId, T model) {
        Boolean isModelPersisted = isModelPersisted(modelId);

        if (isModelPersisted)
            throw new RuntimeException("MODEL DUPLICATED");

        List<T> models = getAllModelsFromPersistence();
        JSONArray modelArrayJSON = new JSONArray();

        for (T persistedModel : models) {
            JSONObject modelJSON = serializer.serialize(persistedModel);
            modelArrayJSON.put(modelJSON);
        }

        /* APPENDING THE NEW MODEL */
        JSONObject modelJSON = serializer.serialize(model);
        modelArrayJSON.put(modelJSON);

        try (FileWriter file = new FileWriter(PATH_TO_FILE.toFile(), false)) {
            file.write(modelArrayJSON.toString(4));
            return model;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public T updateModelInPersistence(Integer modelId, T newModel) {
        Boolean isModelPersisted = isModelPersisted(modelId);

        if (!isModelPersisted)
            throw new RuntimeException("MODEL NOT FOUND");

        JSONArray modelArrayJSON = getAllModelsJSONFromPersistence();

        /* REMOVING THE OLD MODEL */
        for (int i = 0; i < modelArrayJSON.length(); i++) {
            Integer id = ((JSONObject) modelArrayJSON.get(i)).getInt("id");

            if (id.equals(modelId))
                modelArrayJSON.remove(i);
        }

        /* CHECKING UNIQUENESS OF THE NEW MODEL */
        for (Object modelJSON : modelArrayJSON) {
            T model = serializer.deserialize(((JSONObject) modelJSON));

            if (model.hashCode() == newModel.hashCode())
                throw new RuntimeException("MODEL DUPLICATED");
        }

        /* APPENDING THE UPDATED MODEL */
        JSONObject modelJSON = serializer.serialize(newModel);
        modelArrayJSON.put(modelJSON);

        try (FileWriter file = new FileWriter(PATH_TO_FILE.toFile(), false)) {
            file.write(modelArrayJSON.toString(4));
            return newModel;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void clearFile() {
        try (FileWriter file = new FileWriter(PATH_TO_FILE.toFile(), false)) {
            JSONArray emptyArrayJSON = new JSONArray();
            file.write(emptyArrayJSON.toString(4));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
