package com.example.botb.view.objects;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.example.botb.R;


public class Nexus extends Draggable {

    public Nexus(Context context, int frameWidth, int frameHeight, int frameX, int frameY, int addX, int addY) {
        super(context);
        Bitmap spritesheet = BitmapFactory.decodeResource(this.getResources(), R.drawable.overworldb);
        Bitmap sprite = Bitmap
                .createBitmap(spritesheet, frameWidth * frameX + 10, frameHeight * frameY + 15, frameWidth,
                        frameHeight - 10);
        this.setImageBitmap(sprite);
    }

}
