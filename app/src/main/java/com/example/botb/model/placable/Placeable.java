package com.example.botb.model.placable;

public abstract class Placeable {

    protected String name;
    protected int health;
    protected boolean destroyed;
    protected Visibility visibility;

    public Placeable() {
        health = 100;
        visibility = Visibility.HIDDEN;
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
