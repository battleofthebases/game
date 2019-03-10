package com.example.botb.model.weapon;

public class WeaponFactory {

    public static Weapon getWeapon(String weaponId) {

        switch (weaponId) {

            case "example":
                return new ExampleWeapon();

        }

        return null;
    }

}
