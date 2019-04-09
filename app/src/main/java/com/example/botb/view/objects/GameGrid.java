package com.example.botb.view.objects;

import android.content.Context;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;

public class GameGrid extends android.support.v7.widget.GridLayout {

    class DragListener implements OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {

            View view = (View) event.getLocalState();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    return false;
                case DragEvent.ACTION_DRAG_ENDED:
                    view.setVisibility(View.VISIBLE);
                default:
                    break;
            }
            return true;
        }
    }

    public GameGrid(Context context) {
        super(context);
        this.setOnDragListener(new DragListener());
    }

    public GameGrid(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnDragListener(new DragListener());
    }


    public GameGrid(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setOnDragListener(new DragListener());
    }

}
