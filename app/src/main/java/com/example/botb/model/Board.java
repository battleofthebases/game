package com.example.botb.model;

import com.example.botb.model.placeable.Nexus;
import com.example.botb.model.placeable.Placeable;
import com.example.botb.model.placeable.Shield;
import com.example.botb.model.weapon.Weapon;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board implements Serializable {

    private Map<Location, Placeable> placeables;

    private List<Location> shots;

    private int width, height;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        placeables = new HashMap<>();
        shots = new ArrayList<>();
    }

    public Board(int width, int height, Map<Location, Placeable> placeables) {
        this.width = width;
        this.height = height;
        this.placeables = placeables;
        shots = new ArrayList<>();
    }

    public boolean addPlaceable(Placeable placeable, Location location) {
        if (!isLocationAvailable(location)) {
            return false;
        }
        placeables.put(location, placeable);
        return true;
    }

    public boolean addPlaceable(Placeable placeable, int x, int y) {
        return addPlaceable(placeable, new Location(x, y));
    }

    public boolean applyAction(Action action) {
        Weapon weapon = action.getWeapon();
        Location location = action.getLocation();

        // Check if already shot on location
        if (shots.contains(location)) {
            Placeable placeable = getPlaceable(location);
            if (placeable != null && !(placeable instanceof Nexus) || !allShieldsDestroyed()) {
                return false;
            }
        }

        // Apply action and add location to shots
        weapon.applyToBoard(this, location);
        shots.add(location);

        return true;
    }

    public boolean checkWinCondition() {
        if (allShieldsDestroyed()) {
            for (Placeable placeable : placeables.values()) {
                if (placeable instanceof Nexus && placeable.isDestroyed()) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getHeight() {
        return height;
    }

    public Placeable getPlaceable(Location location) {
        return placeables.get(location);
    }

    public Placeable getPlaceable(int x, int y) {
        return getPlaceable(new Location(x, y));
    }

    public Map<Location, Placeable> getPlaceables() {
        return placeables;
    }

    public List<Location> getShots() {
        System.out.println("get shots " + shots.size());
        System.out.println(this);
        return shots;

    }

    public int getWidth() {
        return width;
    }

    public boolean isLocationAvailable(Location location) {
        boolean isValid = isLocationValid(location);
        boolean isEmpty = placeables.get(location) == null;
        return isValid && isEmpty;
    }

    public boolean isLocationValid(Location location) {
        int x = location.getX(), y = location.getY();
        return (0 <= x && x < width) && (0 <= y && y < height);
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

    public boolean allShieldsDestroyed() {
        for (Placeable placeable : placeables.values()) {
            if (placeable instanceof Shield && !placeable.isDestroyed()) {
                return false;
            }
        }
        return true;
    }
}
