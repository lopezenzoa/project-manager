package org.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project.model.User;
import org.project.model.enums.Role;
import org.project.serializers.UserSerializer;
import org.project.view.UserView;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class UserControllerTest {
    User model;
    UserView view;
    UserSerializer serializer;
    UserController userController;

    @BeforeEach
    void setUp() {
        /* INITIALIZE VIEW */
        view = new UserView();

        /* INITIALIZE SERIALIZER */
        serializer = new UserSerializer();

        /* INITIALIZE CONTROLLER */
        userController = new UserController(model, view, serializer);
    }

    @Test
    void shouldWriteGivenModelToAFile() {
        /* GIVEN */
        model = new User(
                1,
                "Enzo López",
                "enzo@gmail.com",
                "123",
                true,
                Role.BACKEND_ENGINEER
        );

        userController.setModel(model);

        /* WHEN */
        assertDoesNotThrow(() -> userController.create(), "Message");
    }

    @Test
    void shouldAddGivenModelToAFile() {
        /* GIVEN */
        model = new User(
                2,
                "Enzo López",
                "enzo@gmail.com",
                "123",
                true,
                Role.BACKEND_ENGINEER
        );

        userController.setModel(model);

        assertDoesNotThrow(() -> userController.create(), "Message");
    }

    @Test
    void shouldDoNotModifyTheFileWhenAddingADuplicate() {
        /* GIVEN */
        model = new User(
                2,
                "Enzo López",
                "enzo@gmail.com",
                "123",
                true,
                Role.BACKEND_ENGINEER
        );

        userController.setModel(model);

        assertDoesNotThrow(() -> userController.create(), "Message");
    }

    @Test
    void shouldReadGivenModelFromFile() {
        /* GIVEN */
        model = new User(
                1,
                "Enzo López",
                "enzo@gmail.com",
                "123",
                true,
                Role.BACKEND_ENGINEER
        );

        userController.setModel(model);

        assertDoesNotThrow(() -> userController.read(), "Message");
    }

    @Test
    void shouldDoNothingWhenReadingUnsavedModel() {
        /* GIVEN */
        model = new User(
                5,
                "Enzo López",
                "enzo@gmail.com",
                "123",
                true,
                Role.BACKEND_ENGINEER
        );

        userController.setModel(model);

        assertDoesNotThrow(() -> userController.read(), "Message");
    }

    @Test
    void shouldUpdateModelOnFile() {
        /* GIVEN */
        model = new User(
                1,
                "Enzo López",
                "enzo@gmail.com",
                "123",
                true,
                Role.BACKEND_ENGINEER
        );

        userController.setModel(model);

        User newModel = model;

        newModel.setName("Enzo Agustín López");
        newModel.setEmail("enzo@gmail.com");
        newModel.setPassword("12334");

        assertDoesNotThrow(() -> userController.update(newModel), "Message");
    }

    @Test
    void shouldDoNothingWhenUpdatingUnsavedModel() {
        /* GIVEN */
        model = new User(
                5,
                "Enzo López",
                "enzo@gmail.com",
                "123",
                true,
                Role.BACKEND_ENGINEER
        );

        userController.setModel(model);

        User newModel = model;

        newModel.setName("Enzo López");
        newModel.setEmail("enzo@gmail.com");
        newModel.setPassword("12334");

        assertDoesNotThrow(() -> userController.update(newModel), "Message");
    }

    @Test
    void shouldDeleteModelFromFile() {
        /* GIVEN */
        model = new User(
                1,
                "Enzo López",
                "enzo@gmail.com",
                "123",
                true,
                Role.BACKEND_ENGINEER
        );

        userController.setModel(model);

        assertDoesNotThrow(() -> userController.delete(), "Message");
    }
}