package com.example.botb.view.objects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.botb.R;

import android.view.View.OnDragListener;

public class Droppable extends android.support.v7.widget.LinearLayoutCompat{

    public Droppable(Context context,  int frameWidth, int frameHeight, int frameX, int frameY, int addX, int addY) {
        super(context);

        Bitmap spritesheet = BitmapFactory.decodeResource(this.getResources(), R.drawable.overworldc);
        Bitmap sprite = Bitmap.createBitmap(spritesheet, frameWidth*frameX+10, frameHeight*frameY+15, frameWidth, frameHeight-10);
        BitmapDrawable background = new BitmapDrawable(sprite);
        this.setBackgroundDrawable(background);

        this.setOnDragListener(new Droppable.DragListener(background));
    }


    class DragListener implements OnDragListener {
        BitmapDrawable background;
        DragListener(BitmapDrawable background){
            this.background = background;
        }

        Drawable enterShape = getContext().getResources().getDrawable(
                R.drawable.shape_droptarget);
        Drawable noEntryShape = getContext().getResources().getDrawable(R.drawable.no_entry_shape);
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            View view = (View) event.getLocalState();
            Droppable container = (Droppable) v;
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
                    v.setBackgroundDrawable(background);
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
                    v.setBackgroundDrawable(background);
                default:
                    break;
            }
            return true;
        }
    }

}
