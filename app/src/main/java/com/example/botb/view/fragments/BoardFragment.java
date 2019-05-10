package com.example.botb.view.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.example.botb.R;
import com.example.botb.controller.InputManager;
import com.example.botb.model.Board;
import com.example.botb.model.Location;
import com.example.botb.model.placeable.Placeable;
import com.example.botb.view.grid.GameGrid;
import com.example.botb.view.grid.GridCell;
import com.example.botb.view.grid.GridPlaceable;
import com.example.botb.view.grid.Nexus;
import com.example.botb.view.grid.Shield;
import com.example.botb.view.grid.Shot;
import java.util.ArrayList;
import java.util.List;

public class BoardFragment extends Fragment {

    public View v;

    protected List<GridCell> gridCells = new ArrayList<>();

    protected int gridHeight = 10;

    protected List<GridPlaceable> gridPlaceables = new ArrayList<>();

    protected int gridWidth = 8;

    protected int height, width;

    protected InputManager inputManager = InputManager.getInstance();

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

                GridCell gridCell = new GridCell(getContext(), localPlayer);
                gridCell.setLayoutParams(new ViewGroup.LayoutParams(lineHeight, lineWidth));
                gridCell.setOrientation(LinearLayout.HORIZONTAL);
                gridCell.setId(R.id.parent + x + y);
                gridCell.setGravity(Gravity.CENTER);
                gridCell.setLocation(x, y);

                // Get placeable

                //TODO: FixNullPointerExceptionHandling
                Placeable placeable = null;
                try {
                    placeable = board.getPlaceable(x, y);

                } catch (NullPointerException e) {

                }

                if (placeable != null) {
                    GridPlaceable gridPlaceable;
                    if (placeable.getName() == "Nexus") {
                        gridPlaceable = createNexus(localPlayer, placeable);
                    } else {
                        gridPlaceable = createShield(localPlayer, placeable);
                    }
                    gridCell.addView(gridPlaceable);
                    gridPlaceables.add(gridPlaceable);
                    gridPlaceable.setLocation(x, y);
                }

                GridLayout.LayoutParams myGLP = new GridLayout.LayoutParams();
                GridLayout.Spec rowSpec = GridLayout.spec(x, 1, 1);
                GridLayout.Spec colSpec = GridLayout.spec(y, 1, 1);
                myGLP.rowSpec = rowSpec;
                myGLP.columnSpec = colSpec;
                myGLP.width = 0;
                myGLP.height = 0;
                layout.addView(gridCell, myGLP);
                gridCells.add(gridCell);
            }
        }
    }

    public Shot createShot() {
        Shot shot = new Shot(getContext());
        shot.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        shot.setAdjustViewBounds(true);
        shot.setScaleType(ImageView.ScaleType.FIT_XY);
        return shot;
    }

    public List<GridCell> getGridCells() {
        return this.gridCells;
    }

    public List<GridPlaceable> getGridPlaceables() {
        return this.gridPlaceables;
    }

    public void updateBoard(Board board) {

        GameGrid layout = v.findViewById(R.id.grid);
        List<Location> shots = board.getShots();

        // Loop all shots on board
        for (int i = 0; i < shots.size(); i++) {

            // Get gridCell at shot location
            GridCell gridCell = (GridCell) layout.getChildAt(shots.get(i).getX() * gridWidth + shots.get(i).getY());

            // Check if gridCell is not hit already
            if (!gridCell.isHit()) {
                if (gridCell.getChildCount() == 1) {

                    // Get gridPlaceable in gridCell and apply hit
                    GridPlaceable gridPlaceable = (GridPlaceable) gridCell.getChildAt(0);
                    if (gridPlaceable.getName() != "Shot") {
                        gridPlaceable.setHit();
                        gridCell.setHit();
                    }

                } else {

                    // Add shot placeable if gridCell is empty
                    gridCell.addView(createShot());

                }
            }
        }
    }

    protected GridPlaceable createNexus(boolean localPlayer, Placeable placable) {
        Nexus nexus = new Nexus(getContext(), localPlayer, placable);
        nexus.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        nexus.setAdjustViewBounds(true);
        return nexus;
    }

    protected GridPlaceable createShield(boolean localPlayer, Placeable placable) {
        Shield shield = new Shield(getContext(), localPlayer, placable);
        shield.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        shield.setAdjustViewBounds(true);
        return shield;
    }
}
