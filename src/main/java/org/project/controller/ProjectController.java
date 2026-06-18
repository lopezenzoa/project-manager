package org.project.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.project.controller.interfaces.Crudable;
import org.project.managers.PersistenceManager;
import org.project.model.Project;
import org.project.view.ProjectView;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class ProjectController implements Crudable<Project> {
    @Setter private Project model;
    private ProjectView view;
    private PersistenceManager<Project> persistenceManager;


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

            view.printProject(model);
        } catch (RuntimeException e) {
            throw new RuntimeException("ERROR ON READ");
        }
    }

    @Override
    public void update(Project newModel) {
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
            model.setIsActive(false);
            persistenceManager.updateModelInPersistence(model.getId(), model);
        } catch (RuntimeException e) {
            throw new RuntimeException("ERROR ON DELETE");
        }
    }

    public void checkModelIntegrity(Project model) throws RuntimeException {
        /* VERIFYING MODEL INTEGRITY */
        if (
                model.getName().trim().isEmpty()
                        || model.getCreationDate().isBefore(LocalDate.now())
                        || model.getExpectedDeadline().isBefore(LocalDate.now())
                        || model.getExpectedDeadline().isEqual(model.getCreationDate())
        )
            throw new RuntimeException("MODEL NOT VALID");
    }
}
