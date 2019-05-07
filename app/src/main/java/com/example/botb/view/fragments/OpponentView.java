package com.example.botb.view.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.botb.R;
import com.example.botb.model.Board;
import com.example.botb.model.Location;
import com.example.botb.model.placeable.Placeable;
import com.example.botb.view.objects.Draggable;
import com.example.botb.view.objects.Droppable;
import com.example.botb.view.objects.GameGrid;
import com.example.botb.view.objects.Sprites;
import java.util.Map;

public class OpponentView extends BoardAdapter {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_view, container, false);
        v = view;
        view.post(new Runnable() {
            @Override
            public void run() {
                Height = v.getHeight();
                Width = v.getWidth();
                sprites = new Sprites(getActivity(), Width, Height);
                createBoard(false);
            }
        });
        return view;
    }

    public void setInitialBoard() {
        Log.d("Initialization", "Setting initial opponent board");
        GameGrid layout = v.findViewById(R.id.grid);
        Map<Location, Placeable> placables = inputManager.getRemoteBoard().getPlaceables();
        for (Map.Entry<Location, Placeable> entry : placables.entrySet()) {
            Droppable droppable = (Droppable) layout
                    .getChildAt(entry.getKey().getX() * gridWidth + entry.getKey().getY());

            Draggable draggable;
            if (entry.getValue().getName().equals("Nexus")) {
                draggable = createNexus(false, entry.getValue());
                Log.d("Initialization", "Adding Nexus");
            } else {
                draggable = createShield(false, entry.getValue());
                Log.d("Initialization", "Adding Shield");
            }

            droppable.addView(draggable);
            draggables.add(draggable);
            draggable.setLocation(entry.getKey().getX(), entry.getKey().getY());

        }

    }

    public void updateBoard(boolean isLocalAction) {
        // Update remote board if local player did something
        if (isLocalAction) {
            Board board = inputManager.getRemoteBoard();
            updateBoard(board);
        }
    }

}



