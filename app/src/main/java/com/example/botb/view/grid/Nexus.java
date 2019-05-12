package com.example.botb.view.grid;


import android.content.Context;
import com.example.botb.model.placeable.Placeable;
import com.example.botb.view.SpriteLoader;


public class Nexus extends GridPlaceable {

    private boolean localPlayer;

    private boolean detected;

    public Nexus(Context context, boolean localPlayer, Placeable placable) {
        super(context);
        this.name = "Nexus";
        this.localPlayer = localPlayer;
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

    public void detect() {
        this.detected = true;
        if (localPlayer) {
            this.setImageDrawable(SpriteLoader.getBaseLocalDetected());
        } else {
            this.setImageDrawable(SpriteLoader.getBaseRemote());
        }
    }

    public boolean isDetected() {
        return detected;
    }
}
