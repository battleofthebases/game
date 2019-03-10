package com.example.botb.model.weapon;

import com.example.botb.model.Board;
import com.example.botb.model.Location;
import com.example.botb.model.placable.Placeable;

public abstract class Weapon {

    protected String name;

    public void applyToBoard(Board board, Location location) {
        Placeable placeable = board.getPlacable(location);
        if (placeable != null) {
            placeable.destroy();
        }
    }

}
