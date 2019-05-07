package com.example.botb.controller;

import com.example.botb.model.Action;
import com.example.botb.model.Location;
import com.example.botb.model.weapon.ExampleWeapon;
import org.junit.*;

public class GameModelControllerTest {

    @Test(expected = NullPointerException.class)
    public void applyAction() throws NullPointerException {
        GameModelController gameModelController = new GameModelController();
        gameModelController.applyAction(true, new Action(new Location(6, 6), new ExampleWeapon()));
        //TODO: SetBoard and test applied action
    }

    @Test
    public void getGame() {
    }

    @Test
    public void getLocalBoard() {
    }

    @Test
    public void getRemoteBoard() {
    }

    @Test
    public void setInitialLocalBoard() {
    }

    @Test
    public void setInitialRemoteBoard() {
    }

    @Test
    public void startGame() {
    }
}