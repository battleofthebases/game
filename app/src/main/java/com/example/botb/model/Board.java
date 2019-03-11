package com.example.botb.model;

import com.example.botb.model.placeable.Placeable;
import com.example.botb.model.weapon.Weapon;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private int width, height;
    private Map<Location, Placeable> placeables;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        placeables = new HashMap<>();
    }

    public Board(int width, int height, Map<Location, Placeable> placeables) {
        this.width = width;
        this.height = height;
        this.placeables = placeables;
    }

    public void applyAction(Action action) {
        Weapon weapon = action.getWeapon();
        Location location = action.getLocation();
        weapon.applyToBoard(this, location);
    }

    public boolean addPlaceable(Placeable placeable, Location location) {
        if (!isLocationAvailable(location)) return false;
        placeables.put(location, placeable);
        return true;
    }

    public boolean addPlaceable(Placeable placeable, int x, int y) {
        return addPlaceable(placeable, new Location(x, y));
    }

    public Placeable getPlaceable(Location location) {
        return placeables.get(location);
    }

    public Placeable getPlaceable(int x, int y) {
        return getPlaceable(new Location(x, y));
    }

    public boolean movePlaceable(Location from, Location to) {

        Placeable placeable = getPlaceable(from);

        if (placeable != null && isLocationAvailable(to)) {
            placeables.remove(from);
            placeables.put(to, placeable);
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
        boolean isEmpty = placeables.get(location) == null;
        return isValid && isEmpty;
    }

}
