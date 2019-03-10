package com.example.botb.model.placable;

public class PlaceableFactory {

    public static Placeable getPlaceable(String placeableId) {

        switch (placeableId) {
            case "example":
                return new ExamplePlaceable();
        }

        return null;
    }

}
