package com.example.botb.view.objects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import com.example.botb.R;
import java.util.Random;

public class Sprites {

    private Bitmap overworldb;

    private Bitmap overworldc;

    Sprites(Context con) {
        overworldc = BitmapFactory.decodeResource(con.getResources(), R.drawable.overworldc);
        overworldb = BitmapFactory.decodeResource(con.getResources(), R.drawable.overworldb);
    }


    public BitmapDrawable getFlames() {

        return null;
    }

    public BitmapDrawable getOpponentBackground() {
        Random rand = new Random();
        Bitmap sprite;
        int n = rand.nextInt(6);
        if (n == 1) {
            sprite = Bitmap.createBitmap(overworldc, 253, 296, 80, 75);
        } else {
            sprite = Bitmap.createBitmap(overworldc, 0, 296, 80, 81);
        }
        BitmapDrawable background = new BitmapDrawable(sprite);
        return background;
    }

    public Bitmap getOpponentNexus() {
        return Bitmap.createBitmap(overworldb, 20, 465, 80, 85);
    }

    public Bitmap getOpponentShield() {
        return Bitmap.createBitmap(overworldb, 120, 505, 50, 60);
    }

    public BitmapDrawable getPlayerBackground() {
        Random rand = new Random();
        Bitmap sprite;
        int n = rand.nextInt(6);

        if (n == 1) {
            sprite = Bitmap.createBitmap(overworldc, 253, 45, 80, 75);
        } else {
            sprite = Bitmap.createBitmap(overworldc, 0, 10, 80, 95);
        }
        BitmapDrawable background = new BitmapDrawable(sprite);
        return background;
    }

    public Bitmap getPlayerNexus() {
        return Bitmap.createBitmap(overworldb, 20, 465, 80, 85);
    }

    public Bitmap getPlayerShield() {
        return Bitmap.createBitmap(overworldb, 120, 405, 50, 60);
    }


}
