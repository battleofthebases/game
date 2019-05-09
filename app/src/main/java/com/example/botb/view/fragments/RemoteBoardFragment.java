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
import com.example.botb.view.SpriteLoader;
import com.example.botb.view.grid.GameGrid;
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
            height = v.getHeight();
            width = v.getWidth();
            spriteLoader = new SpriteLoader(getActivity(), width, height);
            createBoard(false);
        });
        return view;
    }

    public void setInitialBoard() {
        Log.d("Initialization", "Setting initial opponent board");

        GameGrid layout = v.findViewById(R.id.grid);
        Map<Location, Placeable> placeables = inputManager.getRemoteBoard().getPlaceables();

        for (Map.Entry<Location, Placeable> entry : placeables.entrySet()) {
            GridCell gridCell = (GridCell) layout
                    .getChildAt(entry.getKey().getX() * gridWidth + entry.getKey().getY());

            GridPlaceable gridPlaceable;
            if (entry.getValue().getName().equals("Nexus")) {
                gridPlaceable = createNexus(false, entry.getValue());
                Log.d("Initialization", "Adding Nexus");
            } else {
                gridPlaceable = createShield(false, entry.getValue());
                Log.d("Initialization", "Adding Shield");
            }

            gridCell.addView(gridPlaceable);
            gridPlaceables.add(gridPlaceable);
            gridPlaceable.setLocation(entry.getKey().getX(), entry.getKey().getY());
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



