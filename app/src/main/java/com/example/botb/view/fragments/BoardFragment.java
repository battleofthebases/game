package com.example.botb.view.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayout;
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
import com.example.botb.view.objects.Shot;
import com.example.botb.view.objects.Sprites;
import java.util.ArrayList;
import java.util.List;

public class BoardFragment extends Fragment {

    public View v;

    protected List<Draggable> draggables = new ArrayList<Draggable>();

    protected List<Droppable> droppables = new ArrayList<Droppable>();

    protected int gridHeight = 10;

    protected int gridWidth = 8;

    protected int height, width;

    protected InputManager inputManager = InputManager.getInstance();

    protected Sprites sprites;

    public void createBoard(boolean localPlayer) {

        // Get board
        Board board;
        if (localPlayer) {
            board = inputManager.getLocalBoard();
        } else {
            board = inputManager.getRemoteBoard();
        }

        GameGrid layout = v.findViewById(R.id.grid);
        layout.removeAllViews();
        layout.setRowCount(gridHeight);
        layout.setColumnCount(gridWidth);

        int lineHeight = height / layout.getRowCount();
        int lineWidth = width / layout.getColumnCount();

        for (int x = 0; x < layout.getRowCount(); x++) {
            for (int y = 0; y < layout.getColumnCount(); y++) {

                Droppable droppable = new Droppable(getContext(), localPlayer, sprites);
                droppable.setLayoutParams(new ViewGroup.LayoutParams(lineHeight, lineWidth));
                droppable.setOrientation(LinearLayout.HORIZONTAL);
                droppable.setId(R.id.parent + x + y);
                droppable.setGravity(Gravity.CENTER);
                droppable.setLocation(x, y);

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
                        draggable = createNexus(localPlayer, placeable);
                    } else {
                        draggable = createShield(localPlayer, placeable);
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
                droppables.add(droppable);
            }
        }
    }

    public Shot createShot() {
        Shot shot = new Shot(getContext(), sprites);
        shot.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        shot.setAdjustViewBounds(true);
        shot.setScaleType(ImageView.ScaleType.FIT_XY);
        return shot;
    }

    public List<Draggable> getDraggables() {
        return this.draggables;
    }

    public List<Droppable> getDroppables() {
        return this.droppables;
    }

    public void updateBoard(Board board) {
        GameGrid layout = v.findViewById(R.id.grid);
        List<Location> shots = board.getShots();
        for (int i = 0; i < shots.size(); i++) {
            Droppable droppable = (Droppable) layout
                    .getChildAt(shots.get(i).getX() * gridWidth + shots.get(i).getY());
            if (!droppable.isHit()) {
                if (droppable.getChildCount() == 1) {
                    Draggable draggable = (Draggable) droppable.getChildAt(0);
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

    protected Draggable createNexus(Boolean view, Placeable placable) {
        Nexus nexus = new Nexus(getContext(), view, placable, sprites);
        nexus.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        nexus.setAdjustViewBounds(true);
        return nexus;
    }

    protected Draggable createShield(Boolean player, Placeable placable) {
        Shield shield = new Shield(getContext(), player, placable, sprites);
        shield.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        shield.setAdjustViewBounds(true);
        return shield;
    }
}
