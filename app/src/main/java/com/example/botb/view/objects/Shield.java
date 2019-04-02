package com.example.botb.view.objects;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.botb.R;


public class Shield extends Draggable {
    public Shield(Context context, int frameWidth, int frameHeight, int frameX, int frameY, int addX, int addY) {
        super(context);
        Bitmap spritesheet = BitmapFactory.decodeResource(this.getResources(), R.drawable.overworldb);
        Bitmap sprite = Bitmap.createBitmap(spritesheet, frameWidth*frameX, frameHeight*frameY, frameWidth+addX, frameHeight+addY);
        this.setImageBitmap(sprite);
    }

}
