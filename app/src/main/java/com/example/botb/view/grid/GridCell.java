package com.example.botb.view.grid;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import com.example.botb.R;
import com.example.botb.controller.InputManager;
import com.example.botb.model.Action;
import com.example.botb.model.Board;
import com.example.botb.model.Location;
import com.example.botb.model.weapon.ExampleWeapon;
import com.example.botb.view.SpriteLoader;
import java.io.IOException;

public class GridCell extends android.support.v7.widget.LinearLayoutCompat {

    private final class GridCellDragListener implements OnDragListener {

        BitmapDrawable background;

        Drawable enterShape = getContext().getResources().getDrawable(
                R.drawable.shape_droptarget);

        Drawable noEntryShape = getContext().getResources().getDrawable(R.drawable.no_entry_shape);

        GridCellDragListener(BitmapDrawable background) {
            this.background = background;
        }

        @Override
        public boolean onDrag(View v, DragEvent event) {

            GridPlaceable gridPlaceable = (GridPlaceable) event.getLocalState();
            GridCell gridCell = (GridCell) v;
            ViewGroup owner = (ViewGroup) gridPlaceable.getParent();

            switch (event.getAction()) {

                // When dragged placeable enters this gridCell
                case DragEvent.ACTION_DRAG_ENTERED:
                    if (gridCell.getChildCount() > 0 && gridCell != owner) {
                        // Not allowed to place here
                        v.setBackground(noEntryShape);
                    } else {
                        // Can place here
                        v.setBackground(enterShape);
                    }
                    break;

                // When dragged placeable is dropped in this gridCell
                case DragEvent.ACTION_DROP:
                    // Check if gridCell is empty
                    if (gridCell.getChildCount() == 0) {

                        // Get from and to location
                        Location fromLocation = gridPlaceable.getLocation();
                        Location toLocation = getLocation();

                        // Get the board and move placeable
                        Board board = InputManager.getInstance().getLocalBoard();
                        board.movePlaceable(fromLocation, toLocation);

                        // Move gridPlaceable and update location
                        owner.removeView(gridPlaceable);
                        gridCell.addView(gridPlaceable);
                        gridPlaceable.setLocation(toLocation);
                    }
                    gridPlaceable.setVisibility(View.VISIBLE);
                    break;

                // When dragged placeable exits this gridCell
                case DragEvent.ACTION_DRAG_ENDED:
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackground(background);
                default:
                    break;
            }
            return true;
        }
    }

    private final class GridCellClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            Action action = new Action(location, new ExampleWeapon());
            InputManager inputmanager = InputManager.getInstance();
            try {
                inputmanager.handleLocalAction(action);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean hit = false;

    private Location location;

    public GridCell(Context context, boolean localPlayer, SpriteLoader spriteLoader) {
        super(context);

        BitmapDrawable background;

        if (localPlayer) {
            background = spriteLoader.getPlayerBackground();
            this.setOnDragListener(new GridCellDragListener(background));
        } else {
            background = spriteLoader.getOpponentBackground();
        }
        this.setBackground(background);
    }

    public Location getLocation() {
        return location;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit() {
        hit = true;
    }

    public void setLocation(int x, int y) {
        location = new Location(x, y);
    }

    public void setOnClikcListener() {
        this.setOnClickListener(new GridCellClickListener());
    }


}
