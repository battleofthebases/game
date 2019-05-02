package com.example.botb.view.objects;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.botb.InputManager;
import com.example.botb.R;
import com.example.botb.model.Action;
import com.example.botb.model.Board;
import com.example.botb.model.Location;
import com.example.botb.model.placeable.Placeable;
import com.example.botb.model.weapon.ExampleWeapon;
import com.example.botb.view.fragments.BoardAdapter;
import java.io.IOException;

public class Droppable extends android.support.v7.widget.LinearLayoutCompat {

    private Boolean hit = false;
    private Board board;
    private Sprites sprites;

    public Droppable(Context context, Boolean view, Board board, Sprites sprites) {
        super(context);
        this.sprites = sprites;
        this.board = board;
        BitmapDrawable background;

        if (view) {
            background = sprites.getPlayerBackground();
            this.setOnDragListener(new Droppable.DragListener(background));
        } else {
            background = sprites.getOpponentBackground();
        }
        this.setBackgroundDrawable(background);

    }



    class stopOnClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
        }
    }

    class onClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            Droppable container = (Droppable) v;
            Action action = new Action(location, new ExampleWeapon());
            InputManager inputmanager = InputManager.getInstance();
            try {
                inputmanager.handleLocalAction(action);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class DragListener implements OnDragListener {

        BitmapDrawable background;

        Drawable enterShape = getContext().getResources().getDrawable(
                R.drawable.shape_droptarget);

        Drawable noEntryShape = getContext().getResources().getDrawable(R.drawable.no_entry_shape);

        DragListener(BitmapDrawable background) {
            this.background = background;
        }

        @Override
        public boolean onDrag(View v, DragEvent event) {

            Draggable draggable = (Draggable) event.getLocalState();
            Droppable container = (Droppable) v;
            ViewGroup owner = (ViewGroup) draggable.getParent();

            Board board = InputManager.getInstance().getLocalBoard();

            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    if (container.getChildCount() > 0 && container != owner) {
                        v.setBackgroundDrawable(noEntryShape);
                    } else {
                        v.setBackgroundDrawable(enterShape);
                    }
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundDrawable(background);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    if (container.getChildCount() == 0) {

                        // Move placeable and recreate board
                        Location fromLocation = draggable.getLocation();
                        Location toLocation = getLocation();

                        board.movePlaceable(fromLocation, toLocation);
                        draggable.setLocation(toLocation);

                        owner.removeView(draggable);
                        container.addView(draggable);
                    }
                    draggable.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundDrawable(background);
                default:
                    break;
            }
            return true;
        }
    }

    public BoardAdapter gameView;

    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(int x, int y) {
        location = new Location(x, y);
    }

    public void setOnClikcListener() {
        this.setOnClickListener(new onClickListener());
    }

    public void stopClikcListener() {
        this.setOnClickListener(new stopOnClickListener());
    }

    public Board getBoard() {
        return board;
    }

    public Boolean isHit(){
       return hit;
    }

    public void setHit() {
        hit = true;
    }


}
