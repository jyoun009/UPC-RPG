package com.gorgonshank.manyfragments.Main;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;


import com.gorgonshank.manyfragments.R;
import com.gorgonshank.manyfragments.Sprite.Armor;
import com.gorgonshank.manyfragments.Sprite.Enemy;
import com.gorgonshank.manyfragments.Sprite.Item;
import com.gorgonshank.manyfragments.Sprite.Sprite;
import com.gorgonshank.manyfragments.Sprite.Weapon;
import com.gorgonshank.manyfragments.Sprite.Character;

import java.util.ArrayList;
import java.util.Random;



public class SpriteGenerator {
    public static final double ENEMY_OCCURRENCE = .999;
    public static final double ITEM_OCCURRENCE = 0.25;
    public static final double WEAPON_OCCURRENCE = 0.2;
    public static final double ARMOR_OCCURRENCE = 0.1;
    public static final double CHARACTER_OCCURRENCE = 0.001;

    public static ArrayList<ArrayList<? extends Sprite>> sprites = new ArrayList<ArrayList<? extends Sprite>>();
    public static String[] prefixes = {"Omnipotent", "God Like", "Supreme", "Great", "Plain", "Sad", "Broken", "Putrid", "Talking"};
    public static String[] suffixes = {"of Sandwich Making", "of Brilliance", "of The Wolf", "of The Fox", "of Sadness",
                                        "of Otherworldlyness", "of The Divine", "of Supremacy", "of Greatness", "of Plainness",
                                        "of Brokenness", "of Putridness", "of Blabbermouth", "of Wretchedness", "of the Deep",
                                        "of Randiliciousness", "of ChristianSh*t :(", "of Jontastic", "of 594ness"};

    public static boolean hasLoaded = false;
    
    public static Sprite generateSprite(int barcode){
        Random r = new Random();
        double percentage = r.nextDouble();

        if(percentage <= CHARACTER_OCCURRENCE){
//            int characterPos = barcode % sprites.get(4).size();
//            return characterGenerator(characterPos);
            Log.i("Sprite", "Holy carp a character was born!");
            //TODO generate character

        }
        else if(percentage <= ARMOR_OCCURRENCE){
            int armorPos = toPositiveModulo(barcode % sprites.get(3).size(), sprites.get(3).size());
            return armorGenerator(armorPos);
        }
        else if(percentage <= WEAPON_OCCURRENCE){
            int weaponPos = toPositiveModulo(barcode % sprites.get(2).size(), sprites.get(2).size());
            return weaponGenerator(weaponPos);
        }
        else if(percentage <= ITEM_OCCURRENCE){
            int itemPos = toPositiveModulo(barcode % sprites.get(1).size(), sprites.get(1).size());
            return itemGenerator(itemPos);
        }
        else if(percentage <= ENEMY_OCCURRENCE){
            int enemyPos = toPositiveModulo(barcode % sprites.get(0).size(), sprites.get(0).size());
            return enemyGenerator(enemyPos);
        }
        return null;
    }

    public static Enemy enemyGenerator(int enemyPos){
        Enemy enemy = (Enemy) sprites.get(0).get(enemyPos);
        String suffix = rollSuffix();
        String prefix = rollPrefix();
        enemy.setSuffix(suffix);
        enemy.setPrefix(prefix);
        return enemy;
    }

    public static Item itemGenerator(int itemPos){
        Item item = (Item) sprites.get(1).get(itemPos);
        String suffix = rollSuffix();
        String prefix = rollPrefix();
        //TODO need to have item stats to set
        item.setSuffix(suffix);
        item.setPrefix(prefix);
        return item;
    }


    public static Weapon weaponGenerator(int weaponPos){
        Weapon weapon = (Weapon) sprites.get(2).get(weaponPos);
        String suffix = rollSuffix();
        String prefix = rollPrefix();
        int attack = generateNumberByPrefix(prefix);
        weapon.setSuffix(suffix);
        weapon.setPrefix(prefix);
        weapon.setAttack(attack);
        return weapon;
    }

