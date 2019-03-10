package com.example.botb.model.placable;

public abstract class Placeable {

    private String name;
    private int health;
    private boolean destroyed;
    private PlacableVisibility visibility;

    public Placeable(String name) {
        this.name = name;
        health = 100;
        visibility = PlacableVisibility.HIDDEN;
    }

    public void damage(int damage) {

        health -= damage;

        if (health <= 0) {
            destroy();
        }
    }

    public void destroy() {
        destroyed = true;
    }

}
