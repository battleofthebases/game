package com.example.botb.model.placable;

public abstract class Placeable {

    protected int health;
    protected boolean destroyed;
    protected Visibility visibility;

    public Placeable() {
        health = 100;
        visibility = Visibility.HIDDEN;
    }

    /**
     * Define a unique identifier for this placeable class
     * @return unique placeable identifier
     */
    public abstract String getPlaceableType();

    public void damage(int damage) {

        health -= damage;

        if (health <= 0) {
            destroy();
        }
    }

    public void destroy() {
        destroyed = true;
    }

    public int getHealth() {
        return health;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public Visibility getVisibility() {
        return visibility;
    }

}
