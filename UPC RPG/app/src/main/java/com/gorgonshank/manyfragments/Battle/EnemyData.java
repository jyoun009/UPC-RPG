package com.gorgonshank.manyfragments.Battle;

import com.gorgonshank.manyfragments.Main.LootGenerator;
import com.gorgonshank.manyfragments.Sprite.Enemy;
import com.gorgonshank.manyfragments.Sprite.Sprite;

import java.util.Random;

public class EnemyData {
    private long hp;
    private long attack;
    private long defence;
    private long experience;
    private Sprite loot;
    private long maxHP;
    private Enemy enemySprite;

    public EnemyData() {
        long minimum = 50;
        long maximum = 100;

        Random r = new Random();

        this.hp = r.nextInt(1500) + 800;
        this.maxHP = this.hp;
        this.attack = minimum + (int)(Math.random() * ((maximum - minimum) + 1));
        this.defence = minimum + (int)(Math.random() * ((maximum - minimum) + 1));
        this.experience = this.hp;

        this.loot = LootGenerator.generateLoot();
    }

    public EnemyData(int modifier) {
        long minimum = 100 * modifier;
        long maximum = 150 * modifier;

        Random r = new Random();

        this.hp = (r.nextInt(1500) + 800) * modifier;
        this.maxHP = this.hp;
        this.attack = minimum + (int)(Math.random() * ((maximum - minimum) + 1));
        this.defence = minimum + (int)(Math.random() * ((maximum - minimum) + 1));
        this.experience = this.hp;

        this.loot = LootGenerator.generateLoot();
    }

    public long getHp() {
        return hp;
    }

    public void setHp(long hp) {
        this.hp = hp;
    }

    public long getAttack() {
        return attack;
    }

    public void setAttack(long attack) {
        this.attack = attack;
    }

    public long getDefence() {
        return defence;
    }

    public void setDefence(long defence) {
        this.defence = defence;
    }

    public long getExperience() {
        return experience;
    }

    public void setExperience(long experience) {
        this.experience = experience;
    }

    public Sprite getLoot() {
        return loot;
    }

    public void setLoot(Sprite loot) {
        this.loot = loot;
    }

    public long getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(long maxHP) {
        this.maxHP = maxHP;
    }
}
