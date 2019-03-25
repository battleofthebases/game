package com.example.botb.objects;

import android.content.ClipData;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.botb.R;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.xmlpull.v1.XmlPullParser;

public class Droppable {

    private Context context;

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
            int action = event.getAction();

            View view = (View) event.getLocalState();
            LinearLayout container = (LinearLayout) v;
            ViewGroup owner = (ViewGroup) view.getParent();

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
                        owner.removeView(view);
                        container.addView(view);
                    }
                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundDrawable(normalShape);
                default:
                    break;
            }
            return true;
        }
    }

}
