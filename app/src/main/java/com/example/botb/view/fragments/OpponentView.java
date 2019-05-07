package com.example.botb.view.fragments;


import android.media.AudioManager.AudioPlaybackCallback;
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
import java.util.HashMap;
import java.util.List;
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
    public void updateBoard(Boolean player) {
        GameGrid layout = v.findViewById(R.id.grid);
        if (player)  {
            Log.d("UPDATE:", "Local player did something, updating remote board: ");
            Board board = inputManager.getRemoteBoard();
            List<Location> shots = board.getShots();
            for (int i = 0; i < shots.size(); i++) {
                Droppable droppable = (Droppable) layout
                        .getChildAt(shots.get(i).getX()* gridWidth + shots.get(i).getY() );
                if (!droppable.isHit()) {
                    if (droppable.getChildCount() == 1) {
                        Draggable draggable = (Draggable) droppable.getChildAt(0);
                        Log.d("draggable name", "" + draggable.getName());
                        if (draggable.getName() != "Shot") {
                            draggable.setHit(sprites);
                            droppable.setHit();
                        }
                    } else {
                        droppable.addView(createShot());
                    }
                }
            }
        }
    }

    public void setInitialBoard(){
        Log.d("Initialization", "Setting initial opponent board");
        GameGrid layout = v.findViewById(R.id.grid);
        Map<Location, Placeable> placables = inputManager.getRemoteBoard().getPlaceables();
        for (Map.Entry<Location, Placeable> entry : placables.entrySet()) {
            Droppable droppable = (Droppable) layout
                    .getChildAt(entry.getKey().getX()* gridWidth + entry.getKey().getY());

            Draggable draggable;
            if (entry.getValue().getName().equals("Nexus")) {
                draggable = createNexus(false, entry.getValue());
                Log.d("Initialization", "Adding Nexus" );
            } else {
                draggable = createShield(false, entry.getValue());
                Log.d("Initialization", "Adding Shield" );
            }

            droppable.addView(draggable);
            draggables.add(draggable);
            draggable.setLocation(entry.getKey().getX(), entry.getKey().getY());

        }

    }

}



