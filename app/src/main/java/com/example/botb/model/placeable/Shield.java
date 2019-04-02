package com.example.botb.model.placeable;

public class Shield extends Placeable {

    public Shield() {
        super("Shield");
    }

    @Override
    public void damage(int damage) {
        // The ExamplePlaceable gains health when damaged lol
        health += damage;
    }

}
