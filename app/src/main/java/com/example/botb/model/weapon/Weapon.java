package com.example.botb.model.weapon;

import com.example.botb.model.Board;
import com.example.botb.model.Location;
import com.example.botb.model.placeable.Nexus;
import com.example.botb.model.placeable.Placeable;
import com.example.botb.model.placeable.Visibility;

import java.io.Serializable;

public abstract class Weapon implements Serializable {

    protected String name;

    public Weapon(String name) {
        this.name = name;
    }

    public void applyToBoard(Board board, Location location) {
        Placeable placeable = board.getPlaceable(location);
        if (placeable != null) {
            if (placeable instanceof Nexus && !board.allShieldsDestroyed()) {
                placeable.setVisibility(Visibility.VISIBLE);
            } else {
                placeable.destroy();
            }
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
