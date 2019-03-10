package com.example.botb.model;

import com.example.botb.model.weapon.Weapon;

public class Action {

    private Location location;
    private Weapon weapon;

    public Action(Location location, Weapon weapon) {
        this.location = location;
        this.weapon = weapon;
    }

    public Location getLocation() {
        return location;
    }

    public Weapon getWeapon() {
        return weapon;
    }

}
