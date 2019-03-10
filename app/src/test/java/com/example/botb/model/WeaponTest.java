package com.example.botb.model;

import com.example.botb.model.placable.ExamplePlaceable;
import com.example.botb.model.placable.Placeable;
import com.example.botb.model.weapon.ExampleWeapon;
import com.example.botb.model.weapon.Weapon;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class WeaponTest {

    private Weapon weapon;
    private Board board;

    @Before
    public void initialize() {
        weapon = new ExampleWeapon();
        board = new Board(4, 4);
        board.addPlacable(new ExamplePlaceable(), 2, 2);
    }

    @Test
    public void testWeaponDestroyPlaceable1() {
        Location location = new Location(2, 2);
        Placeable placeable = board.getPlacable(location);
        assertFalse(placeable.isDestroyed());
        weapon.applyToBoard(board, location);
        assertTrue(placeable.isDestroyed());
    }

    @Test
    public void testWeaponDestroyPlaceable2() {
        Placeable placeable = board.getPlacable(2, 2);
        assertFalse(placeable.isDestroyed());
        weapon.applyToBoard(board, 2, 2);
        assertTrue(placeable.isDestroyed());
    }

}