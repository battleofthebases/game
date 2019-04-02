package com.example.botb.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LocationTest {

    private Location location;

    @Before
    public void initialize() {
        location = new Location(3, 5);
    }

    @Test
    public void testGetters() {
        assertEquals(location.getX(), 3);
        assertEquals(location.getY(), 5);
    }

    @Test
    public void testEqualsAndHashCode() {
        Location loc1 = new Location(7, 6);
        Location loc2 = new Location(7, 6);
        assertTrue(loc1.equals(loc2) && loc2.equals(loc1));
        assertFalse(location.equals(loc1));
        assertTrue(loc1.hashCode() == loc2.hashCode());
    }

}
