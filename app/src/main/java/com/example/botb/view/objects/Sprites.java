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
    private Bitmap grass;
    private Bitmap bush;
    private Bitmap sand;
    private Bitmap sandstone;
    private Bitmap basep;
    private Bitmap baseo;
    private Bitmap shieldp;
    private Bitmap shieldo;

    Sprites(Context con) {
        overworldc = BitmapFactory.decodeResource(con.getResources(), R.drawable.overworldc);
        overworldb = BitmapFactory.decodeResource(con.getResources(), R.drawable.overworldb);
        Bitmap grass = BitmapFactory.decodeResource(con.getResources(), R.drawable.grass);
        this.grass = Bitmap.createBitmap(grass, 0,0,50,50);

        Bitmap bush = BitmapFactory.decodeResource(con.getResources(), R.drawable.bush);
        this.bush = Bitmap.createBitmap(bush, 0,0,50,50);

        Bitmap sand = BitmapFactory.decodeResource(con.getResources(), R.drawable.sand);
        this.sand = Bitmap.createBitmap(sand, 0,0,50,50);

        Bitmap sandstone = BitmapFactory.decodeResource(con.getResources(), R.drawable.sandstone);
        this.sandstone = Bitmap.createBitmap(sandstone, 0,0,50,50);

        Bitmap basep = BitmapFactory.decodeResource(con.getResources(), R.drawable.basep);
        this.basep = Bitmap.createBitmap(basep, 0,0,50,50);

        Bitmap baseo = BitmapFactory.decodeResource(con.getResources(), R.drawable.baseo);
        this.baseo = Bitmap.createBitmap(baseo, 0,0,50,50);

        Bitmap shieldp = BitmapFactory.decodeResource(con.getResources(), R.drawable.shieldp);
        this.shieldp = Bitmap.createBitmap(shieldp, 0,0,50,50);

        Bitmap shieldo = BitmapFactory.decodeResource(con.getResources(), R.drawable.shieldo);
        this.shieldo = Bitmap.createBitmap(shieldo, 0,0,50,50);
    }


    public BitmapDrawable getFlames() {

        return null;
    }

    public BitmapDrawable getOpponentBackground() {
        Random rand = new Random();
        Bitmap sprite;
        int n = rand.nextInt(6);
        if (n == 1) {
            sprite = sand;
        } else {
            sprite = sandstone;
        }
        BitmapDrawable background = new BitmapDrawable(sprite);
        return background;
    }

    public Bitmap getOpponentNexus() {
        return baseo;
    }

    public Bitmap getOpponentShield() {
        return shieldo;
    }

    public BitmapDrawable getPlayerBackground() {
        Random rand = new Random();
        Bitmap sprite;
        int n = rand.nextInt(6);

        if (n == 1) {
            sprite = grass;
        } else {
            sprite = bush;
        }
        BitmapDrawable background = new BitmapDrawable(sprite);
        return background;
    }

    public Bitmap getPlayerNexus() { return basep; }

    public Bitmap getPlayerShield() {
        return shieldp;
    }


}
