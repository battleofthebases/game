package com.example.botb.model;

import java.io.Serializable;
import java.util.Objects;

public class Location implements Serializable{

    private final int x, y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) return true;
        if (!(obj instanceof Location)) return false;

        Location location = (Location) obj;

        return location.x == x && location.y == y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Location(" + x + "," + y + ")";
    }

}
