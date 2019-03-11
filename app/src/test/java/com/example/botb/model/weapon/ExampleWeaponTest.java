package com.example.botb.model.weapon;

import com.example.botb.model.Board;
import com.example.botb.model.Location;
import com.example.botb.model.placeable.ExamplePlaceable;
import com.example.botb.model.placeable.Placeable;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExampleWeaponTest {

    private Weapon weapon;
    private Board board;

    @Before
    public void initialize() {
        weapon = new ExampleWeapon();
        board = new Board(4, 4);
        board.addPlaceable(new ExamplePlaceable(), 2, 2);
    }

    @Test
    public void testDestroyPlaceable1() {
        Location location = new Location(2, 2);
        Placeable placeable = board.getPlaceable(location);
        assertFalse(placeable.isDestroyed());
        weapon.applyToBoard(board, location);
        assertTrue(placeable.isDestroyed());
    }

    @Test
    public void testDestroyPlaceable2() {
        Placeable placeable = board.getPlaceable(2, 2);
        assertFalse(placeable.isDestroyed());
        weapon.applyToBoard(board, 2, 2);
        assertTrue(placeable.isDestroyed());
    }

    @Test
    public void testToString() {
        // Need coverage bro
        assertEquals(weapon.toString(), "Weapon " + weapon.getWeaponType());
    }

}