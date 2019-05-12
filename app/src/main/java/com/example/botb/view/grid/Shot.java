package com.example.botb.view.grid;

import android.content.Context;
import com.example.botb.view.SpriteLoader;

public class Shot extends GridPlaceable {

    public Shot(Context context) {
        super(context);
        this.name = "Shot";
        this.setImageDrawable(SpriteLoader.getFlames());
        this.StopDrag();
    }

    @Override
    public void setHit() {

    }

}
