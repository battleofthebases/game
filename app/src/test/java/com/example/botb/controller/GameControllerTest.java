package com.example.botb.controller;

import com.example.botb.model.Action;
import com.example.botb.model.Location;
import com.example.botb.model.weapon.ExampleWeapon;
import org.junit.*;

public class GameControllerTest {

    @Test
    public void applyAction() {
        GameController gameController = new GameController();
        gameController.applyAction(true, new Action(new Location(6, 6), new ExampleWeapon()));
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