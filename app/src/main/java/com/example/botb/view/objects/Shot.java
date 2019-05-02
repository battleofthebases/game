package com.example.botb.view.objects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.example.botb.R;

public class Shot extends Draggable {

    public Shot(Context context, Sprites sprites) {

        super(context);
        this.name = "Shot";
        this.setImageBitmap(sprites.getFlame());
        this.StopDrag();
    }

    @Override
    public void setHit(final Sprites sprites) {

    }
}
