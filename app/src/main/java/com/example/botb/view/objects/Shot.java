package com.example.botb.view.objects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.botb.R;

public class Shot extends android.support.v7.widget.AppCompatImageView {
    private static final String TAG = "Shot";
    @Override
    public Object getTag() {return TAG; }

    Sprites sprites = new Sprites(this.getContext());
    int frameCount = 5;
    int currentFrame = 0;
    long lastFrameChangeTime = 0;
    int frameLengthInMilliseconds = 100;
    int width = 100;
    int height = 100;

    Bitmap light = BitmapFactory.decodeResource(getResources(), R.drawable.light);


    public Shot(Context context) {

        super(context);
        //while (true){ prøver å mekke bitmap/spritesheet animasjon, stopper opp hele spillet
            Bitmap flame = Bitmap.createBitmap(light, width*currentFrame, 400, width, height);
            //if(getCurrentFrame()){
            this.setImageBitmap(flame);
            //}
        //}
    }

    public Boolean getCurrentFrame(){
        long time  = System.currentTimeMillis();
        if ( time > lastFrameChangeTime + frameLengthInMilliseconds) {
            lastFrameChangeTime = time;
            currentFrame ++;
            if (currentFrame >= frameCount) {

                currentFrame = 0;
            }
            return true;
        }
        return false;
    }






}
