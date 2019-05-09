package com.example.botb.view.grid;

import android.content.Context;
import com.example.botb.model.placeable.Placeable;
import com.example.botb.view.SpriteLoader;


public class Shield extends GridPlaceable {

    private Boolean view;

    public Shield(Context context, Boolean view, Placeable placable, SpriteLoader spriteLoader) {
        super(context);
        this.view = view;
        this.name = "Shield";
        this.placable = placable;
        if (view) {
            this.setImageBitmap(spriteLoader.getPlayerShield());
        } else {
            this.StopDrag();
        }
    }

    @Override
    public void setHit(SpriteLoader spriteLoader){
        hit = true;
        if(view){
            this.setImageBitmap(spriteLoader.getShieldpdestroyed());
        } else {
            this.setImageBitmap(spriteLoader.getShieldodestroyed());
        }
    }

}
