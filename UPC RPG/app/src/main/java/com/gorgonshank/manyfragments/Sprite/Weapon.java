package com.gorgonshank.manyfragments.Sprite;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class Weapon extends Sprite {

    int attack;

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public Weapon(Resources resources, Drawable temp, String name, int width, int height) {
        super(resources, temp, name, width, height);
    }

    public Weapon(){

    }

    public String toString(){
        String superToString = super.toString();
        return superToString + "\nAttack is: " + attack;
    }
}
