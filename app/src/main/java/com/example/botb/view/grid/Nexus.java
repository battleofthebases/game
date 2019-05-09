package com.example.botb.view.grid;


import android.content.Context;
import com.example.botb.model.placeable.Placeable;
import com.example.botb.view.SpriteLoader;


public class Nexus extends GridPlaceable {

    private boolean localPlayer;

    public Nexus(Context context, boolean localPlayer, Placeable placable, SpriteLoader spriteLoader) {
        super(context);
        this.name = "Nexus";
        this.localPlayer = localPlayer;
        this.placable = placable;
        if (localPlayer) {
            this.setImageBitmap(spriteLoader.getPlayerNexus());
        } else {
            this.StopDrag();
        }
    }

    @Override
    public void setHit(SpriteLoader spriteLoader) {
        if (localPlayer) {
            this.setImageBitmap(spriteLoader.getBasepdestroyed());
        } else {
            this.setImageBitmap(spriteLoader.getBaseodestroyed());
        }
    }
}
