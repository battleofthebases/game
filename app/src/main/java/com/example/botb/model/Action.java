package com.example.botb.model;

import com.example.botb.model.weapon.Weapon;

import java.io.Serializable;

public class Action implements Serializable{

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

    @Override
    public String toString() {
        return "Action: " + weapon + " at " + location;
    }

}
