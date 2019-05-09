package com.example.botb.view.grid;

import android.content.Context;
import com.example.botb.model.placeable.Placeable;
import com.example.botb.view.Sprites;


public class Shield extends GridPlaceable {

    private Boolean view;

    public Shield(Context context, Boolean view, Placeable placable, Sprites sprites) {
        super(context);
        this.view = view;
        this.name = "Shield";
        this.placable = placable;
        if (view) {
            this.setImageBitmap(sprites.getPlayerShield());
        } else {
            this.StopDrag();
        }
    }

    @Override
    public void setHit(Sprites sprites){
        hit = true;
        if(view){
            this.setImageBitmap(sprites.getShieldpdestroyed());
        } else {
            this.setImageBitmap(sprites.getShieldodestroyed());
        }
    }

}
