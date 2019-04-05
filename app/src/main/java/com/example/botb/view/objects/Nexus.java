package com.example.botb.view.objects;


import android.content.Context;



public class Nexus extends Draggable {
    private static final String TAG = "Nexus";
    @Override
    public Object getTag() {return TAG; }

    Sprites sprites = new Sprites(this.getContext());

    public Nexus(Context context, Boolean view) {
        super(context);
        if(view){
            this.setImageBitmap(sprites.getPlayerNexus());
        }else {
            this.StopDrag();
        }
    }

}
