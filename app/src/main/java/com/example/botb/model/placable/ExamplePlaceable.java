package com.example.botb.model.placable;

public class ExamplePlaceable extends Placeable {

    @Override
    public String getPlaceableType() {
        return "example";
    }

    @Override
    public void damage(int damage) {
        // The ExamplePlaceable gains health when damaged lol
        health += damage;
    }

}
