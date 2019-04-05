package com.example.botb.view.objects;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.botb.R;


public class Shield extends Draggable {
    private static final String TAG = "Shield";
    @Override
    public Object getTag() {return TAG; }

    Sprites sprites = new Sprites(this.getContext());
    public Shield(Context context, Boolean view) {
        super(context);
        if(view){
            this.setImageBitmap(sprites.getPlayerShield());
        } else {
            this.StopDrag();
        }
    }

}
