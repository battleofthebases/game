package com.example.botb.model.placeable;

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

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void destroy() {
        destroyed = true;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public Visibility getVisibility() {
        return visibility;
    }

}
