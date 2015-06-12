package com.gorgonshank.manyfragments.Sprite;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.gorgonshank.manyfragments.Main.SpriteGenerator;

import java.io.Serializable;
import java.util.ArrayList;

public class Sprite implements Serializable{
    transient Drawable sprite;
    transient Bitmap bitmap;
    String prefix;
    String name;
    String suffix;

    public Sprite(){

    }

    public Sprite(Resources resources, Drawable temp, String name, int width, int height){
        this.bitmap = ((BitmapDrawable) temp).getBitmap();
        this.name = name;
        sprite = new BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, width, height, true));
    }

    public int getWidth(){
        return bitmap.getWidth();
    }

    public int getHeight(){
        return bitmap.getHeight();
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Drawable getDrawable(){
        return sprite;
    }

    public void setWidth(Resources resources, int newWidth){
        sprite = new BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, newWidth, getHeight(), true));
        bitmap = ((BitmapDrawable) sprite).getBitmap();
    }

    public void setHeight(Resources resources, int newHeight){
        sprite = new BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, getWidth(), newHeight, true));
        bitmap = ((BitmapDrawable) sprite).getBitmap();
    }

    public Drawable getSprite() {
        return sprite;
    }

    public void setSprite(Drawable sprite) {
        this.sprite = sprite;
    }

    public String toString(){
        return prefix + " " + name + " " + suffix;
    }

    public Drawable getDrawableFromName(){
        ArrayList<ArrayList<? extends Sprite>> sprites = SpriteGenerator.sprites;
        for(ArrayList<? extends Sprite> stuff : sprites){
            for(Sprite thing : stuff){
                if(thing.getName().equals(name)){
                    this.sprite = thing.getSprite();
                    return thing.getSprite();
                }
            }
        }
        return null;
    }

}
