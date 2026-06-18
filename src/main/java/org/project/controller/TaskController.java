package org.project.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.project.controller.interfaces.Crudable;
import org.project.managers.PersistenceManager;
import org.project.model.Task;
import org.project.model.User;
import org.project.model.enums.State;
import org.project.serializers.interfaces.Serializable;
import org.project.view.TaskView;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class TaskController implements Crudable<Task> {
    @Setter public Task model;
    public TaskView view;
    private PersistenceManager<Task> persistenceManager;

    @Override
    public void create() {
        try {
            checkModelIntegrity(model);

            persistenceManager.addModelToPersistence(model.getId(), model);
        } catch (RuntimeException e) {
            throw new RuntimeException("ERROR ON CREATE");
        }
    }

    @Override
    public void read() {
        try {
            if (!persistenceManager.isModelPersisted(model.getId()))
                throw new RuntimeException();

            view.printTask(model);
        } catch (RuntimeException e) {
            throw new RuntimeException("ERROR ON READ");
        }
    }

    @Override
    public void update(Task newModel) {
        try {
            checkModelIntegrity(newModel);

            persistenceManager.updateModelInPersistence(newModel.getId(), newModel);
            this.model = newModel;
        } catch (RuntimeException e) {
            throw new RuntimeException("ERROR ON UPDATE");
        }
    }

    @Override
    public void delete() {
        try {
            model.setState(State.CANCELED);
            persistenceManager.updateModelInPersistence(model.getId(), model);
        } catch (RuntimeException e) {
            throw new RuntimeException("ERROR ON DELETE");
        }
    }

    public void checkModelIntegrity(Task model) throws RuntimeException {
        /* VERIFYING MODEL INTEGRITY */
        if (
                model.getProjectId() <= 0
                        || model.getTitle().trim().isEmpty()
                        || model.getDescription().trim().isEmpty()
                        || model.getCreationDate().isBefore(LocalDate.now())
                        || model.getExpectedDeadline().isBefore(LocalDate.now())
                        || model.getExpectedDeadline().isEqual(model.getCreationDate())
        )
            throw new RuntimeException("MODEL NOT VALID");
    }
}
