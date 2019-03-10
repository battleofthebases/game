package com.example.botb.model.weapon;

public class WeaponFactory {

    public static Weapon getWeapon(int id) {

        switch (id) {
            case 1:
                return new ExampleWeapon(1);
        }

        return null;
    }

}
