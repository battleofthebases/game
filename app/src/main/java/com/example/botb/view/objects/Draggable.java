package com.example.botb.view.objects;

import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.example.botb.model.Location;
import com.example.botb.model.placeable.Placeable;
import com.example.botb.model.placeable.Visibility;

public abstract class Draggable extends android.support.v7.widget.AppCompatImageView {

    protected Placeable placable;
    protected String name;
    protected Boolean hit = false;

    private final class TouchListener implements OnTouchListener {

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

    private final class StopTouchListener implements OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return false;
        }
    }

    private Location location;

    public Draggable(Context context) {
        super(context);
        this.StartDrag();
    }

    public String getName() { return name; }

    public void StartDrag() {
        this.setOnTouchListener(new TouchListener());
    }

    public void StopDrag() {
        this.setOnTouchListener(new StopTouchListener());
    }

    public Location getLocation() {
        return location;
    }

    public abstract void setHit(Sprites sprites);

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setLocation(int x, int y) {
        location = new Location(x, y);
    }

}
