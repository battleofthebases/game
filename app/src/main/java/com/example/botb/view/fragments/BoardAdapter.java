package com.example.botb.view.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.example.botb.InputManager;
import com.example.botb.R;
import com.example.botb.model.Board;
import com.example.botb.model.Location;
import com.example.botb.model.placeable.Placeable;
import com.example.botb.view.objects.Draggable;
import com.example.botb.view.objects.Droppable;
import com.example.botb.view.objects.GameGrid;
import com.example.botb.view.objects.Nexus;
import com.example.botb.view.objects.Shield;
import java.util.ArrayList;
import java.util.List;

public class BoardAdapter extends Fragment {

    private static final String TAG = "";

    protected int Height;

    protected int Width;

    View v;

    private List<Draggable> draggables = new ArrayList<Draggable>();

    private int gridHeight = 10;

    private int gridWidth = 8;

    private InputManager inputManager = InputManager.getInstance();

    public void createBoard(Boolean player) {
        // Get local board

        Board board;
        if (player) {
            board = inputManager.getLocalBoard();
        } else {
            board = inputManager.getRemoteBoard();
        }
        GameGrid layout = v.findViewById(R.id.grid);
        layout.removeAllViews();
        layout.setRowCount(gridHeight);
        layout.setColumnCount(gridWidth);

        int lineHeight = Height / layout.getRowCount();
        int lineWidth = Width / layout.getColumnCount();

        for (int x = 0; x < layout.getRowCount(); x++) {
            for (int y = 0; y < layout.getColumnCount(); y++) {

                Droppable droppable = new Droppable(getContext(), player, board);
                droppable.setLayoutParams(new ViewGroup.LayoutParams(lineHeight, lineWidth));
                droppable.setOrientation(LinearLayout.HORIZONTAL);
                droppable.setId(R.id.parent + x + y);
                droppable.setGravity(Gravity.CENTER);
                droppable.setLocation(x, y);
                droppable.gameView = this;

                // Get placeable

                //TODO: FixNullPointerExceptionHandling
                Placeable placeable = null;
                try {
                    placeable = board.getPlaceable(x, y);

                } catch (NullPointerException e) {

                }

                if (placeable != null) {
                    Draggable draggable;
                    if (placeable.getName() == "Nexus") {
                        draggable = createNexus(player, placeable);
                    } else {
                        draggable = createShield(player, placeable);
                    }
                    droppable.addView(draggable);
                    draggables.add(draggable);
                    draggable.setLocation(x, y);
                }

                GridLayout.LayoutParams myGLP = new GridLayout.LayoutParams();
                GridLayout.Spec rowSpec = GridLayout.spec(x, 1, 1);
                GridLayout.Spec colSpec = GridLayout.spec(y, 1, 1);
                myGLP.rowSpec = rowSpec;
                myGLP.columnSpec = colSpec;
                myGLP.width = 0;
                myGLP.height = 0;
                layout.addView(droppable, myGLP);
            }
        }
    }

    public List<Draggable> getDraggables() {
        return this.draggables;
    }

    public void updateBoard(Boolean player) {
        Log.d("Update", "Board");
        Board board;
        System.out.println("The player " + player);
        if (!player) {
            board = inputManager.getLocalBoard();
        } else {
            board = inputManager.getRemoteBoard();
        }
        GameGrid layout = v.findViewById(R.id.grid);
        List<Location> shots = board.getShots();
        System.out.println("shots size" + shots.size());
        for (int i = 0; i < shots.size(); i++) {
            Droppable droppable = (Droppable) layout
                    .getChildAt(shots.get(i).getX() + shots.get(i).getY() * gridWidth);
            System.out.println("bla bla" + droppable);
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

    private Draggable createNexus(Boolean view, Placeable placable) {
        Nexus nexus = new Nexus(getContext(), view, placable);
        nexus.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        nexus.setAdjustViewBounds(true);
        nexus.setScaleType(ImageView.ScaleType.FIT_XY);
        return nexus;
    }

    private Draggable createShield(Boolean player, Placeable placable) {
        Shield shield = new Shield(getContext(), player, placable);
        shield.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        shield.setAdjustViewBounds(true);
        shield.setScaleType(ImageView.ScaleType.FIT_XY);
        return shield;
    }
}
