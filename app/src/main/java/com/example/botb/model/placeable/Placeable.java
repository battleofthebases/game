package com.example.botb.model.placeable;

public abstract class Placeable {

    protected boolean destroyed;

    protected int health;

    protected String name;

    protected Visibility visibility;

    public Placeable(String name) {
        this.name = name;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

}
