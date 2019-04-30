package com.example.botb.view.objects;

import android.content.Context;
import com.example.botb.model.placeable.Placeable;


public class Shield extends Draggable {

    private static final String TAG = "Shield";

    Sprites sprites = new Sprites(this.getContext());

    public Shield(Context context, Boolean view, Placeable placable) {
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
