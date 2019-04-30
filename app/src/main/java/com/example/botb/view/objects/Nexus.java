package com.example.botb.view.objects;


import android.content.Context;
import com.example.botb.model.placeable.Placeable;


public class Nexus extends Draggable {

    private static final String TAG = "Nexus";

    Sprites sprites = new Sprites(this.getContext());

    public Nexus(Context context, Boolean view, Placeable placable) {
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
