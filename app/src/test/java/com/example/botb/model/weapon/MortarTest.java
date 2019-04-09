package com.example.botb.model.weapon;

import static org.junit.Assert.*;

import com.example.botb.model.Board;
import org.junit.*;

public class MortarTest {

    private Board board;

    private Weapon weapon;

    @Before
    public void initialize() {
        weapon = new Mortar();
    }

    @Test
    public void testName() {
        assertEquals(weapon.getName(), "Mortar");
    }

    @Test
    public void testToString() {
        // Need coverage bro
        assertEquals(weapon.toString(), "Weapon: " + weapon.getName());
    }

}