    public static Armor armorGenerator(int armorPos){
        Armor armor = (Armor) sprites.get(3).get(armorPos);
        String suffix = rollSuffix();
        String prefix = rollPrefix();
        int defense = generateNumberByPrefix(prefix);
        int hp = generateNumberByPrefix(prefix);
        armor.setSuffix(suffix);
        armor.setPrefix(prefix);
        armor.setDefense(defense);
        armor.setHp(hp);
        return armor;
    }

    public static int generateNumberByPrefix(String prefix){
        int prefixIndex = findIndexOfPrefix(prefix);
        Random r = new Random();
        switch(prefixIndex){
            case 0:
                return r.nextInt(1000) + 500;
            case 1:
                return r.nextInt(200) + 100;
            case 2:
                return r.nextInt(100) + 50;
            case 3:
                return r.nextInt(50) + 10;
            case 4:
                return r.nextInt(10) + 5;
            case 5:
                return r.nextInt(2) + 1;
            case 6:
                return -1 * (r.nextInt(20) + 10);
            case 7:
                return -1 * (r.nextInt(200) + 100);
            case 8:
                return r.nextInt(10000) + 5000;
        }
        return 0;
    }

    public static int generateEnemyModifierByPrefix(String prefix){
        int prefixIndex = findIndexOfPrefix(prefix);
        Random r = new Random();
        switch(prefixIndex){
            case 0:
                return r.nextInt(6) + 4;
            case 1:
                return r.nextInt(4) + 2;
            case 2:
                return r.nextInt(4) + 1;
            case 3:
                return r.nextInt(3) + 1;
            case 4:
                return r.nextInt(3) + 1;
            case 5:
                return r.nextInt(3) + 1;
            case 6:
                return r.nextInt(2) + 1;
            case 7:
                return r.nextInt(2) + 1;
            case 8:
                return r.nextInt(10000) + 5000;
        }
        return 0;
    }

    public static String rollSuffix(){
        Random r = new Random();
        int num = r.nextInt(suffixes.length);
        Log.i("Roll", "Suffix roll = " + num);
        return suffixes[num];
    }

    public static String rollPrefix(){
        Random r = new Random();
        double threshold = r.nextDouble();
        Log.i("Roll", "Prefix roll = " + threshold);
        if(threshold <= 0.01){
            return prefixes[0];
        }
        else if(threshold <= 0.05){
            return prefixes[1];
        }
        else if(threshold <= 0.15){
            return prefixes[2];
        }
        else if(threshold <= 0.25){
            return prefixes[3];
        }
        else if(threshold <= 0.75){
            return prefixes[4];
        }
        else if(threshold <= 0.85){
            return prefixes[5];
        }
        else if(threshold <= 0.95){
            return prefixes[6];
        }
        else if(threshold <= 0.98){
            return prefixes[7];
        }
        else if(threshold <= 0.99){
            return prefixes[8];
        }
        return prefixes[7];
    }

    public static int findIndexOfPrefix(String prefix){
        for(int i = 0; i < prefixes.length; i++){
            if(prefix.equals(prefixes[i])){
                return i;
            }
        }
        return 7;
    }

    public static int toPositiveModulo(int num, int modulo){
        int posNum = num;
        while(posNum < 0){
            posNum += modulo;
        }
        return posNum;
    }

