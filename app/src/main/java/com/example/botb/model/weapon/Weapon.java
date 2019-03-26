package com.example.botb.model.weapon;

import com.example.botb.model.Board;
import com.example.botb.model.Location;
import com.example.botb.model.placeable.Placeable;

public abstract class Weapon {

    protected String name;

    public Weapon(String name) {
        this.name = name;
    }

    public void applyToBoard(Board board, Location location) {
        Placeable placeable = board.getPlaceable(location);
        if (placeable != null) {
            placeable.destroy();
        }
    }

    public void applyToBoard(Board board, int x, int y) {
        applyToBoard(board, new Location(x, y));
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Weapon: " + getName();
    }

}
