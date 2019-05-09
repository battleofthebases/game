package com.example.botb.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import com.example.botb.R;
import java.util.Random;

public class SpriteLoader {

    private Bitmap baseodestroyed;

    private Bitmap basep;

    private Bitmap basepdestroyed;

    private Bitmap bush;

    private Bitmap flame;

    private Bitmap grass;

    private Bitmap plain;

    private Bitmap sand;

    private Bitmap sandstone;

    private float scaleHeight;

    private float scaleWidth;

    private Bitmap shieldodestroyed;

    private Bitmap shieldp;

    private Bitmap shieldpdestroyed;

    public SpriteLoader(Context con, int width, int height) {

        scaleHeight = ((float) height) / 1617;
        scaleWidth = ((float) width) / 1080;

        Bitmap grass = BitmapFactory.decodeResource(con.getResources(), R.drawable.grass);
        this.grass = Bitmap.createBitmap(grass, 0, 0, (int) (85 * scaleWidth), (int) (85 * scaleHeight));

        Bitmap bush = BitmapFactory.decodeResource(con.getResources(), R.drawable.bush);
        this.bush = Bitmap.createBitmap(bush, 0, 0, (int) (85 * scaleWidth), (int) (85 * scaleHeight));

        Bitmap plain = BitmapFactory.decodeResource(con.getResources(), R.drawable.plain);
        this.plain = Bitmap.createBitmap(plain, 0, 0, (int) (85 * scaleWidth), (int) (85 * scaleHeight));

        Bitmap sand = BitmapFactory.decodeResource(con.getResources(), R.drawable.sand);
        this.sand = Bitmap.createBitmap(sand, 0, 0, (int) (85 * scaleWidth), (int) (85 * scaleHeight));

        Bitmap sandstone = BitmapFactory.decodeResource(con.getResources(), R.drawable.sandstone);
        this.sandstone = Bitmap.createBitmap(sandstone, 0, 0, (int) (85 * scaleWidth), (int) (85 * scaleHeight));

        Bitmap basep = BitmapFactory.decodeResource(con.getResources(), R.drawable.basep);
        this.basep = Bitmap.createBitmap(basep, 0, 0, (int) (73 * scaleWidth), (int) (73 * scaleHeight));

        Bitmap shieldp = BitmapFactory.decodeResource(con.getResources(), R.drawable.shieldp);
        this.shieldp = Bitmap.createBitmap(shieldp, 0, 0, (int) (70 * scaleWidth), (int) (70 * scaleHeight));

        Bitmap basepdestroyed = BitmapFactory.decodeResource(con.getResources(), R.drawable.basepdestroyed);
        this.basepdestroyed = Bitmap
                .createBitmap(basepdestroyed, 0, 0, (int) (73 * scaleWidth), (int) (73 * scaleHeight));

        Bitmap baseodestroyed = BitmapFactory.decodeResource(con.getResources(), R.drawable.baseodestroyed);
        this.baseodestroyed = Bitmap
                .createBitmap(baseodestroyed, 0, 0, (int) (73 * scaleWidth), (int) (73 * scaleHeight));

        Bitmap shieldpdestroyed = BitmapFactory.decodeResource(con.getResources(), R.drawable.shieldpdestroyed);
        this.shieldpdestroyed = Bitmap
                .createBitmap(shieldpdestroyed, 0, 0, (int) (70 * scaleWidth), (int) (70 * scaleHeight));

        Bitmap shieldodestroyed = BitmapFactory.decodeResource(con.getResources(), R.drawable.shieldodestoyed);
        this.shieldodestroyed = Bitmap
                .createBitmap(shieldodestroyed, 0, 0, (int) (70 * scaleWidth), (int) (70 * scaleHeight));

        Bitmap flame = BitmapFactory.decodeResource(con.getResources(), R.drawable.flame);
        this.flame = Bitmap.createBitmap(flame, 0, 0, (int) (73 * scaleWidth), (int) (73 * scaleHeight));
    }

    public Bitmap getFlame() {
        return flame;
    }

    public BitmapDrawable getLocalBackground() {
        Random rand = new Random();
        Bitmap sprite;
        int n = rand.nextInt(6);

        if (n == 1) {
            sprite = bush;
        } else if (n == 2) {
            sprite = plain;
        } else {
            sprite = grass;
        }
        BitmapDrawable background = new BitmapDrawable(sprite);
        return background;
    }

    public Bitmap getLocalBaseDestroyed() {
        return basepdestroyed;
    }

    public Bitmap getLocalShieldDestroyed() {
        return shieldpdestroyed;
    }

    public Bitmap getPlayerNexus() {
        return basep;
    }

    public Bitmap getPlayerShield() {
        return shieldp;
    }

    public BitmapDrawable getRemoteBackground() {
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

    public Bitmap getRemoteBaseDestroyed() {
        return baseodestroyed;
    }

    public Bitmap getRemoteShieldDestroyed() {
        return shieldodestroyed;
    }

}
