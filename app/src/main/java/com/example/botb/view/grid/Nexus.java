package com.example.botb.view.grid;


import android.content.Context;
import com.example.botb.model.placeable.Placeable;
import com.example.botb.view.Sprites;


public class Nexus extends GridPlaceable {

    Boolean view;

    public Nexus(Context context, Boolean view, Placeable placable, Sprites sprites) {
        super(context);
        this.name = "Nexus";
        this.view = view;
        this.placable = placable;
        if (view) {
            this.setImageBitmap(sprites.getPlayerNexus());
        } else {
            this.StopDrag();
        }
    }

    @Override
    public void setHit(Sprites sprites){
        hit = true;
        if(view){
            this.setImageBitmap(sprites.getBasepdestroyed());
        } else {
            this.setImageBitmap(sprites.getBaseodestroyed());
        }
    }
}
