package org.project.managers;

import org.junit.jupiter.api.AfterEach;
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

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PersistenceManagerTest {
    PersistenceManager<User> userPersistenceManager;
    PersistenceManager<Task> taskPersistenceManager;
    PersistenceManager<Project> projectPersistenceManager;

    User user;
    Task task;
    Project project;

    /* FOR TC-010 */
    User user1;
    Task task1;
    Project project1;

    @BeforeEach
    void setUp() {
        /* INITIALIZE USER */
        user = new User(
                1,
                "Enzo López",
                "enzo@gmail.com",
                "123",
                true,
                Role.BACKEND_ENGINEER
        );

        user1 = new User(
                2,
                "Enzo López 1",
                "enzo@gmail.com 1",
                "123 1",
                true,
                Role.PROJECT_OWNER
        );

        /* INITIALIZE TASK */
        task = new Task(
                1,
                1,
                "Do Something",
                "Do Something (desc)",
                user,
                LocalDate.now(),
                LocalDate.of(2026, 12, 31),
                State.PENDING
        );

        task1 = new Task(
                2,
                1,
                "Do Something 1",
                "Do Something (desc) 1",
                user1,
                LocalDate.now(),
                LocalDate.of(2026, 12, 31),
                State.PENDING
        );

        /* INITIALIZE PROJECT */
        project = new Project(
                1,
                new HashMap<>(),
                new LinkedList<>(),
                "Project",
                LocalDate.now(),
                LocalDate.of(2026, 12, 31),
                true
        );

        project1 = new Project(
                2,
                new HashMap<>(),
                new LinkedList<>(),
                "Project 1",
                LocalDate.now(),
                LocalDate.of(2026, 12, 31),
                true
        );

        /* INITIALIZE PERSISTENCE MANAGERS */
        userPersistenceManager = new PersistenceManager<>(
                "users.json",
                new UserSerializer()
        );

        taskPersistenceManager = new PersistenceManager<>(
                "tasks.json",
                new TaskSerializer(new UserSerializer())
        );

        projectPersistenceManager = new PersistenceManager<>(
                "projects.json",
                new ProjectSerializer(new UserSerializer(), new TaskSerializer(new UserSerializer()))
        );

        /* CLEARING FILES */
        userPersistenceManager.clearFile();
        taskPersistenceManager.clearFile();
        projectPersistenceManager.clearFile();
    }

    /* TEST CASE 001 */
    @Test
    void assertFalseForAllThreeUnsavedModels() {
        /* GIVEN */
        userPersistenceManager.addModelToPersistence(user.getId(), user);
        taskPersistenceManager.addModelToPersistence(task.getId(), task);
        projectPersistenceManager.addModelToPersistence(project.getId(), project);

        Integer sampleUnsavedId = 10_000;

        /* WHEN */
        /* THEN */
        assertFalse(userPersistenceManager.isModelPersisted(sampleUnsavedId));
        assertFalse(taskPersistenceManager.isModelPersisted(sampleUnsavedId));
        assertFalse(projectPersistenceManager.isModelPersisted(sampleUnsavedId));
    }

    /* TEST CASE 002 */
    @Test
    void assertTrueForAllThreeSavedModels() {
        /* GIVEN */
        userPersistenceManager.addModelToPersistence(user.getId(), user);
        taskPersistenceManager.addModelToPersistence(task.getId(), task);
        projectPersistenceManager.addModelToPersistence(project.getId(), project);

        /* WHEN */
        /* THEN */
        assertTrue(userPersistenceManager.isModelPersisted(user.getId()));
        assertTrue(taskPersistenceManager.isModelPersisted(task.getId()));
        assertTrue(projectPersistenceManager.isModelPersisted(project.getId()));
    }

    /* TEST CASE 003 */
    @Test
    void assertNullForAllThreeUnsavedModels() {
        /* GIVEN */
        userPersistenceManager.addModelToPersistence(user.getId(), user);
        taskPersistenceManager.addModelToPersistence(task.getId(), task);
        projectPersistenceManager.addModelToPersistence(project.getId(), project);
        Integer sampleUnsavedId = 10_000;

        /* WHEN */
        /* THEN */
        assertNull(userPersistenceManager.getModelFromPersistence(sampleUnsavedId));
        assertNull(taskPersistenceManager.getModelFromPersistence(sampleUnsavedId));
        assertNull(projectPersistenceManager.getModelFromPersistence(sampleUnsavedId));
    }

    /* TEST CASE 004 */
    @Test
    void assertModelForAllThreeSavedModels() {
        /* GIVEN */
        userPersistenceManager.addModelToPersistence(user.getId(), user);
        taskPersistenceManager.addModelToPersistence(task.getId(), task);
        projectPersistenceManager.addModelToPersistence(project.getId(), project);

        /* WHEN */
        User persistedUser = userPersistenceManager.getModelFromPersistence(user.getId());
        Task persistedTask = taskPersistenceManager.getModelFromPersistence(task.getId());
        Project persistedProject = projectPersistenceManager.getModelFromPersistence(project.getId());

        /* THEN */
        assertEquals(persistedUser, user);
        assertEquals(persistedTask, task);
        assertEquals(persistedProject, project);
    }

    /* TEST CASE 005 */
    @Test
    void assertEqualForAllModels() {
        /* GIVEN */
        List<User> expectedUsers = new ArrayList<>(Collections.singletonList(user));
        List<Task> expectedTasks = new ArrayList<>(Collections.singletonList(task));
        List<Project> expectedProjects = new ArrayList<>(Collections.singletonList(project));

        userPersistenceManager.addModelToPersistence(user.getId(), user);
        taskPersistenceManager.addModelToPersistence(task.getId(), task);
        projectPersistenceManager.addModelToPersistence(project.getId(), project);

        /* WHEN */
        /* THEN */
        assertEquals(expectedUsers, userPersistenceManager.getAllModelsFromPersistence());
        assertEquals(expectedTasks, taskPersistenceManager.getAllModelsFromPersistence());
        assertEquals(expectedProjects, projectPersistenceManager.getAllModelsFromPersistence());
    }

    /* TEST CASE 006 */
    @Test
    void assertEqualForNoSavedModels() {
        /* GIVEN */
        List<User> expectedUsers = new ArrayList<>();
        List<Task> expectedTasks = new ArrayList<>();
        List<Project> expectedProjects = new ArrayList<>();

        /* WHEN */
        /* THEN */
        assertEquals(expectedUsers, userPersistenceManager.getAllModelsFromPersistence());
        assertEquals(expectedTasks, taskPersistenceManager.getAllModelsFromPersistence());
        assertEquals(expectedProjects, projectPersistenceManager.getAllModelsFromPersistence());
    }

    /* TEST CASE 007 */
    @Test
    void assertEqualWhenAddingAllThreeModels() {
        /* GIVEN */
        /* WHEN */
        /* THEN */
        assertEquals(
                userPersistenceManager.addModelToPersistence(user.getId(), user),
                user
        );

        assertEquals(
                taskPersistenceManager.addModelToPersistence(task.getId(), task),
                task
        );

        assertEquals(
                projectPersistenceManager.addModelToPersistence(project.getId(), project),
                project
        );
    }

    /* TEST CASE 008 */
    @Test
    void assertThrowsAnExceptionWhenAddingAllThreeDuplicatedModels() {
        /* GIVEN */
        userPersistenceManager.addModelToPersistence(user.getId(), user);
        taskPersistenceManager.addModelToPersistence(task.getId(), task);
        projectPersistenceManager.addModelToPersistence(project.getId(), project);

        /* WHEN */
        /* THEN */
        assertThrows(
                RuntimeException.class,
                () -> userPersistenceManager.addModelToPersistence(user.getId(), user)
        );

        assertThrows(
                RuntimeException.class,
                () -> taskPersistenceManager.addModelToPersistence(task.getId(), task)
        );

        assertThrows(
                RuntimeException.class,
                () -> projectPersistenceManager.addModelToPersistence(project.getId(), project)
        );
    }

    /* TEST CASE 009 */
    @Test
    void assertEqualModelWhenUpdatingAllThreeModels() {
        /* GIVEN */
        userPersistenceManager.addModelToPersistence(user.getId(), user);
        taskPersistenceManager.addModelToPersistence(task.getId(), task);
        projectPersistenceManager.addModelToPersistence(project.getId(), project);

        user.setRole(Role.PROJECT_OWNER);
        task.setState(State.FINISHED);
        project.setExpectedDeadline(LocalDate.of(2027, 12, 31));

        /* WHEN */
        /* THEN */
        assertEquals(
                userPersistenceManager.updateModelInPersistence(user.getId(), user),
                user
        );

        assertEquals(
                taskPersistenceManager.updateModelInPersistence(task.getId(), task),
                task
        );

        assertEquals(
                projectPersistenceManager.updateModelInPersistence(project.getId(), project),
                project
        );
    }
}