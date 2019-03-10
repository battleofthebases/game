package com.example.botb.model.placable;

public class ExamplePlaceable extends Placeable {

    public ExamplePlaceable() {
        name = "ExamplePlaceable";
    }

    @Override
    public void damage(int damage) {
        // The ExamplePlaceable gains health when damaged lol
        health += damage;
    }

}
