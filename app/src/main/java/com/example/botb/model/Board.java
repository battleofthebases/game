package com.example.botb.model;

import com.example.botb.model.placable.Placeable;
import com.example.botb.model.weapon.Weapon;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private int width, height;
    private Map<Location, Placeable> placables;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        placables = new HashMap<>();
    }

    public Board(int width, int height, Map<Location, Placeable> placables) {
        this.width = width;
        this.height = height;
        this.placables = placables;
    }

    public void applyAction(Action action) {
        Weapon weapon = action.getWeapon();
        Location location = action.getLocation();
        weapon.applyToBoard(this, location);
    }

    public boolean addPlacable(Placeable placeable, Location location) {
        if (!isLocationAvailable(location)) return false;
        placables.put(location, placeable);
        return true;
    }

    public boolean addPlacable(Placeable placeable, int x, int y) {
        return addPlacable(placeable, new Location(x, y));
    }

    public Placeable getPlacable(Location location) {
        return placables.get(location);
    }

    public Placeable getPlacable(int x, int y) {
        return getPlacable(new Location(x, y));
    }

    public boolean movePlacable(Location from, Location to) {

        Placeable placeable = getPlacable(from);

        if (placeable != null && isLocationAvailable(to)) {
            placables.remove(from);
            placables.put(to, placeable);
            return true;
        }

        return false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isLocationValid(Location location) {
        int x = location.getX(), y = location.getY();
        return (0 <= x && x < width) && (0 <= y && y < height);
    }

    public boolean isLocationAvailable(Location location) {
        boolean isValid = isLocationValid(location);
        boolean isEmpty = placables.get(location) == null;
        return isValid && isEmpty;
    }

}
