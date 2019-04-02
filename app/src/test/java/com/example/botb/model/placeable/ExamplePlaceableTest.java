package com.example.botb.model.placeable;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExamplePlaceableTest {

    private Placeable placeable;

    @Before
    public void initialize() {
        placeable = new ExamplePlaceable();
    }

    @Test
    public void testDamage() {
        assertEquals(placeable.health, 100);
        placeable.damage(10);
        assertEquals(placeable.health, 110);
    }

    @Test
    public void testDestroy() {
        assertFalse(placeable.isDestroyed());
        placeable.destroy();
        assertTrue(placeable.isDestroyed());
    }

    @Test
    public void testVisibility() {
        assertEquals(placeable.getVisibility(), Visibility.HIDDEN);
        placeable.setVisibility(Visibility.VISIBLE);
        assertEquals(placeable.getVisibility(), Visibility.VISIBLE);
    }

    @Test
    public void testHealth() {
        assertEquals(placeable.getHealth(), 100);
        placeable.setHealth(50);
        assertEquals(placeable.getHealth(), 50);
    }

    @Test
    public void testName() {
        assertEquals(placeable.getName(), "ExamplePlaceable");
    }

}
