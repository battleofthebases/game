package com.example.botb.model;

import com.example.botb.model.weapon.ExampleWeapon;
import com.example.botb.model.weapon.Weapon;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ActionTest {

    private Location location;
    private Weapon weapon;
    private Action action;

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
