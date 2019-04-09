package com.example.botb.model;

import static org.junit.Assert.*;

import com.example.botb.model.weapon.ExampleWeapon;
import com.example.botb.model.weapon.Weapon;
import org.junit.*;

public class ActionTest {

    private Action action;

    private Location location;

    private Weapon weapon;

    @Before
    public void initialize() {
        location = new Location(3, 4);
        weapon = new ExampleWeapon();
        action = new Action(location, weapon);
    }

    @Test
    public void testGetters() {
        assertEquals(action.getLocation(), location);
        assertEquals(action.getWeapon(), weapon);
    }

    @Test
    public void testToString() {
        String expected = String.format("Action: %s at %s", weapon, location);
        assertEquals(action.toString(), expected);
    }

}
