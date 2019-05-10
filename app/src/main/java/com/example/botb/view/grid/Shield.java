package com.example.botb.view.grid;

import android.content.Context;
import com.example.botb.model.placeable.Placeable;
import com.example.botb.view.SpriteLoader;


public class Shield extends GridPlaceable {

    private boolean localPlayer;

    public Shield(Context context, boolean localPlayer, Placeable placable) {
        super(context);
        this.name = "Shield";
        this.localPlayer = localPlayer;
        this.placable = placable;
        if (localPlayer) {
            this.setImageDrawable(SpriteLoader.getShieldLocal());
        } else {
            this.StopDrag();
        }
    }

    @Override
    public void setHit() {
        if (localPlayer) {
            this.setImageDrawable(SpriteLoader.getShieldLocalDestroyed());
        } else {
            this.setImageDrawable(SpriteLoader.getShieldRemoteDestroyed());
        }
    }

}
