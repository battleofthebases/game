package com.example.botb.view.grid;


import android.content.Context;
import com.example.botb.model.placeable.Placeable;
import com.example.botb.view.SpriteLoader;


public class Nexus extends GridPlaceable {

    private boolean localPlayer;

    public Nexus(Context context, boolean localPlayer, Placeable placable) {
        super(context);
        this.name = "Nexus";
        this.localPlayer = localPlayer;
        this.placable = placable;
        if (localPlayer) {
            this.setImageDrawable(SpriteLoader.getBaseLocal());
        } else {
            this.StopDrag();
        }
    }

    @Override
    public void setHit() {
        if (localPlayer) {
            this.setImageDrawable(SpriteLoader.getBaseLocalDestroyed());
        } else {
            this.setImageDrawable(SpriteLoader.getBaseRemoteDestroyed());
        }
    }
}
