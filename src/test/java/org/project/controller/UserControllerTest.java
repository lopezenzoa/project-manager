package org.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project.managers.PersistenceManager;
import org.project.model.User;
import org.project.model.enums.Role;
import org.project.serializers.UserSerializer;
import org.project.view.UserView;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    User model;
    UserView view;
    UserController controller;
    PersistenceManager<User> persistenceManager;

    @BeforeEach
    void setUp() {
        /* INITIALIZE VIEW */
        view = new UserView();

        /* INITIALIZING PERSISTENCE MANAGER (REPOSITORY) */
        persistenceManager = new PersistenceManager<>("users_test.json", new UserSerializer());

        /* INITIALIZE CONTROLLER */
        controller = new UserController(model, view, persistenceManager);

        /* CLEARING FILE */
        persistenceManager.clearFile();
    }

    User buildUser(Integer id) {
        return new User(
                id,
                "Enzo Lopez",
                "Enzo" + Math.round(Math.random() * 10) + "@gmail.com",
                "123",
                true,
                Role.BACKEND_ENGINEER
        );
    }

    /* TEST CASE 001 */
    @Test
    void shouldPersistANewFreshUser() {
        /* GIVEN */
        controller.setModel(buildUser(1));

        /* WHEN */
        /* THEN */
        assertDoesNotThrow(() -> controller.create(), "ERROR ON CREATE");
        assertTrue(persistenceManager.isModelPersisted(1));
    }

    /* TEST CASE 002 */
    @Test
    void shouldThrowAnExceptionWhenPersistingDuplicatedUser() {
        /* GIVEN */
        controller.setModel(buildUser(1));

        controller.create();

        /* WHEN */
        /* THEN */
        assertThrows(RuntimeException.class, () -> controller.create());
    }

    /* TEST CASE 003 */
    @Test
    void shouldPersistADifferentUser() {
        /* GIVEN */
        controller.setModel(buildUser(1));

        controller.create();

        controller.setModel(buildUser(2));

        /* WHEN */
        /* THEN */
        assertDoesNotThrow(() -> controller.create(), "ERROR ON CREATE");
    }

    /* TEST CASE 004 */
    @Test
    void shouldReadAPersistedUser() {
        /* GIVEN */
        controller.setModel(buildUser(1));

        controller.create();

        /* WHEN */
        /* THEN */
        assertDoesNotThrow(() -> controller.read(), "ERROR ON READ");
        assertTrue(persistenceManager.isModelPersisted(1));
    }

    /* TEST CASE 005 */
    @Test
    void shouldThrowAnExceptionWhenReadingAnUnpersistedUser() {
        /* GIVEN */
        controller.setModel(buildUser(1));

        controller.create();

        controller.setModel(buildUser(2));

        /* WHEN */
        /* THEN */
        assertThrows(RuntimeException.class, () -> controller.read());
    }

    /* TEST CASE 006 */
    @Test
    void shouldUpdateAPersistedUser() {
        /* GIVEN */
        controller.setModel(buildUser(1));

        controller.create();

        User newModel = buildUser(1);

        newModel.setName("Enzo Agustín López");
        newModel.setPassword("12334");
        newModel.setRole(Role.PROJECT_OWNER);

        /* WHEN */
        /* THEN */
        assertDoesNotThrow(() -> controller.update(newModel), "ERROR ON UPDATE");
        assertTrue(persistenceManager.isModelPersisted(1));
    }

    /* TEST CASE 007 */
    @Test
    void shouldThrowAnExceptionWhenUpdatingAnUnpersistedUser() {
        /* GIVEN */
        controller.setModel(buildUser(1));

        controller.create();

        User newModel = buildUser(2);

        /* WHEN */
        /* THEN */
        assertThrows(RuntimeException.class, () -> controller.update(newModel));
    }

    /* TEST CASE 008 */
    @Test
    void shouldDeletePersistedUser() {
        /* GIVEN */
        controller.setModel(buildUser(1));

        controller.create();

        /* WHEN */
        /* THEN */
        assertDoesNotThrow(() -> controller.delete(), "ERROR ON DELETE");
        assertTrue(persistenceManager.isModelPersisted(1));
    }

    /* TEST CASE 009 */
    @Test
    void shouldThrowAnExceptionWhenDeletingAnUnpersistedUser() {
        /* GIVEN */
        controller.setModel(buildUser(1));

        controller.create();

        controller.setModel(buildUser(2));

        /* WHEN */
        /* THEN */
        assertThrows(RuntimeException.class, () -> controller.delete());
    }

    /* TEST CASE 010 */
    @Test
    void shouldThrowAnExceptionWhenPersistingAnEmptyUser() {
        /* GIVEN */
        controller.setModel(new User(
                1,
                "",
                "",
                "",
                true,
                Role.BACKEND_ENGINEER
        ));

        /* WHEN */
        /* THEN */
        assertThrows(RuntimeException.class, () -> controller.create());
        assertFalse(persistenceManager.isModelPersisted(1));
    }

    /* TEST CASE 011 */
    @Test
    void shouldThrowAnExceptionWhenPersistingAnInvalidEmailForUser() {
        /* GIVEN */
        User model = buildUser(1);

        model.setEmail("enzoagustin");

        controller.setModel(model);

        /* WHEN */
        /* THEN */
        assertThrows(RuntimeException.class, () -> controller.create());
        assertFalse(persistenceManager.isModelPersisted(1));
    }

    /* TEST CASE 012 */
    @Test
    void shouldThrowAnExceptionWhenUpdatingAnEmptyUser() {
        /* GIVEN */
        controller.setModel(buildUser(1));

        controller.create();

        User newModel = buildUser(1);

        newModel.setName("");
        newModel.setEmail("");
        newModel.setPassword("");

        /* WHEN */
        /* THEN */
        assertThrows(RuntimeException.class, () -> controller.update(newModel));
        assertTrue(persistenceManager.isModelPersisted(1));
    }

    /* TEST CASE 013 */
    @Test
    void shouldThrowAnExceptionWhenUpdatingAnInvalidEmailForUser() {
        /* GIVEN */
        controller.setModel(buildUser(1));

        controller.create();

        User newModel = buildUser(1);

        newModel.setEmail("enzoagustin");

        /* WHEN */
        /* THEN */
        assertThrows(RuntimeException.class, () -> controller.update(newModel));
        assertTrue(persistenceManager.isModelPersisted(1));
    }

    /* TEST CASE 014 */
    @Test
    void shouldThrowAnExceptionWhenPersistingADuplicatedEmailForUser() {
        /* GIVEN */
        controller.setModel(buildUser(1));
        controller.create();

        User newModel = buildUser(2);

        newModel.setEmail(controller.getModel().getEmail());

        controller.setModel(newModel);

        /* WHEN */
        /* THEN */
        assertThrows(RuntimeException.class, () -> controller.create());
        assertFalse(persistenceManager.isModelPersisted(2));
    }

    /* TEST CASE 015 */
    @Test
    void shouldThrowAnExceptionWhenUpdatingADuplicatedEmailForUser() {
        /* GIVEN */
        User model_1 = buildUser(1);
        controller.setModel(model_1);
        controller.create();

        User model_2 = buildUser(2);
        controller.setModel(model_2);
        controller.create();

        controller.setModel(model_1);
        model_1.setEmail(model_2.getEmail());

        /* WHEN */
        /* THEN */
        assertThrows(RuntimeException.class, () -> controller.update(model_1));
    }
}