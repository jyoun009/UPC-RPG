package com.gorgonshank.manyfragments.Main;

import com.gorgonshank.manyfragments.Sprite.Armor;
import com.gorgonshank.manyfragments.Sprite.Item;
import com.gorgonshank.manyfragments.Sprite.Sprite;
import com.gorgonshank.manyfragments.Sprite.Weapon;

import java.util.ArrayList;
import java.util.Random;

public class LootGenerator {
    public static Sprite generateLoot(){
        ArrayList<ArrayList<? extends Sprite>> sprites = SpriteGenerator.sprites;
        ArrayList<Armor> armors = (ArrayList<Armor>)sprites.get(3);
        ArrayList<Weapon> weapons = (ArrayList<Weapon>)sprites.get(2);
        ArrayList<Item> items = (ArrayList<Item>)sprites.get(1);

        Random r = new Random();
        int spriteLoot = r.nextInt(3);

        if(spriteLoot == 0){
            return SpriteGenerator.armorGenerator(r.nextInt(armors.size()));
        }
        else if(spriteLoot == 1){
            return SpriteGenerator.weaponGenerator(r.nextInt(weapons.size()));
        }
        else{
            return SpriteGenerator.itemGenerator(r.nextInt(items.size()));
        }
    }
}
