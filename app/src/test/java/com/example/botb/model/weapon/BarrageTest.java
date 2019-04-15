package com.example.botb.model.weapon;

import static org.junit.Assert.*;

import com.example.botb.model.Board;
import com.example.botb.model.placeable.ExamplePlaceable;
import org.junit.*;

public class BarrageTest {

    private Board board;

    private Weapon weapon;

    @Before
    public void initialize() {
        weapon = new Barrage();
        board = new Board(10, 10);
        board.addPlaceable(new ExamplePlaceable(), 5, 5);
        board.addPlaceable(new ExamplePlaceable(), 5, 6);
        board.addPlaceable(new ExamplePlaceable(), 4, 4);
        board.addPlaceable(new ExamplePlaceable(), 3, 3);
        board.addPlaceable(new ExamplePlaceable(), 0, 9);
    }

    @Test
    public void testName() {
        assertEquals(weapon.getName(), "Barrage");
    }

    @Test
    public void testToString() {
        // Need coverage bro
        assertEquals(weapon.toString(), "Weapon: " + weapon.getName());
    }

    @Test
    public void testWeapon() {
        weapon.applyToBoard(board, 5, 4);
        assertTrue(board.getPlaceable(4, 4).isDestroyed());
        assertTrue(board.getPlaceable(5, 5).isDestroyed());
        assertFalse(board.getPlaceable(3, 3).isDestroyed());
        assertFalse(board.getPlaceable(5, 6).isDestroyed());
        assertFalse(board.getPlaceable(0, 9).isDestroyed());
    }

    @Test
    public void testWeaponOnEdge() {
        weapon.applyToBoard(board, 0, 9);
        assertTrue(board.getPlaceable(0, 9).isDestroyed());
        assertFalse(board.getPlaceable(4, 4).isDestroyed());
        assertFalse(board.getPlaceable(5, 5).isDestroyed());
        assertFalse(board.getPlaceable(3, 3).isDestroyed());
        assertFalse(board.getPlaceable(5, 6).isDestroyed());
    }

}
