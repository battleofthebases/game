package com.example.botb.view.objects;


import android.content.Context;
import com.example.botb.model.placeable.Placeable;


public class Nexus extends Draggable {

    private static final String TAG = "Nexus";

    public Nexus(Context context, Boolean view, Placeable placable, Sprites sprites) {
        super(context);
        this.placable = placable;
        if (view) {
            this.setImageBitmap(sprites.getPlayerNexus());
        } else {
            this.StopDrag();
        }
    }

    @Override
    public Object getTag() {
        return TAG;
    }

}
