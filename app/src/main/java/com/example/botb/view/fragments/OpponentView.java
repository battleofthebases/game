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
import com.example.botb.view.objects.Draggable;
import com.example.botb.view.objects.Droppable;
import com.example.botb.view.objects.GameGrid;
import java.util.List;

public class OpponentView extends BoardAdapter {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_view, container, false);
        v = view;
        view.post(new Runnable() {
            @Override
            public void run() {
                Height = v.getMeasuredHeight();
                Width = v.getMeasuredWidth();
            }
        });

        // Create the board
        this.createBoard(false);
        return view;
    }
    public void updateBoard(Boolean player) {
        GameGrid layout = v.findViewById(R.id.grid);
        if (player)  {
            Log.d("UPDATE:", "Local player did something, updating remote board: ");
            Board board = inputManager.getRemoteBoard();
            Board board2 = inputManager.getLocalBoard();
            List<Location> shots = board.getShots();
            for (int i = 0; i < shots.size(); i++) {
                Droppable droppable = (Droppable) layout
                        .getChildAt(shots.get(i).getX()* gridWidth + shots.get(i).getY() );
                if (!droppable.isHit) {
                    if (droppable.getChildCount() == 1) {
                        Draggable draggable = (Draggable) droppable.getChildAt(0);
                        if (draggable.getName() != "Shot") {
                            draggable.updateHit();
                        }
                    } else {
                        droppable.addView(droppable.createShot());
                    }
                }
            }
        }
    }

}



