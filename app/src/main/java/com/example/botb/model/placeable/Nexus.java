package com.example.botb.model.placeable;

public class Nexus extends Placeable {

    public Nexus() {
        super("Nexus");
    }

    @Override
    public void damage(int damage) {
        // The ExamplePlaceable gains health when damaged lol
        health += damage;
    }

}
