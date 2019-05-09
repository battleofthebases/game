package com.example.botb.view.grid;

import android.content.Context;
import com.example.botb.view.Sprites;

public class Shot extends GridPlaceable {

    public Shot(Context context, Sprites sprites) {

        super(context);
        this.name = "Shot";
        this.setImageBitmap(sprites.getFlame());
        this.StopDrag();
    }

    @Override
    public void setHit(final Sprites sprites) {

    }
}
