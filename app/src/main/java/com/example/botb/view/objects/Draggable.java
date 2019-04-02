package com.example.botb.view.objects;

import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.botb.model.Location;

public class Draggable extends android.support.v7.widget.AppCompatImageView {

    public int size;  // possible if multiple space occupation

    private Location location;

    public Draggable(Context context) {
        super(context);
        this.StartDrag();
    }

    public void StopDrag(){
        this.setOnTouchListener(new StopTouchListener());
    }

    public void StartDrag(){
        this.setOnTouchListener(new TouchListener());
    }


    private final class TouchListener implements OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            }
            else {
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setLocation(int x, int y) {
        location = new Location(x, y);
    }
}
