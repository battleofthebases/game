package com.example.botb.view.objects;

import android.content.ClipData;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

public class Draggable extends android.support.v7.widget.AppCompatImageView {

    public int size;  // possible if multiple space occupation


    public Draggable(Context context) {
        super(context);
        this.setOnTouchListener(new TouchListener());
    }


    private final class TouchListener implements OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            }
            else {
                return false;
            }
        }
    }

}