    public static void initDrawables(Resources r, int drawableWidth, int drawableHeight){
        ArrayList<Armor> armors = new ArrayList<Armor>();
        ArrayList<Character> characters = new ArrayList<Character>(); //not yet implemented
        ArrayList<Enemy> enemies = new ArrayList<Enemy>(); //not yet implemented
        ArrayList<Item> items = new ArrayList<Item>();
        ArrayList<Weapon> weapons = new ArrayList<Weapon>();

        Drawable drawable = r.getDrawable(R.drawable.agate);
        Item agate = new Item(r, drawable, "Agate", drawableWidth, drawableHeight);
        items.add(agate);

        drawable = r.getDrawable(R.drawable.antidote);
        Item antidote = new Item(r, drawable, "Antidote", drawableWidth, drawableHeight);
        items.add(antidote);

        drawable = r.getDrawable(R.drawable.apple);
        Item apple = new Item(r, drawable, "Apple", drawableWidth, drawableHeight);
        items.add(apple);

        drawable = r.getDrawable(R.drawable.armor1);
        Armor armor1 = new Armor(r, drawable, "Leather Chestplate", drawableWidth, drawableHeight);
        armors.add(armor1);

        drawable = r.getDrawable(R.drawable.armor2);
        Armor armor2 = new Armor(r, drawable, "Silver Chestplate", drawableWidth, drawableHeight);
        armors.add(armor2);

        drawable = r.getDrawable(R.drawable.armor3);
        Armor armor3 = new Armor(r, drawable, "Golden Chestplate", drawableWidth, drawableHeight);
        armors.add(armor3);

        drawable = r.getDrawable(R.drawable.axe1);
        Weapon wep1 = new Weapon(r, drawable, "Hachet", drawableWidth, drawableHeight);
        weapons.add(wep1);

        drawable = r.getDrawable(R.drawable.axe2);
        Weapon wep2 = new Weapon(r, drawable, "Axe" ,drawableWidth, drawableHeight);
        weapons.add(wep2);

        drawable = r.getDrawable(R.drawable.axe3);
        Weapon wep3 = new Weapon(r, drawable, "Great Axe", drawableWidth, drawableHeight);
        weapons.add(wep3);

        drawable = r.getDrawable(R.drawable.banana);
        Item item1 = new Item(r, drawable, "Banana :D", drawableWidth, drawableHeight);
        items.add(item1);

        drawable = r.getDrawable(R.drawable.bluepotion1);
        Item item2 = new Item(r, drawable, "Potent Blue Potion", drawableWidth, drawableHeight);
        items.add(item2);

        drawable = r.getDrawable(R.drawable.bluepotion2);
        Item item3 = new Item(r, drawable, "Blue Potion", drawableWidth, drawableHeight);
        items.add(item3);

        drawable = r.getDrawable(R.drawable.bow1);
        Weapon wep4 = new Weapon(r, drawable, "Bow", drawableWidth, drawableHeight);
        weapons.add(wep4);

        drawable = r.getDrawable(R.drawable.bow2);
        Weapon wep5 = new Weapon(r, drawable, "Long Bow", drawableWidth, drawableHeight);
        weapons.add(wep5);

        drawable = r.getDrawable(R.drawable.bow3);
        Weapon wep6 = new Weapon(r, drawable, "Compound Bow", drawableWidth, drawableHeight);
        weapons.add(wep6);

        drawable = r.getDrawable(R.drawable.chestclosed);
        Item item4 = new Item(r, drawable, "Closed Chest", drawableWidth, drawableHeight);
        items.add(item4);

        drawable = r.getDrawable(R.drawable.chestopen);
        Item item5 = new Item(r, drawable, "Open Chest", drawableWidth, drawableHeight);
        items.add(item5);

        drawable = r.getDrawable(R.drawable.crystal);
        Item item6 = new Item(r, drawable, "Crystal", drawableWidth, drawableHeight);
        items.add(item6);

        drawable = r.getDrawable(R.drawable.dagger1);
        Weapon wep7 = new Weapon(r, drawable, "Short Dagger", drawableWidth, drawableHeight);
        weapons.add(wep7);

        drawable = r.getDrawable(R.drawable.dagger2);
        Weapon wep8 = new Weapon(r, drawable, "Pointy Dagger", drawableWidth, drawableHeight);
        weapons.add(wep8);

        drawable = r.getDrawable(R.drawable.dagger3);
        Weapon wep9 = new Weapon(r, drawable, "Curved Dagger", drawableWidth, drawableHeight);
        weapons.add(wep9);

        drawable = r.getDrawable(R.drawable.diamond);
        Item item7 = new Item(r, drawable, "Diamond", drawableWidth, drawableHeight);
        items.add(item7);

        drawable = r.getDrawable(R.drawable.gloves1);
        Armor armor4 = new Armor(r, drawable, "Leather Gloves", drawableWidth, drawableHeight);
        armors.add(armor4);

        drawable = r.getDrawable(R.drawable.gloves2);
        Armor armor5 = new Armor(r, drawable, "Moss Gloves", drawableWidth, drawableHeight);
        armors.add(armor5);

        drawable = r.getDrawable(R.drawable.gloves3);
        Armor armor6 = new Armor(r, drawable, "Silver Gloves", drawableWidth, drawableHeight);
        armors.add(armor6);

        drawable = r.getDrawable(R.drawable.goldbar);
        Item item8 = new Item(r, drawable, "Gold Bar", drawableWidth, drawableHeight);
        items.add(item8);

        drawable = r.getDrawable(R.drawable.goldcoin);
        Item item9 = new Item(r, drawable, "Gold Coin", drawableWidth, drawableHeight);
        items.add(item9);

        drawable = r.getDrawable(R.drawable.grapes);
        Item item10 = new Item(r, drawable, "Grapes", drawableWidth, drawableHeight);
        items.add(item10);

        drawable = r.getDrawable(R.drawable.greenpotion1);
        Item item11 = new Item(r, drawable, "Potent Green Potion", drawableWidth, drawableHeight);
        items.add(item11);

        drawable = r.getDrawable(R.drawable.greenpotion2);
        Item item12 = new Item(r, drawable, "Green Potion", drawableWidth, drawableHeight);
        items.add(item12);

        drawable = r.getDrawable(R.drawable.mace1);
        Weapon wep10 = new Weapon(r, drawable, "Club", drawableWidth, drawableHeight);
        weapons.add(wep10);

        drawable = r.getDrawable(R.drawable.mace2);
        Weapon wep11 = new Weapon(r, drawable, "Mace", drawableWidth, drawableHeight);
        weapons.add(wep11);

        drawable = r.getDrawable(R.drawable.mace3);
        Weapon wep12 = new Weapon(r, drawable, "Hammer", drawableWidth, drawableHeight);
        weapons.add(wep12);

        drawable = r.getDrawable(R.drawable.mushroom);
        Item item13 = new Item(r, drawable, "Mushroom", drawableWidth, drawableHeight);
        items.add(item13);

        drawable = r.getDrawable(R.drawable.redpotion1);
        Item item14 = new Item(r, drawable, "Potent Red Potion", drawableWidth, drawableHeight);
        items.add(item14);

        drawable = r.getDrawable(R.drawable.redpotion2);
        Item item15 = new Item(r, drawable, "Red Potion", drawableWidth, drawableHeight);
        items.add(item15);

        drawable = r.getDrawable(R.drawable.ruby);
        Item item16 = new Item(r, drawable, "Ruby", drawableWidth, drawableHeight);
        items.add(item16);

        drawable = r.getDrawable(R.drawable.sapphire);
        Item item17 = new Item(r, drawable, "Sapphire", drawableWidth, drawableHeight);
        items.add(item17);

        drawable = r.getDrawable(R.drawable.shoes1);
        Armor armor8 = new Armor(r, drawable, "Leather Shoes", drawableWidth, drawableHeight);
        armors.add(armor8);

        drawable = r.getDrawable(R.drawable.shoes2);
        Armor armor9 = new Armor(r, drawable, "Magic Shoes", drawableWidth, drawableHeight);
        armors.add(armor9);

        drawable = r.getDrawable(R.drawable.shoes3);
        Armor armor10 = new Armor(r, drawable, "Iron Shoes", drawableWidth, drawableHeight);
        armors.add(armor10);

        drawable = r.getDrawable(R.drawable.spear1);
        Weapon wep13 = new Weapon(r, drawable, "Spear", drawableWidth, drawableHeight);
        weapons.add(wep13);

        drawable = r.getDrawable(R.drawable.spear2);
        Weapon wep14 = new Weapon(r, drawable, "Halberd", drawableWidth, drawableHeight);
        weapons.add(wep14);

        drawable = r.getDrawable(R.drawable.spear3);
        Weapon wep15 = new Weapon(r, drawable, "Throwing Spear", drawableWidth, drawableHeight);
        weapons.add(wep15);

        drawable = r.getDrawable(R.drawable.sword1);
        Weapon wep16 = new Weapon(r, drawable, "Short Sword", drawableWidth, drawableHeight);
        weapons.add(wep16);

        drawable = r.getDrawable(R.drawable.sword2);
        Weapon wep17 = new Weapon(r, drawable, "Long Sword", drawableWidth, drawableHeight);
        weapons.add(wep17);

        drawable = r.getDrawable(R.drawable.sword3);
        Weapon wep18 = new Weapon(r, drawable, "Claymore", drawableWidth, drawableHeight);
        weapons.add(wep18);

        drawable = r.getDrawable(R.drawable.sword4);
        Weapon wep19 = new Weapon(r, drawable, "Excalibur", drawableWidth, drawableHeight);
        weapons.add(wep19);

        drawable = r.getDrawable(R.drawable.sword5);
        Weapon wep20 = new Weapon(r, drawable, "Masamune", drawableWidth, drawableHeight);
        weapons.add(wep20);

        drawable = r.getDrawable(R.drawable.sword6);
        Weapon wep21 = new Weapon(r, drawable, "Aeon Blade", drawableWidth, drawableHeight);
        weapons.add(wep21);

//        drawable = r.getDrawable(R.drawable.boss1);
//        Enemy boss1 = new Enemy(r, drawable, "Rel\'kan", drawableWidth, drawableHeight);
//        enemies.add(boss1);

        drawable = r.getDrawable(R.drawable.enemy1);
        Enemy enemy1 = new Enemy(r, drawable, "Clay Giant", drawableWidth, drawableHeight);
        enemies.add(enemy1);

        drawable = r.getDrawable(R.drawable.enemy2);
        Enemy enemy2 = new Enemy(r, drawable, "Demon Squid", drawableWidth, drawableHeight);
        enemies.add(enemy2);

        drawable = r.getDrawable(R.drawable.enemy3);
        Enemy enemy3 = new Enemy(r, drawable, "Harpie", drawableWidth, drawableHeight);
        enemies.add(enemy3);

        drawable = r.getDrawable(R.drawable.enemy4);
        Enemy enemy4 = new Enemy(r, drawable, "Rat Wizard", drawableWidth, drawableHeight);
        enemies.add(enemy4);

        drawable = r.getDrawable(R.drawable.enemy5);
        Enemy enemy5 = new Enemy(r, drawable, "Green Dragon", drawableWidth, drawableHeight);
        enemies.add(enemy5);

        drawable = r.getDrawable(R.drawable.enemy6);
        Enemy enemy6 = new Enemy(r, drawable, "Wyvern", drawableWidth, drawableHeight);
        enemies.add(enemy6);

        drawable = r.getDrawable(R.drawable.enemy7);
        Enemy enemy7 = new Enemy(r, drawable, "Orc Warrior", drawableWidth, drawableHeight);
        enemies.add(enemy7);

        drawable = r.getDrawable(R.drawable.enemy8);
        Enemy enemy8 = new Enemy(r, drawable, "Lion Solider", drawableWidth, drawableHeight);
        enemies.add(enemy8);

        drawable = r.getDrawable(R.drawable.enemy9);
        Enemy enemy9 = new Enemy(r, drawable, "Cloud Dragon", drawableWidth, drawableHeight);
        enemies.add(enemy9);

        drawable = r.getDrawable(R.drawable.enemy10);
        Enemy enemy10 = new Enemy(r, drawable, "Gargoyle", drawableWidth, drawableHeight);
        enemies.add(enemy10);

        drawable = r.getDrawable(R.drawable.enemy11);
        Enemy enemy11 = new Enemy(r, drawable, "Pumpkin \'o Doom", drawableWidth, drawableHeight);
        enemies.add(enemy11);

        drawable = r.getDrawable(R.drawable.enemy12);
        Enemy enemy12 = new Enemy(r, drawable, "Crystal Juggernaut", drawableWidth, drawableHeight);
        enemies.add(enemy12);

        drawable = r.getDrawable(R.drawable.enemy13);
        Enemy enemy13 = new Enemy(r, drawable, "Beholder", drawableWidth, drawableHeight);
        enemies.add(enemy13);

        sprites.add(enemies);
        sprites.add(items);
        sprites.add(weapons);
        sprites.add(armors);
        sprites.add(characters);



    }
}
