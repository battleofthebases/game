package com.example.botb.view.fragments;

import android.support.v4.app.Fragment;
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

    protected List<GridPlaceable> gridPlaceables = new ArrayList<>();

    protected InputManager inputManager = InputManager.getInstance();

    public void createBoard(boolean localPlayer) {

        // Get local board
        Board board = inputManager.getLocalBoard();

        GameGrid layout = v.findViewById(R.id.grid);
        layout.removeAllViews();
        layout.setRowCount(board.getHeight());
        layout.setColumnCount(board.getWidth());

        int cellWidth = v.getWidth() / layout.getColumnCount();
        int cellHeight = v.getWidth() / layout.getRowCount();

        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {

                // Create grid cell
                GridCell gridCell = new GridCell(getContext(), localPlayer);
                gridCell.setLayoutParams(new ViewGroup.LayoutParams(cellWidth, cellHeight));
                gridCell.setOrientation(LinearLayout.HORIZONTAL);
                gridCell.setId(R.id.parent + x + y);
                gridCell.setGravity(Gravity.CENTER);
                gridCell.setLocation(x, y);

                // Get placeable if this is the local player
                if (localPlayer) {
                    Placeable placeable = board.getPlaceable(x, y);
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
                }

                // Add grid cell to layout
                layout.addView(gridCell);
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

    public GridCell getGridCellAtLocation(Location location) {
        for (GridCell gridCell : gridCells) {
            if (gridCell.getLocation().equals(location)) {
                return gridCell;
            }
        }
        return null;
    }

    public List<GridPlaceable> getGridPlaceables() {
        return this.gridPlaceables;
    }

    public void updateBoard(Board board) {

        List<Location> shots = board.getShots();

        // Loop all shots on board
        for (Location shotLocation : shots) {

            // Get gridCell at shot location
            GridCell gridCell = getGridCellAtLocation(shotLocation);

            // Check if gridCell is not hit already
            if (!gridCell.isHit()) {
                if (gridCell.getChildCount() == 1) {

                    // Get gridPlaceable in gridCell and apply hit
                    GridPlaceable gridPlaceable = (GridPlaceable) gridCell.getChildAt(0);
                    if (!(gridPlaceable instanceof  Shot)) {
                        if (gridPlaceable instanceof Nexus) {

                            Nexus nexus = (Nexus) gridPlaceable;
                            if (board.getPlaceable(shotLocation).isDestroyed()) {
                                nexus.setHit();
                            } else if (!nexus.isDetected()) {
                                nexus.detect();
                            }

                        } else {
                            gridPlaceable.setHit();
                            gridCell.setHit();
                        }
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
