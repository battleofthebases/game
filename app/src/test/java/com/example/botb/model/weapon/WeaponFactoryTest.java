package com.example.botb.model.weapon;

import org.junit.Test;
import static org.junit.Assert.*;

public class WeaponFactoryTest {

    @Test
    public void testExampleWeapon() {
        Weapon weapon = WeaponFactory.getWeapon("example");
        assertTrue(weapon instanceof ExampleWeapon);
    }

}
