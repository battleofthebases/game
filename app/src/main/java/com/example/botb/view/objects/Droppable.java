package com.example.botb.view.objects;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.botb.InputManager;
import com.example.botb.R;
import com.example.botb.model.Board;
import com.example.botb.model.Location;
import com.example.botb.view.fragments.GameView;

import android.view.View.OnDragListener;

import java.io.Console;

public class Droppable {

    private Context context;
    private Location location;
    public GameView gameView;

    public Droppable(Context con, View view) {
        context = con;
        view.setOnDragListener(new Droppable.DragListener());
    }

    class DragListener implements OnDragListener {
        Drawable enterShape = context.getResources().getDrawable(
                R.drawable.shape_droptarget);
        Drawable normalShape = context.getResources().getDrawable(R.drawable.shape);
        Drawable noEntryShape = context.getResources().getDrawable(R.drawable.no_entry_shape);

        @Override
        public boolean onDrag(View v, DragEvent event) {

            Draggable draggable = (Draggable) event.getLocalState();
            LinearLayout container = (LinearLayout) v;
            ViewGroup owner = (ViewGroup) draggable.getParent();

            Board board = InputManager.getInstance().getLocalBoard();

            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    if (container.getChildCount() > 0 && container != owner){
                        v.setBackgroundDrawable(noEntryShape);
                    } else {
                        v.setBackgroundDrawable(enterShape);
                    }
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundDrawable(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    if (container.getChildCount() == 0){

                        // Move placeable and recreate board
                        board.movePlaceable(draggable.getLocation(), getLocation());
                        if (gameView != null)
                            gameView.createBoard();

                        //owner.removeView(draggable);
                        //container.addView(draggable);
                    }
                    draggable.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundDrawable(normalShape);
                default:
                    break;
            }
            return true;
        }
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(int x, int y) {
        location = new Location(x, y);
    }

}
