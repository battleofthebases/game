package com.example.botb.view.grid;


import android.content.Context;
import com.example.botb.model.placeable.Placeable;
import com.example.botb.view.SpriteLoader;


public class Nexus extends GridPlaceable {

    Boolean view;

    public Nexus(Context context, Boolean view, Placeable placable, SpriteLoader spriteLoader) {
        super(context);
        this.name = "Nexus";
        this.view = view;
        this.placable = placable;
        if (view) {
            this.setImageBitmap(spriteLoader.getPlayerNexus());
        } else {
            this.StopDrag();
        }
    }

    @Override
    public void setHit(SpriteLoader spriteLoader){
        hit = true;
        if(view){
            this.setImageBitmap(spriteLoader.getBasepdestroyed());
        } else {
            this.setImageBitmap(spriteLoader.getBaseodestroyed());
        }
    }
}
