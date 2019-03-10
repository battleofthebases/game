package com.example.botb.model.weapon;

import com.example.botb.model.Board;
import com.example.botb.model.Location;
import com.example.botb.model.placeable.Placeable;

public abstract class Weapon {

    /**
     * Define a unique identifier for this weapon class
     * @return unique weapon identifier
     */
    public abstract String getWeaponType();

    public void applyToBoard(Board board, Location location) {
        Placeable placeable = board.getPlaceable(location);
        if (placeable != null) {
            placeable.destroy();
        }
    }

    public void applyToBoard(Board board, int x, int y) {
        applyToBoard(board, new Location(x, y));
    }

    @Override
    public String toString() {
        return "Weapon " + getWeaponType();
    }

}
