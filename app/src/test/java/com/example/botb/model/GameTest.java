package com.example.botb.model;

import static org.junit.Assert.*;

import com.example.botb.model.weapon.ExampleWeapon;
import org.junit.*;

public class GameTest {

    private Game game;

    @Before
    public void initialize() {
        Board localBoard = new Board(4, 4);
        Board remoteBoard = new Board(4, 4);
        game = new Game(localBoard, remoteBoard, true);
    }

    @Test
    public void testApplyAction() {
        Action action = new Action(new Location(1, 1), new ExampleWeapon());
        assertTrue(game.isLocalTurn());
        assertFalse(game.applyAction(false, action));
        assertTrue(game.applyAction(true, action));
        assertFalse(game.isLocalTurn());
    }

}
