package com.example.botb.model.weapon;

import com.example.botb.model.Board;
import com.example.botb.model.Location;
import com.example.botb.model.placeable.Placeable;
import com.example.botb.model.placeable.Visibility;

public class Scanner extends Weapon {

    public Scanner() {
        super("Scanner");
    }

    @Override
    public void applyToBoard(final Board board, final Location location) {

        // Update visibility and return if there is a placeable in the selected location
        Placeable placeable = board.getPlaceable(location);
        if (placeable != null) {
            placeable.setVisibility(Visibility.DETECTED);
            return;
        }

        // Define "walkers"
        int xLeft = location.getX() - 1;
        int xRight = location.getX() + 1;
        int yUp = location.getY() - 1;
        int yDown = location.getY() + 1;

        while (xLeft >= 0 || xRight < board.getWidth() || yUp >= 0 || yDown < board.getHeight()) {

            // Check horizontally to the left
            if (xLeft >= 0 && checkLocation(board, new Location(xLeft, location.getY()))) {
                xLeft = -1;
            }

            // Check horizontally to the right
            if (xRight < board.getWidth() && checkLocation(board, new Location(xRight, location.getY()))) {
                xRight = board.getWidth();
            }

            // Check vertically up
            if (yUp >= 0 && checkLocation(board, new Location(location.getX(), yUp))) {
                yUp = -1;
            }

            // Check vertically down
            if (yDown < board.getHeight() && checkLocation(board, new Location(location.getX(), yDown))) {
                yDown = board.getHeight();
            }

            // Update "walkers"
            xLeft--;
            xRight++;
            yUp--;
            yDown++;

        }
    }

    // Check if a placeable exist at location and set visibility to detected if so
    private boolean checkLocation(Board board, Location location) {
        Placeable placeable = board.getPlaceable(location);
        if (placeable != null && placeable.getVisibility() == Visibility.HIDDEN) {
            placeable.setVisibility(Visibility.DETECTED);
            return true;
        }
        return false;
    }

}
