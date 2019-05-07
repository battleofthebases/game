package com.example.botb.view.objects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.text.Layout;
import android.util.Log;
import com.example.botb.R;
import java.util.Random;

public class Sprites {

    private Bitmap overworldb;
    private Bitmap overworldc;
    private Bitmap grass;
    private Bitmap bush;
    private Bitmap plain;
    private Bitmap sand;
    private Bitmap sandstone;
    private Bitmap basep;
    private Bitmap baseo;
    private Bitmap shieldp;
    private Bitmap shieldo;
    private Bitmap shieldodestroyed;
    private Bitmap shieldpdestroyed;
    private Bitmap baseodestroyed;
    private Bitmap basepdestroyed;
    private Bitmap flame;

    private float scaleWidth;
    private float scaleHeight;

    public Sprites(Context con, int width, int height) {

        scaleHeight = ((float)height) /  1617;
        scaleWidth = ((float)width) /  1080;

        overworldc = BitmapFactory.decodeResource(con.getResources(), R.drawable.overworldc);
        overworldb = BitmapFactory.decodeResource(con.getResources(), R.drawable.overworldb);

        Bitmap grass = BitmapFactory.decodeResource(con.getResources(), R.drawable.grass);
        this.grass = Bitmap.createBitmap(grass, 0,0,(int) (85 *scaleWidth),(int) (85 *scaleHeight));

        Bitmap bush = BitmapFactory.decodeResource(con.getResources(), R.drawable.bush);
        this.bush = Bitmap.createBitmap(bush, 0,0,(int) (85 *scaleWidth),(int) (85 *scaleHeight));

        Bitmap plain = BitmapFactory.decodeResource(con.getResources(), R.drawable.plain);
        this.plain = Bitmap.createBitmap(plain, 0,0,(int) (85 *scaleWidth),(int) (85 *scaleHeight));

        Bitmap sand = BitmapFactory.decodeResource(con.getResources(), R.drawable.sand);
        this.sand = Bitmap.createBitmap(sand, 0,0,(int) (85 *scaleWidth),(int) (85 *scaleHeight));

        Bitmap sandstone = BitmapFactory.decodeResource(con.getResources(), R.drawable.sandstone);
        this.sandstone = Bitmap.createBitmap(sandstone, 0,0,(int) (85 *scaleWidth),(int) (85 *scaleHeight));

        Bitmap basep = BitmapFactory.decodeResource(con.getResources(), R.drawable.basep);
        this.basep = Bitmap.createBitmap(basep, 0,0,(int) (73 *scaleWidth),(int) (73 *scaleHeight));

        Bitmap baseo = BitmapFactory.decodeResource(con.getResources(), R.drawable.baseo);
        this.baseo = Bitmap.createBitmap(baseo, 0,0,(int) (73 *scaleWidth),(int) (73 *scaleHeight));

        Bitmap shieldp = BitmapFactory.decodeResource(con.getResources(), R.drawable.shieldp);
        this.shieldp = Bitmap.createBitmap(shieldp, 0,0,(int) (70 *scaleWidth),(int) (70 *scaleHeight));

        Bitmap shieldo = BitmapFactory.decodeResource(con.getResources(), R.drawable.shieldo);
        this.shieldo = Bitmap.createBitmap(shieldo, 0,0,(int) (70 *scaleWidth),(int) (70 *scaleHeight));

        Bitmap basepdestroyed = BitmapFactory.decodeResource(con.getResources(), R.drawable.basepdestroyed);
        this.basepdestroyed = Bitmap.createBitmap(basepdestroyed, 0,0,(int) (73 *scaleWidth),(int) (73 *scaleHeight));

        Bitmap baseodestroyed = BitmapFactory.decodeResource(con.getResources(), R.drawable.baseodestroyed);
        this.baseodestroyed = Bitmap.createBitmap(baseodestroyed, 0,0,(int) (73 *scaleWidth),(int) (73 *scaleHeight));

        Bitmap shieldpdestroyed = BitmapFactory.decodeResource(con.getResources(), R.drawable.shieldpdestroyed);
        this.shieldpdestroyed = Bitmap.createBitmap(shieldpdestroyed, 0,0,(int) (70 *scaleWidth),(int) (70 *scaleHeight));

        Bitmap shieldodestroyed = BitmapFactory.decodeResource(con.getResources(), R.drawable.shieldodestoyed);
        this.shieldodestroyed = Bitmap.createBitmap(shieldodestroyed, 0,0,(int) (70 *scaleWidth),(int) (70 *scaleHeight));

        Bitmap flame = BitmapFactory.decodeResource(con.getResources(), R.drawable.flame);
        this.flame = Bitmap.createBitmap(flame, 0,0,(int) (73 *scaleWidth),(int) (73 *scaleHeight));
    }

    public Bitmap getShieldodestroyed() {
        return shieldodestroyed;
    }

    public Bitmap getShieldpdestroyed() {
        return shieldpdestroyed;
    }

    public Bitmap getBaseodestroyed() {
        return baseodestroyed;
    }

    public Bitmap getBasepdestroyed() {
        return basepdestroyed;
    }

    public Bitmap getFlame() {
        return flame;
    }

    public BitmapDrawable getOpponentBackground() {
        Random rand = new Random();
        Bitmap sprite;
        int n = rand.nextInt(6);
        if (n == 1) {
            sprite = sandstone;
        } else {
            sprite = sand;
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
            sprite = bush;
        } else if(n == 2) {
            sprite = plain;
        } else {
            sprite = grass;
        }
        BitmapDrawable background = new BitmapDrawable(sprite);
        return background;
    }

    public Bitmap getPlayerNexus() { return basep; }

    public Bitmap getPlayerShield() {
        return shieldp;
    }


}
