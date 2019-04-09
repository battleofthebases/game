package com.example.botb.view.objects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.example.botb.R;

public class Shot extends android.support.v7.widget.AppCompatImageView {

    private static final String TAG = "Shot";

    int currentFrame = 0;

    int frameCount = 5;

    int frameLengthInMilliseconds = 100;

    int height = 100;

    long lastFrameChangeTime = 0;

    Bitmap light = BitmapFactory.decodeResource(getResources(), R.drawable.light);

    Sprites sprites = new Sprites(this.getContext());

    int width = 100;

    public Shot(Context context) {

        super(context);
        //while (true){ prøver å mekke bitmap/spritesheet animasjon, stopper opp hele spillet
        Bitmap flame = Bitmap.createBitmap(light, width * currentFrame, 400, width, height);
        //if(getCurrentFrame()){
        this.setImageBitmap(flame);
        //}
        //}
    }

    public Boolean getCurrentFrame() {
        long time = System.currentTimeMillis();
        if (time > lastFrameChangeTime + frameLengthInMilliseconds) {
            lastFrameChangeTime = time;
            currentFrame++;
            if (currentFrame >= frameCount) {

                currentFrame = 0;
            }
            return true;
        }
        return false;
    }

    @Override
    public Object getTag() {
        return TAG;
    }


}
