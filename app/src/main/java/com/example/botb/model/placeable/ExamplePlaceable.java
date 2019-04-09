package com.example.botb.model.placeable;

public class ExamplePlaceable extends Placeable {

    public ExamplePlaceable() {
        super("ExamplePlaceable");
    }

    @Override
    public void damage(int damage) {
        // The ExamplePlaceable gains health when damaged lol
        health += damage;
    }

}