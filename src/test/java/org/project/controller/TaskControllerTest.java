package org.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project.managers.PersistenceManager;
import org.project.model.Task;
import org.project.model.User;
import org.project.model.enums.Role;
import org.project.model.enums.State;
import org.project.serializers.TaskSerializer;
import org.project.serializers.UserSerializer;
import org.project.view.TaskView;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaskControllerTest {
    Task model;
    TaskView view;
    TaskController controller;
    PersistenceManager<Task> persistenceManager;

    @BeforeEach
    void setUp() {
        /* INITIALIZE VIEW */
        view = new TaskView();

        /* INITIALIZING PERSISTENCE MANAGER (REPOSITORY) */
        persistenceManager = new PersistenceManager<>(
                "tasks_test.json",
                new TaskSerializer(new UserSerializer())
        );

        /* INITIALIZE CONTROLLER */
        controller = new TaskController(model, view, persistenceManager);

        /* CLEARING FILE */
        persistenceManager.clearFile();

        /* ADDING A MOCK MODEL */
        controller.setModel(buildTask(1));
        // controller.create();
    }
    
    Task buildTask(Integer id) {
        return new Task(
                id,
                1,
                "Task",
                "Task (desc)",
                new User(
                        id,
                        "Enzo Lopez",
                        "Enzo" + Math.round(Math.random() * 10) + "@gmail.com",
                        "123",
                        true,
                        Role.BACKEND_ENGINEER
                ),
                LocalDate.now(),
                LocalDate.of(2026, 12, 31),
                State.PENDING
        );
    }

    /* TEST CASE 001 */
    @Test
    void shouldPersistANewFreshTask() {
        /* WHEN */
        /* THEN */
        assertDoesNotThrow(() -> controller.create(), "ERROR ON CREATE");
        assertTrue(persistenceManager.isModelPersisted(1));
    }

    /* TEST CASE 002 */
    @Test
    void shouldThrowAnExceptionWhenPersistingDuplicatedTask() {
        /* GIVEN */
        controller.setModel(buildTask(1));

        controller.create();

        /* WHEN */
        /* THEN */
        assertThrows(RuntimeException.class, () -> controller.create());
    }

    /* TEST CASE 003 */
    @Test
    void shouldPersistADifferentTask() {
        /* GIVEN */
        controller.setModel(buildTask(1));

        controller.create();

        controller.setModel(buildTask(2));

        /* WHEN */
        /* THEN */
        assertDoesNotThrow(() -> controller.create(), "ERROR ON CREATE");
    }

    /* TEST CASE 004 */
    @Test
    void shouldReadAPersistedTask() {
        /* GIVEN */
        controller.setModel(buildTask(1));

        controller.create();

        /* WHEN */
        /* THEN */
        assertDoesNotThrow(() -> controller.read(), "ERROR ON READ");
        assertTrue(persistenceManager.isModelPersisted(1));
    }

    /* TEST CASE 005 */
    @Test
    void shouldThrowAnExceptionWhenReadingAnUnpersistedTask() {
        /* GIVEN */
        controller.setModel(buildTask(1));

        controller.create();

        controller.setModel(buildTask(2));

        /* WHEN */
        /* THEN */
        assertThrows(RuntimeException.class, () -> controller.read());
    }

    /* TEST CASE 006 */
    @Test
    void shouldUpdateAPersistedTask() {
        /* GIVEN */
        controller.setModel(buildTask(1));

        controller.create();

        Task newModel = buildTask(1);

        newModel.setTitle("Task Update");
        newModel.setExpectedDeadline(LocalDate.of(2027, 12, 31));
        newModel.setState(State.FINISHED);

        /* WHEN */
        /* THEN */
        assertDoesNotThrow(() -> controller.update(newModel), "ERROR ON UPDATE");
        assertTrue(persistenceManager.isModelPersisted(1));
    }

    /* TEST CASE 007 */
    @Test
    void shouldThrowAnExceptionWhenUpdatingAnUnpersistedTask() {
        /* GIVEN */
        controller.setModel(buildTask(1));

        controller.create();

        Task newModel = buildTask(2);

        /* WHEN */
        /* THEN */
        assertThrows(RuntimeException.class, () -> controller.update(newModel));
    }

    /* TEST CASE 008 */
    @Test
    void shouldDeletePersistedTask() {
        /* GIVEN */
        controller.setModel(buildTask(1));

        controller.create();

        /* WHEN */
        /* THEN */
        assertDoesNotThrow(() -> controller.delete(), "ERROR ON DELETE");
        assertTrue(persistenceManager.isModelPersisted(1));
    }

    /* TEST CASE 009 */
    @Test
    void shouldThrowAnExceptionWhenDeletingAnUnpersistedTask() {
        /* GIVEN */
        controller.setModel(buildTask(1));

        controller.create();

        controller.setModel(buildTask(2));

        /* WHEN */
        /* THEN */
        assertThrows(RuntimeException.class, () -> controller.delete());
    }

    /* TEST CASE 010 */
    @Test
    void shouldThrowAnExceptionWhenPersistingAnEmptyTask() {
        /* GIVEN */
        controller.setModel(new Task(
                1,
                1,
                "",
                "",
                buildTask(1).getResponsible(),
                LocalDate.now(),
                LocalDate.now(),
                State.PENDING
        ));

        /* WHEN */
        /* THEN */
        assertThrows(RuntimeException.class, () -> controller.create());
        assertFalse(persistenceManager.isModelPersisted(1));
    }

    /* TEST CASE 011 */
    @Test
    void shouldThrowAnExceptionWhenPersistingANegativeProjectId() {
        /* GIVEN */
        Task model = buildTask(2);

        model.setProjectId(-2);

        controller.setModel(model);

        /* WHEN */
        /* THEN */
        assertThrows(RuntimeException.class, () -> controller.create());
        assertFalse(persistenceManager.isModelPersisted(1));
    }

    /* TEST CASE 012 */
    @Test
    void shouldThrowAnExceptionWhenPersistingAnInvalidCreationDateOrExpectedDeadline() {
        /* GIVEN */
        Task model = buildTask(2);

        model.setCreationDate(LocalDate.of(2025, 6, 18));
        model.setExpectedDeadline(LocalDate.of(2025, 6, 18));

        controller.setModel(model);

        /* WHEN */
        /* THEN */
        assertThrows(RuntimeException.class, () -> controller.create());
        assertFalse(persistenceManager.isModelPersisted(2));
    }
}