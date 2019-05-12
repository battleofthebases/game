package com.example.botb.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.botb.R;

public class SpriteLoader {

    private static Drawable baseLocal;
    private static Drawable baseLocalDetected;
    private static Drawable baseLocalDestroyed;
    private static Drawable baseRemote;
    private static Drawable baseRemoteDestroyed;
    private static Drawable shieldLocal;
    private static Drawable shieldLocalDestroyed;
    private static Drawable shieldRemote;
    private static Drawable shieldRemoteDestroyed;
    private static Drawable grass;
    private static Drawable sand;
    private static Drawable flames;

    public static void loadSprites(Context context) {
        Resources res = context.getResources();

        baseLocal = res.getDrawable(R.drawable.base_local);
        baseLocalDetected = res.getDrawable(R.drawable.base_local_detected);
        baseLocalDestroyed = res.getDrawable(R.drawable.base_local_destroyed);
        baseRemote = res.getDrawable(R.drawable.base_remote);
        baseRemoteDestroyed = res.getDrawable(R.drawable.base_remote_destroyed);
        shieldLocal = res.getDrawable(R.drawable.shield_local);
        shieldLocalDestroyed = res.getDrawable(R.drawable.shield_local_destroyed);
        shieldRemote = res.getDrawable(R.drawable.shield_remote);
        shieldRemoteDestroyed = res.getDrawable(R.drawable.shield_remote_destroyed);
        grass = res.getDrawable(R.drawable.grass);
        sand = res.getDrawable(R.drawable.sand);
        flames = res.getDrawable(R.drawable.flames);
    }

    public static Drawable getBaseLocal() {
        return baseLocal;
    }

    public static Drawable getBaseLocalDetected() {
        return baseLocalDetected;
    }

    public static Drawable getBaseLocalDestroyed() {
        return baseLocalDestroyed;
    }

    public static Drawable getBaseRemote() {
        return baseRemote;
    }

    public static Drawable getBaseRemoteDestroyed() {
        return baseRemoteDestroyed;
    }

    public static Drawable getShieldLocal() {
        return shieldLocal;
    }

    public static Drawable getShieldLocalDestroyed() {
        return shieldLocalDestroyed;
    }

    public static Drawable getShieldRemote() {
        return shieldRemote;
    }

    public static Drawable getShieldRemoteDestroyed() {
        return shieldRemoteDestroyed;
    }

    public static Drawable getGrass() {
        return grass;
    }

    public static Drawable getSand() {
        return sand;
    }

    public static Drawable getFlames() {
        return flames;
    }

}
