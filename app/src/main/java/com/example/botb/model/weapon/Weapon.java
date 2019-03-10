package com.example.botb.model.weapon;

import com.example.botb.model.Board;
import com.example.botb.model.Location;
import com.example.botb.model.placable.Placeable;

public abstract class Weapon {

    public final int weaponType;

    public Weapon(int weaponType) {
        this.weaponType = weaponType;
    }

    public void applyToBoard(Board board, Location location) {
        Placeable placeable = board.getPlacable(location);
        if (placeable != null) {
            placeable.destroy();
        }
    }

    public void applyToBoard(Board board, int x, int y) {
        applyToBoard(board, new Location(x, y));
    }

    public int getWeaponType() {
        return weaponType;
    }

    @Override
    public String toString() {
        return "Weapon " + weaponType;
    }

}
