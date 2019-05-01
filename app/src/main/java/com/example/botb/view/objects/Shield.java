package com.example.botb.view.objects;

import android.content.Context;
import com.example.botb.model.placeable.Placeable;


public class Shield extends Draggable {

    private static final String TAG = "Shield";

    public Shield(Context context, Boolean view, Placeable placable, Sprites sprites) {
        super(context);
        this.placable = placable;
        if (view) {
            this.setImageBitmap(sprites.getPlayerShield());
        } else {
            this.StopDrag();
        }
    }

    @Override
    public Object getTag() {
        return TAG;
    }

}
