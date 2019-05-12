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
import com.example.botb.view.grid.GridCell;
import com.example.botb.view.grid.GridPlaceable;

import java.util.Map;

public class RemoteBoardFragment extends BoardFragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_view, container, false);
        v = view;
        view.post(() -> {
            createBoard(false);
        });
        return view;
    }

    public void setInitialBoard() {
        Log.d("Initialization", "Setting initial opponent board");

        Board board = inputManager.getRemoteBoard();
        Map<Location, Placeable> placeables = board.getPlaceables();

        for (Map.Entry<Location, Placeable> entry : placeables.entrySet()) {
            Location location = entry.getKey();
            Placeable placeable = entry.getValue();

            GridCell gridCell = getGridCellAtLocation(location);

            GridPlaceable gridPlaceable;
            if (placeable.getName().equals("Nexus")) {
                gridPlaceable = createNexus(false, placeable);
                Log.d("Initialization", "Adding Nexus");
            } else {
                gridPlaceable = createShield(false, placeable);
                Log.d("Initialization", "Adding Shield");
            }
            gridPlaceable.setLocation(location);

            gridCell.addView(gridPlaceable);
            gridPlaceables.add(gridPlaceable);
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



