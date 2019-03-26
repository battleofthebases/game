package com.example.botb.model.placeable;

public abstract class Placeable {

    protected String name;
    protected int health;
    protected boolean destroyed;
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

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
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
