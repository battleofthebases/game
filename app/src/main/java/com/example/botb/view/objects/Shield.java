package com.example.botb.view.objects;

import android.content.Context;


public class Shield extends Draggable {

    private static final String TAG = "Shield";

    Sprites sprites = new Sprites(this.getContext());

    public Shield(Context context, Boolean view) {
        super(context);
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
