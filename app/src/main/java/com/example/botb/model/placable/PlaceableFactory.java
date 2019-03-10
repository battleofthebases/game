package com.example.botb.model.placable;

public class PlaceableFactory {

    public static Placeable getPlaceable(int id) {

        switch (id) {
            case 1:
                return new ExamplePlaceable();
        }

        return null;
    }

}
