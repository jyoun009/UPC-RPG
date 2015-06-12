package com.gorgonshank.manyfragments.Main;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.gorgonshank.manyfragments.Data.CharacterData;
import com.gorgonshank.manyfragments.Fragments.BarcodeFragment;
import com.gorgonshank.manyfragments.Fragments.CharacterEquipmentFragment;
import com.gorgonshank.manyfragments.Fragments.CharacterFragment;
import com.gorgonshank.manyfragments.Fragments.EquippedFragment;
import com.gorgonshank.manyfragments.Fragments.ThirdFragment;
import com.gorgonshank.manyfragments.JSON.JSONReader;
import com.gorgonshank.manyfragments.JSON.JSONWriter;
import com.gorgonshank.manyfragments.R;
import com.gorgonshank.manyfragments.Sprite.Armor;
import com.gorgonshank.manyfragments.Sprite.Weapon;

import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends FragmentActivity {

    private MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!SpriteGenerator.hasLoaded){
            SpriteGenerator.initDrawables(this.getResources(), 200, 200);
            SpriteGenerator.hasLoaded = true;
        }

        initializeCharacterData();

        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setPageTransformer(true, new ZoomOutPageTransformer());
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
    }

    public void initializeCharacterData() {

        // Read persistent JSON Data
        JSONReader JSONReader = null;

        try{
            JSONReader = new JSONReader(getApplicationContext().getFilesDir());
        } catch (FileNotFoundException e){
            Log.i("Error", "IOException at startup.");
        }

        JSONObject JSON = JSONReader.getMyObject();

        // Set Static Class Data Here

        // ==================<ARMOR>==================
        JSONObject armorJSON = (JSONObject) JSON.get("armor");
        Armor armor = new Armor();
        armor.setPrefix((String)armorJSON.get("prefix"));
        armor.setSuffix((String)armorJSON.get("suffix"));
        armor.setName((String)armorJSON.get("name"));

        Long tempHp = (Long) armorJSON.get("hp");
        armor.setHp(tempHp.intValue());
        Long tempDef = (Long) armorJSON.get("defense");
        armor.setDefense(tempDef.intValue());

        Log.i("ARMOR", armor.toString());

        // ==================<GLOVES>==================
        JSONObject glovesJSON = (JSONObject) JSON.get("gloves");
        Armor gloves = new Armor();
        gloves.setPrefix((String)glovesJSON.get("prefix"));
        gloves.setSuffix((String)glovesJSON.get("suffix"));
        gloves.setName((String)glovesJSON.get("name"));

        tempHp = (Long) glovesJSON.get("hp");
        gloves.setHp(tempHp.intValue());
        tempDef = (Long) glovesJSON.get("defense");
        gloves.setDefense(tempDef.intValue());

        Log.i("ARMOR", gloves.toString());

        // ==================<SHOES>==================
        JSONObject shoesJSON = (JSONObject) JSON.get("shoes");
        Armor shoes = new Armor();
        shoes.setPrefix((String)shoesJSON.get("prefix"));
        shoes.setSuffix((String)shoesJSON.get("suffix"));
        shoes.setName((String)shoesJSON.get("name"));

        tempHp = (Long) shoesJSON.get("hp");
        shoes.setHp(tempHp.intValue());
        tempDef = (Long) shoesJSON.get("defense");
        shoes.setDefense(tempDef.intValue());

        Log.i("ARMOR", shoes.toString());

        // ==================<WEAPON 1>==================

        JSONObject weaponOneJSON = (JSONObject) JSON.get("weapon1");
        Long longAttack = (Long) weaponOneJSON.get("attack");
        Weapon weaponOne = new Weapon();
        weaponOne.setAttack(longAttack.intValue());
        weaponOne.setPrefix((String)weaponOneJSON.get("prefix"));
        weaponOne.setSuffix((String)weaponOneJSON.get("suffix"));
        weaponOne.setName((String)weaponOneJSON.get("name"));

        Log.i("ARMOR", weaponOne.toString());

        Long hp = (Long) JSON.get("hit_points");
        Long maxHp = (Long) JSON.get("max_hit_points");
        Long defense = (Long) JSON.get("defense");
        Long attack = (Long) JSON.get("attack");

        CharacterData.setArmor(armor);
        CharacterData.setGloves(gloves);
        CharacterData.setShoes(shoes);
        CharacterData.setWeapon1(weaponOne);

        CharacterData.setHit_points(hp);
        CharacterData.setMax_hit_points(maxHp);
        CharacterData.setDefense(defense);
        CharacterData.setAttack(attack);

        CharacterData.getArmor().setSprite(CharacterData.getArmor().getDrawableFromName());
        CharacterData.getShoes().setSprite(CharacterData.getShoes().getDrawableFromName());
        CharacterData.getGloves().setSprite(CharacterData.getGloves().getDrawableFromName());
        CharacterData.getWeapon1().setSprite(CharacterData.getWeapon1().getDrawableFromName());
    }

    public void playSound(String fileName) {

        //String fileName = "";
        AssetFileDescriptor afd = null;

        try{
            afd = getAssets().openFd(fileName);
        } catch(IOException e) {
            Log.i("Error", "Assets file I/O Error");
        }

        mp = new MediaPlayer();
        try{
            mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(),afd.getLength());
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            Log.i("Error", "Media player I/O Exception");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Persist the Data through our JSON Object
        CharacterData cd = new CharacterData();
        JSONWriter JSONWriter = null;

        try{
            JSONWriter = new JSONWriter(cd, getApplicationContext().getFilesDir());
        } catch(IOException e) {
            Log.i("Error", "IOException");
        }

        playSound("game_save.mp3");
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        // Set titles for tabs
        private String tabtitles[] = new String[] {"Character Screen", "Adventure!", "Loot Screen"};

        // If you add a new tab, must change this variable
        private static final int NUMBER_OF_TABS = 3;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        // Create each new fragment here
        @Override
        public Fragment getItem(int pos) {
            switch(pos) {
                case 0: return CharacterFragment.newInstance("Character Fragment1");
                case 1: return BarcodeFragment.newInstance("BarcodeFragment");
                case 2: return CharacterEquipmentFragment.newInstance("Character Equipment");



                default: return ThirdFragment.newInstance("ThirdFragment, Default");
            }
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            return NUMBER_OF_TABS;
        }

        public CharSequence getPageTitle(int position) {
            return tabtitles[position];
        }
    }




}