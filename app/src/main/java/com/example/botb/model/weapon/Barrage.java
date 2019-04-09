package com.example.botb.model.weapon;

import com.example.botb.model.Board;
import com.example.botb.model.Location;
import com.example.botb.model.placeable.Placeable;

public class Barrage extends Weapon {

    public Barrage() {
        super("Barrage");
    }

    @Override
    public void applyToBoard(final Board board, final Location location) {

        int startX = location.getX() - 1;
        int startY = location.getY() - 1;

        for (int x = startX; x < startX + 3; x++) {
            for (int y = startY; y < startY + 3; y++) {

                // Check if location is valid
                if (x >= 0 && x < board.getWidth() && y >= 0 && y < board.getHeight()) {

                    // Get and destroy placeable at location
                    Placeable placeable = board.getPlaceable(x, y);
                    if (placeable != null) {
                        placeable.destroy();
                    }

                }

            }
        }

    }

}
