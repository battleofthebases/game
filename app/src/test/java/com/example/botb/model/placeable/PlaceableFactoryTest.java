package com.example.botb.model.placeable;

import org.junit.Test;
import static org.junit.Assert.*;

public class PlaceableFactoryTest {

    @Test
    public void testExamplePlaceable() {
        Placeable placeable = PlaceableFactory.getPlaceable("example");
        assertTrue(placeable instanceof ExamplePlaceable);
    }

}
