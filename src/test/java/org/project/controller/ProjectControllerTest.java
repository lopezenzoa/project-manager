package org.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project.managers.PersistenceManager;
import org.project.model.Project;
import org.project.serializers.ProjectSerializer;
import org.project.serializers.TaskSerializer;
import org.project.serializers.UserSerializer;
import org.project.view.ProjectView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class ProjectControllerTest {
    Project model;
    ProjectView view;
    ProjectController controller;
    PersistenceManager<Project> persistenceManager;

    @BeforeEach
    void setUp() {
        /* INITIALIZE VIEW */
        view = new ProjectView();

        /* INITIALIZING PERSISTENCE MANAGER (REPOSITORY) */
        persistenceManager = new PersistenceManager<>(
                "projects_test.json",
                new ProjectSerializer(
                        new UserSerializer(),
                        new TaskSerializer(new UserSerializer())
                )
        );

        /* INITIALIZE CONTROLLER */
        controller = new ProjectController(model, view, persistenceManager);

        /* CLEANING FILE */
        persistenceManager.clearFile();

        /* INITIALIZE MOCK RESPONSIBLE */
        controller.setModel(buildProject(1));
    }
    
    Project buildProject(Integer id) {
        return new Project(
                id,
                new HashMap<>(),
                new LinkedList<>(),
                "Project",
                LocalDate.now(),
                LocalDate.of(2026, 12, 31),
                true
        );
    }

    /* TEST CASE 001 */
    @Test
    void shouldPersistANewFreshProject() {
        /* WHEN */
        /* THEN */
        assertDoesNotThrow(() -> controller.create(), "ERROR ON CREATE");
        assertTrue(persistenceManager.isModelPersisted(1));
    }

    /* TEST CASE 002 */
    @Test
    void shouldThrowAnExceptionWhenPersistingDuplicatedProject() {
        /* GIVEN */
        controller.setModel(buildProject(1));

        controller.create();

        /* WHEN */
        /* THEN */
        assertThrows(RuntimeException.class, () -> controller.create());
    }

    /* TEST CASE 003 */
    @Test
    void shouldPersistADifferentProject() {
        /* GIVEN */
        controller.setModel(buildProject(1));

        controller.create();

        controller.setModel(buildProject(2));

        /* WHEN */
        /* THEN */
        assertDoesNotThrow(() -> controller.create(), "ERROR ON CREATE");
    }

    /* TEST CASE 004 */
    @Test
    void shouldReadAPersistedProject() {
        /* GIVEN */
        controller.setModel(buildProject(1));

        controller.create();

        /* WHEN */
        /* THEN */
        assertDoesNotThrow(() -> controller.read(), "ERROR ON READ");
        assertTrue(persistenceManager.isModelPersisted(1));
    }

    /* TEST CASE 005 */
    @Test
    void shouldThrowAnExceptionWhenReadingAnUnpersistedProject() {
        /* GIVEN */
        controller.setModel(buildProject(1));

        controller.create();

        controller.setModel(buildProject(2));

        /* WHEN */
        /* THEN */
        assertThrows(RuntimeException.class, () -> controller.read());
    }

    /* TEST CASE 006 */
    @Test
    void shouldUpdateAPersistedProject() {
        /* GIVEN */
        controller.setModel(buildProject(1));

        controller.create();

        Project newModel = buildProject(1);

        newModel.setName("Project Update");
        newModel.setExpectedDeadline(LocalDate.of(2027, 12, 31));

        /* WHEN */
        /* THEN */
        assertDoesNotThrow(() -> controller.update(newModel), "ERROR ON UPDATE");
        assertTrue(persistenceManager.isModelPersisted(1));
    }

    /* TEST CASE 007 */
    @Test
    void shouldThrowAnExceptionWhenUpdatingAnUnpersistedProject() {
        /* GIVEN */
        controller.setModel(buildProject(1));

        controller.create();

        Project newModel = buildProject(2);

        /* WHEN */
        /* THEN */
        assertThrows(RuntimeException.class, () -> controller.update(newModel));
    }

    /* TEST CASE 008 */
    @Test
    void shouldDeletePersistedProject() {
        /* GIVEN */
        controller.setModel(buildProject(1));

        controller.create();

        /* WHEN */
        /* THEN */
        assertDoesNotThrow(() -> controller.delete(), "ERROR ON DELETE");
        assertTrue(persistenceManager.isModelPersisted(1));
    }

    /* TEST CASE 009 */
    @Test
    void shouldThrowAnExceptionWhenDeletingAnUnpersistedProject() {
        /* GIVEN */
        controller.setModel(buildProject(1));

        controller.create();

        controller.setModel(buildProject(2));

        /* WHEN */
        /* THEN */
        assertThrows(RuntimeException.class, () -> controller.delete());
    }

    /* TEST CASE 010 */
    @Test
    void shouldThrowAnExceptionWhenPersistingAnEmptyProject() {
        /* GIVEN */
        Project model = buildProject(2);

        model.setName("");

        controller.setModel(model);

        /* WHEN */
        /* THEN */
        assertThrows(RuntimeException.class, () -> controller.create());
        assertFalse(persistenceManager.isModelPersisted(2));
    }

    /* TEST CASE 011 */
    @Test
    void shouldThrowAnExceptionWhenPersistingAnInvalidCreationDateOrExpectedDeadline() {
        /* GIVEN */
        Project model = buildProject(2);

        model.setCreationDate(LocalDate.of(2025, 6, 18));
        model.setExpectedDeadline(LocalDate.of(2025, 6, 18));

        controller.setModel(model);

        /* WHEN */
        /* THEN */
        assertThrows(RuntimeException.class, () -> controller.create());
        assertFalse(persistenceManager.isModelPersisted(2));
    }
}