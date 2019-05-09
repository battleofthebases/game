package com.example.botb.view.grid;

import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.example.botb.model.Location;
import com.example.botb.model.placeable.Placeable;
import com.example.botb.view.SpriteLoader;

public abstract class GridPlaceable extends android.support.v7.widget.AppCompatImageView {

    private final class GridPlaceableTouchListener implements OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                Log.d("onTouch:", "false");
                return false;
            }
        }
    }

    protected String name;

    protected Placeable placable;

    private Location location;

    public GridPlaceable(Context context) {
        super(context);
        this.StartDrag();
    }

    public void StartDrag() {
        this.setOnTouchListener(new GridPlaceableTouchListener());
    }

    public void StopDrag() {
        this.setOnTouchListener((v, event) -> false);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public abstract void setHit(SpriteLoader spriteLoader);

    public void setLocation(int x, int y) {
        location = new Location(x, y);
    }

}
