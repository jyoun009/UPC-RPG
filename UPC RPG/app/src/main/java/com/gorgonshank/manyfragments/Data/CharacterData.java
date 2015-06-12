package com.gorgonshank.manyfragments.Data;


import com.gorgonshank.manyfragments.Main.SpriteGenerator;
import com.gorgonshank.manyfragments.Sprite.Armor;
import com.gorgonshank.manyfragments.Sprite.Sprite;
import com.gorgonshank.manyfragments.Sprite.Weapon;

import java.util.ArrayList;

public class CharacterData {

    public static long hit_points = 1000;

    public static long max_hit_points = 1000;

    public static long attack = 200;

    public static long defense = 50;

    public static long experience = 0;

    public static long skill_points = 10;

    public static long max_skill_points = 20;

    public static Armor armor = SpriteGenerator.armorGenerator(3);

    public static Armor shoes = SpriteGenerator.armorGenerator(4);

    public static Armor gloves = SpriteGenerator.armorGenerator(5);

    public static Weapon weapon1 = SpriteGenerator.weaponGenerator(0);

    public static Character characterSprite;

    public static ArrayList<Sprite> loot = new ArrayList<Sprite>();

    public static Armor getGloves() {
        return gloves;
    }

    public static void setGloves(Armor gloves) {
        CharacterData.gloves = gloves;
    }

    public static ArrayList<Sprite> getLoot() {
        return loot;
    }

    public static void setLoot(ArrayList<Sprite> loot) {
        CharacterData.loot = loot;
    }

    public static void addLoot(Sprite loot){
        CharacterData.loot.add(loot);
    }

    public static Armor getArmor() {
        return armor;
    }

    public static void setArmor(Armor armor) {
        CharacterData.armor = armor;
    }

    public static Armor getShoes() {
        return shoes;
    }

    public static void setShoes(Armor shoes) {
        CharacterData.shoes = shoes;
    }

    public static Weapon getWeapon1() {
        return weapon1;
    }

    public static void setWeapon1(Weapon weapon) {
        CharacterData.weapon1 = weapon;
    }

    public static Character getCharacterSprite() {
        return characterSprite;
    }

    public static void setCharacterSprite(Character characterSprite) {
        CharacterData.characterSprite = characterSprite;
    }

    public static long getAttack() {
        return attack;
    }

    public static void setAttack(long attack) {
        CharacterData.attack = attack
                //+ weapon1.getAttack() + weapon2.getAttack()
                ;
    }

    public static long getDefense() {
        return defense;
    }

    public static void setDefense(long defense) {
        CharacterData.defense = defense
                //+ armor.getDefense() + shoes.getDefense() + gloves.getDefense()
                ;
    }

    public static long getExperience() {
        return experience;
    }

    public static void setExperience(long experience) {
        CharacterData.experience = experience;
    }

    public static long getHit_points() {
        return hit_points;
    }

    public static void setHit_points(long hit_points) {
        CharacterData.hit_points = hit_points;
    }

    public static long getMax_hit_points() {
        return max_hit_points;
    }

    public static void setMax_hit_points(long max_hit_points) {
        CharacterData.max_hit_points = max_hit_points
                //+ armor.getHp() + (int)(shoes.getHp() *.6) + (int)(gloves.getHp() * 0.4)
                ;
    }

    public static long getSkill_points() {
        return skill_points;
    }

    public static void setSkill_points(long skill_points) {
        CharacterData.skill_points = skill_points;
    }


    public static long getMax_skill_points() {
        return max_skill_points;
    }

    public static void setMax_skill_points(long max_skill_points) {
        CharacterData.max_skill_points = max_skill_points;
    }








}
