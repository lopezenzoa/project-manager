package org.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project.model.Project;
import org.project.model.Task;
import org.project.model.User;
import org.project.model.enums.Role;
import org.project.model.enums.State;
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
    ProjectSerializer serializer;
    ProjectController projectController;
    LinkedList<Task> tasks;
    HashMap<Integer, User> team;

    @BeforeEach
    void setUp() {
        /* INITIALIZE VIEW */
        view = new ProjectView();

        /* INITIALIZE SERIALIZER */
        serializer = new ProjectSerializer(new UserSerializer(), new TaskSerializer(new UserSerializer()));

        /* INITIALIZE CONTROLLER */
        projectController = new ProjectController(model, view, serializer);

        /* INITIALIZE TEAM */
        team = new HashMap<>();

        /* INITIALIZE TASKS */
        tasks = new LinkedList<>();

        /* INITIALIZE MOCK RESPONSIBLE */
    }

    @Test
    void shouldWriteGivenModelToAFile() {
        /* GIVEN */
        model = new Project(
                1,
                team,
                tasks,
                "Project 1",
                LocalDate.now(),
                LocalDate.of(2026, 12, 31),
                true
        );

        projectController.setModel(model);

        /* WHEN */
        assertDoesNotThrow(() -> projectController.create(), "Message");
    }


    @Test
    void shouldAddGivenModelToAFile() {
        /* GIVEN */
        model = new Project(
                2,
                team,
                tasks,
                "Project 2",
                LocalDate.now(),
                LocalDate.of(2026, 12, 31),
                true
        );

        projectController.setModel(model);

        assertDoesNotThrow(() -> projectController.create(), "Message");
    }

    @Test
    void shouldDoNotModifyTheFileWhenAddingADuplicate() {
        /* GIVEN */
        model = new Project(
                2,
                team,
                tasks,
                "Project 2",
                LocalDate.now(),
                LocalDate.of(2026, 12, 31),
                true
        );

        projectController.setModel(model);

        assertDoesNotThrow(() -> projectController.create(), "Message");
    }

    @Test
    void shouldReadGivenModelFromFile() {
        /* GIVEN */
        model = new Project(
                2,
                team,
                tasks,
                "Project 2",
                LocalDate.now(),
                LocalDate.of(2026, 12, 31),
                true
        );

        projectController.setModel(model);

        assertDoesNotThrow(() -> projectController.read(), "Message");
    }

    @Test
    void shouldDoNothingWhenReadingUnsavedModel() {
        /* GIVEN */
        model = new Project(
                6,
                team,
                tasks,
                "Project 2",
                LocalDate.now(),
                LocalDate.of(2026, 12, 31),
                true
        );

        projectController.setModel(model);

        assertDoesNotThrow(() -> projectController.read(), "Message");
    }

    @Test
    void shouldUpdateModelOnFile() {
        /* GIVEN */
        model = new Project(
                2,
                team,
                tasks,
                "Project 2",
                LocalDate.now(),
                LocalDate.of(2026, 12, 31),
                true
        );

        User user = new User(
                1,
                "Enzo López",
                "enzo@gmail.com",
                "123",
                true,
                Role.BACKEND_ENGINEER
        );

        Task task = new Task(
                2,
                1,
                "Do Something 2",
                "Do Something Desc 2",
                user,
                LocalDate.of(2026, 6, 14),
                LocalDate.of(2026, 12, 31),
                State.PENDING
        );

        projectController.setModel(model);

        Project newModel = model;

        team.put(user.getId(), user);
        tasks.add(task);

        newModel.setTeam(team);
        newModel.setTasks(tasks);

        assertDoesNotThrow(() -> projectController.update(newModel), "Message");
    }

    @Test
    void shouldDoNothingWhenUpdatingUnsavedModel() {
        /* GIVEN */
        model = new Project(
                6,
                team,
                tasks,
                "Project 2",
                LocalDate.now(),
                LocalDate.of(2026, 12, 31),
                true
        );

        User user = new User(
                1,
                "Enzo López",
                "enzo@gmail.com",
                "123",
                true,
                Role.BACKEND_ENGINEER
        );

        Task task = new Task(
                2,
                1,
                "Do Something 2",
                "Do Something Desc 2",
                user,
                LocalDate.of(2026, 6, 14),
                LocalDate.of(2026, 12, 31),
                State.PENDING
        );

        Task task2 = new Task(
                2,
                1,
                "Do Something 2",
                "Do Something Desc 2",
                user,
                LocalDate.of(2026, 6, 14),
                LocalDate.of(2026, 12, 31),
                State.PENDING
        );

        projectController.setModel(model);

        Project newModel = model;

        team.put(user.getId(), user);
        tasks.add(task);
        tasks.add(task2);

        newModel.setTeam(team);
        newModel.setTasks(tasks);

        assertDoesNotThrow(() -> projectController.update(newModel), "Message");
    }

    @Test
    void shouldDeleteModelFromFile() {
        /* GIVEN */
        model = new Project(
                2,
                team,
                tasks,
                "Project 2",
                LocalDate.now(),
                LocalDate.of(2026, 12, 31),
                true
        );

        projectController.setModel(model);

        assertDoesNotThrow(() -> projectController.delete(), "Message");
    }
}