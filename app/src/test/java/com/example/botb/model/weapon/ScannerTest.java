package com.example.botb.model.weapon;

import static org.junit.Assert.*;

import com.example.botb.model.Board;
import com.example.botb.model.placeable.ExamplePlaceable;
import com.example.botb.model.placeable.Placeable;
import com.example.botb.model.placeable.Visibility;
import org.junit.*;

public class ScannerTest {

    private Board board;

    private Weapon weapon;

    @Before
    public void initialize() {
        weapon = new Scanner();
        board = new Board(10, 10);
        board.addPlaceable(new ExamplePlaceable(), 4, 2);
        board.addPlaceable(new ExamplePlaceable(), 6, 4);
        board.addPlaceable(new ExamplePlaceable(), 8, 4);
        board.addPlaceable(new ExamplePlaceable(), 4, 7);
        board.addPlaceable(new ExamplePlaceable(), 2, 8);
        board.addPlaceable(new ExamplePlaceable(), 0, 4);
    }

    @Test
    public void testName() {
        assertEquals(weapon.getName(), "Scanner");
    }

    @Test
    public void testToString() {
        // Need coverage bro
        assertEquals(weapon.toString(), "Weapon: " + weapon.getName());
    }

    @Test
    public void testWeapon() {
        weapon.applyToBoard(board, 4, 4);
        assertSame(board.getPlaceable(4, 2).getVisibility(), Visibility.DETECTED);
        assertSame(board.getPlaceable(6, 4).getVisibility(), Visibility.DETECTED);
        assertSame(board.getPlaceable(4, 7).getVisibility(), Visibility.DETECTED);
        assertSame(board.getPlaceable(0, 4).getVisibility(), Visibility.DETECTED);
        assertNotSame(board.getPlaceable(8, 4).getVisibility(), Visibility.DETECTED);
        assertNotSame(board.getPlaceable(2, 8).getVisibility(), Visibility.DETECTED);
    }

    @Test
    public void testWeaponOnPlaceable() {
        weapon.applyToBoard(board, 4, 2);
        assertSame(board.getPlaceable(4, 2).getVisibility(), Visibility.DETECTED);
        assertNotSame(board.getPlaceable(6, 4).getVisibility(), Visibility.DETECTED);
        assertNotSame(board.getPlaceable(4, 7).getVisibility(), Visibility.DETECTED);
        assertNotSame(board.getPlaceable(8, 4).getVisibility(), Visibility.DETECTED);
        assertNotSame(board.getPlaceable(2, 8).getVisibility(), Visibility.DETECTED);
        assertNotSame(board.getPlaceable(0, 4).getVisibility(), Visibility.DETECTED);
    }

    @Test
    public void testWeaponOnVisiblePlaceable() {
        Placeable placeable = board.getPlaceable(4, 2);
        placeable.setVisibility(Visibility.VISIBLE);
        weapon.applyToBoard(board, 4, 4);
        assertSame(placeable.getVisibility(), Visibility.VISIBLE);
    }

}
