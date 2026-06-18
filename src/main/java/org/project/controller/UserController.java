package org.project.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.project.controller.interfaces.Crudable;
import org.project.managers.PersistenceManager;
import org.project.model.User;
import org.project.view.UserView;

import java.util.List;

@AllArgsConstructor
@Getter
public class UserController implements Crudable<User> {
    @Setter private User model;
    private UserView view;
    private PersistenceManager<User> persistenceManager;

    @Override
    public void create() {
        try {
            checkModelIntegrity(this.model);
            checkModelUniqueness();

            persistenceManager.addModelToPersistence(model.getId(), model);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void read() {
        try {
            if (!persistenceManager.isModelPersisted(model.getId()))
                throw new RuntimeException();

            view.printUser(model);
        } catch (RuntimeException e) {
            throw new RuntimeException("ERROR ON READ");
        }
    }

    @Override
    public void update(User newModel) {
        try {
            checkModelIntegrity(newModel);
            checkModelUniqueness(newModel);

            persistenceManager.updateModelInPersistence(newModel.getId(), newModel);
            this.model = newModel;
        } catch (RuntimeException e) {
            throw new RuntimeException("ERROR ON UPDATE");
        }
    }

    @Override
    public void delete() {
        try {
            model.setIsActive(false);
            persistenceManager.updateModelInPersistence(model.getId(), model);
        } catch (RuntimeException e) {
            throw new RuntimeException("ERROR ON DELETE");
        }
    }

    public void checkModelIntegrity(User model) throws RuntimeException {
        /* VERIFYING MODEL INTEGRITY */
        if (
                model.getName().trim().isEmpty()
                        || model.getEmail().trim().isEmpty()
                        || !model.getEmail().trim().contains("@")
                        || model.getPassword().trim().isEmpty()
        )
            throw new RuntimeException("MODEL NOT VALID");
    }

    public void checkModelUniqueness() throws RuntimeException {
        /* VERIFYING MODEL UNIQUENESS */
        List<User> persistedModels = persistenceManager.getAllModelsFromPersistence();

        for (User persistedModel : persistedModels) {
            if (persistedModel.getEmail().trim().equalsIgnoreCase(model.getEmail().trim()))
                throw new RuntimeException("EMAIL DUPLICATED");
        }
    }

    public void checkModelUniqueness(User newModel) throws RuntimeException {
        /* VERIFYING MODEL UNIQUENESS */
        List<User> persistedModels = persistenceManager.getAllModelsFromPersistence();

        /* REMOVING THE OLD MODEL */
        persistedModels.remove(model);

        for (User persistedModel : persistedModels) {
            if (persistedModel.getEmail().trim().equalsIgnoreCase(newModel.getEmail().trim()))
                throw new RuntimeException("EMAIL DUPLICATED");
        }
    }
}
