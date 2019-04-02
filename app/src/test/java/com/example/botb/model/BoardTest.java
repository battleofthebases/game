package com.example.botb.model;

import com.example.botb.model.placeable.ExamplePlaceable;
import com.example.botb.model.placeable.Placeable;
import com.example.botb.model.weapon.ExampleWeapon;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class BoardTest {

    private Board board;

    @Before
    public void initialize() {
        Map<Location, Placeable> placeables = new HashMap<>();
        placeables.put(new Location(1,1), new ExamplePlaceable());
        placeables.put(new Location(1,2), new ExamplePlaceable());
        placeables.put(new Location(2,3), new ExamplePlaceable());
        placeables.put(new Location(3,4), new ExamplePlaceable());
        board = new Board(4, 4, placeables);
    }

    @Test
    public void testAddPlaceable() {
        Placeable placeable = new ExamplePlaceable();
        assertFalse(board.addPlaceable(placeable, new Location(1,1)));
        assertTrue(board.addPlaceable(placeable, 2, 1));
        assertEquals(board.getPlaceable(2, 1), placeable);
    }

    @Test
    public void testMovePlaceable1() {
        Location from = new Location(1,1);
        Location to = new Location(2,1);
        assertTrue(board.isLocationAvailable(to));
        assertTrue(board.movePlaceable(from, to));
        assertFalse(board.isLocationAvailable(to));
        assertNull(board.getPlaceable(from));
    }

    @Test
    public void testMovePlaceable2() {
        Location from = new Location(1,1);
        Location to = new Location(3,4);
        assertFalse(board.movePlaceable(from, to));
    }

    @Test
    public void testApplyAction() {
        Placeable placeable = board.getPlaceable(1,1);
        assertFalse(placeable.isDestroyed());
        Action action = new Action(new Location(1,1), new ExampleWeapon());
        board.applyAction(action);
        assertTrue(placeable.isDestroyed());
    }

    @Test
    public void testGetters() {
        assertEquals(board.getWidth(), 4);
        assertEquals(board.getHeight(), 4);
    }

    @Test public void testLocationValidAndAvailable() {
        assertFalse(board.isLocationValid(new Location(4,5)));
        assertTrue(board.isLocationValid(new Location(2,3)));
        assertFalse(board.isLocationAvailable(new Location(1,2)));
        assertTrue(board.isLocationAvailable(new Location(3,3)));
    }

}
