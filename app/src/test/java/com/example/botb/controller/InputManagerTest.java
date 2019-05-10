package com.example.botb.controller;

import static org.junit.Assert.*;

import com.example.botb.controller.InputManager;
import com.example.botb.model.Board;
import org.junit.*;

public class InputManagerTest {

    @Test
    public void connectToServer() {
        final InputManager instance = InputManager.getInstance();
        instance.connectToServer();
    }

    @Test
    public void getGame() {
        final InputManager instance = InputManager.getInstance();
        assertNull(instance.getGame());
    }

    @Test
    public void getInstance() {
        final InputManager instance = InputManager.getInstance();
        assertNotNull(instance);
    }

    @Test
    public void getLocalBoard() {
        final InputManager instance = InputManager.getInstance();
        final Board localBoard = instance.getLocalBoard();
        assertEquals(localBoard.getHeight(), 8);
        assertEquals(localBoard.getWidth(), 10);
    }

    @Test
    public void getRemoteBoard() {
        final InputManager instance = InputManager.getInstance();
        final Board remoteBoard = instance.getRemoteBoard();
        assertNull(remoteBoard);
    }

    @Test
    public void handleLocalAction() {
    }

    @Test
    public void handleRemoteAction() {
    }

    @Test
    public void setInitialLocalBoard() {
    }

    @Test
    public void setInitialRemoteBoard() {
    }

    @Test
    public void subscribe() {
    }

    @Test
    public void subscriptionEvent() {
    }

    @Test
    public void unsubscribe() {
    }
}