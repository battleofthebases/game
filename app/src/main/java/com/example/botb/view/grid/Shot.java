package com.example.botb.view.grid;

import android.content.Context;
import com.example.botb.view.SpriteLoader;

public class Shot extends GridPlaceable {

    public Shot(Context context, SpriteLoader spriteLoader) {

        super(context);
        this.name = "Shot";
        this.setImageBitmap(spriteLoader.getFlame());
        this.StopDrag();
    }

    @Override
    public void setHit(final SpriteLoader spriteLoader) {

    }
}
