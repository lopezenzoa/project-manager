package org.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    TaskSerializer serializer;
    TaskController taskController;
    User responsible;

    @BeforeEach
    void setUp() {
        /* INITIALIZE VIEW */
        view = new TaskView();

        /* INITIALIZE SERIALIZER */
        serializer = new TaskSerializer(new UserSerializer());

        /* INITIALIZE CONTROLLER */
        taskController = new TaskController(model, view, serializer);

        /* INITIALIZE MOCK RESPONSIBLE */
        responsible = new User(
                1,
                "Enzo López",
                "enzo@gmail.com",
                "123",
                true,
                Role.BACKEND_ENGINEER
        );
    }

    @Test
    void shouldWriteGivenModelToAFile() {
        /* GIVEN */
        model = new Task(
                1,
                1,
                "Do Something",
                "Do Something Desc",
                responsible,
                LocalDate.now(),
                LocalDate.of(2026, 12, 31),
                State.PENDING
        );

        taskController.setModel(model);

        /* WHEN */
        assertDoesNotThrow(() -> taskController.create(), "Message");
    }


    @Test
    void shouldAddGivenModelToAFile() {
        model = new Task(
                2,
                1,
                "Do Something 2",
                "Do Something Desc 2",
                responsible,
                LocalDate.of(2026, 6, 14),
                LocalDate.of(2026, 12, 31),
                State.PENDING
        );

        taskController.setModel(model);

        assertDoesNotThrow(() -> taskController.create(), "Message");
    }

    @Test
    void shouldDoNotModifyTheFileWhenAddingADuplicate() {
        /* GIVEN */
        model = new Task(
                2,
                1,
                "Do Something 2",
                "Do Something Desc 2",
                responsible,
                LocalDate.of(2026, 6, 14),
                LocalDate.of(2026, 12, 31),
                State.PENDING
        );

        taskController.setModel(model);

        assertDoesNotThrow(() -> taskController.create(), "Message");
    }

    @Test
    void shouldReadGivenModelFromFile() {
        /* GIVEN */
        model = new Task(
                2,
                1,
                "Do Something 2",
                "Do Something Desc 2",
                responsible,
                LocalDate.of(2026, 6, 14),
                LocalDate.of(2026, 12, 31),
                State.PENDING
        );

        taskController.setModel(model);

        assertDoesNotThrow(() -> taskController.read(), "Message");
    }

    @Test
    void shouldDoNothingWhenReadingUnsavedModel() {
        /* GIVEN */
        model = new Task(
                6,
                1,
                "Do Something 2",
                "Do Something Desc 2",
                responsible,
                LocalDate.of(2026, 6, 14),
                LocalDate.of(2026, 12, 31),
                State.PENDING
        );

        taskController.setModel(model);

        assertDoesNotThrow(() -> taskController.read(), "Message");
    }

    @Test
    void shouldUpdateModelOnFile() {
        /* GIVEN */
        model = new Task(
                2,
                1,
                "Do Something 2",
                "Do Something Desc 2",
                responsible,
                LocalDate.of(2026, 6, 14),
                LocalDate.of(2026, 12, 31),
                State.PENDING
        );

        taskController.setModel(model);

        Task newModel = model;

        newModel.setTitle("Do Something 3");
        newModel.setExpectedDeadline(LocalDate.of(2027, 12, 31));

        assertDoesNotThrow(() -> taskController.update(newModel), "Message");
    }

    @Test
    void shouldDoNothingWhenUpdatingUnsavedModel() {
        /* GIVEN */
        model = new Task(
                6,
                1,
                "Do Something 2",
                "Do Something Desc 2",
                responsible,
                LocalDate.of(2026, 6, 14),
                LocalDate.of(2026, 12, 31),
                State.PENDING
        );

        taskController.setModel(model);

        Task newModel = model;

        newModel.setTitle("Do Something 3");
        newModel.setExpectedDeadline(LocalDate.of(2027, 12, 31));

        assertDoesNotThrow(() -> taskController.update(newModel), "Message");
    }

    @Test
    void shouldDeleteModelFromFile() {
        /* GIVEN */
        model = new Task(
                2,
                1,
                "Do Something 2",
                "Do Something Desc 2",
                responsible,
                LocalDate.of(2026, 6, 14),
                LocalDate.of(2026, 12, 31),
                State.PENDING
        );

        taskController.setModel(model);

        assertDoesNotThrow(() -> taskController.delete(), "Message");
    }
}