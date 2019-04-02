package com.example.botb.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.botb.InputManager;
import com.example.botb.R;
import com.example.botb.model.Board;
import com.example.botb.model.placeable.Placeable;
import com.example.botb.view.objects.Draggable;
import com.example.botb.view.objects.Droppable;
import com.example.botb.view.objects.GameGrid;

import java.util.ArrayList;
import java.util.List;


public class GameView extends Fragment {
    private static final String TAG = "";

    private int Width;
    private int Height;

    View v;

    private List <Draggable> draggables = new ArrayList<Draggable>();
    public List<Draggable> getDraggables(){
        return this.draggables;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_view, container, false);
        v = view;
        view.post(new Runnable() {
              @Override
              public void run() {
                  Height=v.getMeasuredHeight();
                  Width=v.getMeasuredWidth();
              }
        });

        // Create the board
        createBoard();

        return view;
    }

    public void createBoard() {

        // Get local board
        Board board = InputManager.getInstance().getLocalBoard();

        GameGrid layout = v.findViewById(R.id.grid);
        layout.removeAllViews();
        layout.setRowCount(10);
        layout.setColumnCount(8);

        int lineHeight = Height/layout.getRowCount();
        int lineWidth = Width/layout.getColumnCount();

        for (int x = 0; x < layout.getRowCount(); x++) {
            for (int y = 0; y < layout.getColumnCount(); y++) {

                Droppable droppable = new Droppable(getContext(), 80, 100, 0, 0, 0, 0);
                droppable.setLayoutParams(new ViewGroup.LayoutParams(lineHeight,lineWidth ));
                droppable.setOrientation(LinearLayout.HORIZONTAL);
                droppable.setId(R.id.parent + x + y);
                droppable.setGravity(Gravity.FILL_HORIZONTAL);

                droppable.setLocation(x, y);
                droppable.gameView = this;

                // Get placeable
                Placeable placeable = board.getPlaceable(x, y);

                if (placeable != null){
                    Draggable draggable = new Draggable(getContext());
                    draggable.setImageResource(R.drawable.ic_launcher_background);
                    draggable.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    draggable.setAdjustViewBounds(true);
                    draggable.setScaleType(ImageView.ScaleType.FIT_XY);
                    droppable.addView(draggable);
                    draggables.add(draggable);
                    draggable.setLocation(x, y);
                }

                GridLayout.LayoutParams myGLP = new GridLayout.LayoutParams();
                GridLayout.Spec rowSpec = GridLayout.spec(x, 1,1);
                GridLayout.Spec colSpec = GridLayout.spec(y, 1,1);
                myGLP.rowSpec = rowSpec;
                myGLP.columnSpec = colSpec;
                myGLP.width = 0;
                myGLP.height = 0;
                layout.addView(droppable, myGLP);
            }
        }
    }
}
