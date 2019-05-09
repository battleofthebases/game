package com.example.botb.view.grid;

import android.content.Context;
import com.example.botb.model.placeable.Placeable;
import com.example.botb.view.SpriteLoader;


public class Shield extends GridPlaceable {

    private boolean localPlayer;

    public Shield(Context context, boolean localPlayer, Placeable placable, SpriteLoader spriteLoader) {
        super(context);
        this.name = "Shield";
        this.localPlayer = localPlayer;
        this.placable = placable;
        if (localPlayer) {
            this.setImageBitmap(spriteLoader.getPlayerShield());
        } else {
            this.StopDrag();
        }
    }

    @Override
    public void setHit(SpriteLoader spriteLoader) {
        if (localPlayer) {
            this.setImageBitmap(spriteLoader.getShieldpdestroyed());
        } else {
            this.setImageBitmap(spriteLoader.getShieldodestroyed());
        }
    }

}
