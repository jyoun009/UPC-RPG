package com.gorgonshank.manyfragments.Fragments;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.gorgonshank.manyfragments.Data.CharacterData;
import com.gorgonshank.manyfragments.Main.SpriteGenerator;
import com.gorgonshank.manyfragments.R;
import com.gorgonshank.manyfragments.Sprite.Armor;
import com.gorgonshank.manyfragments.Sprite.Sprite;
import com.gorgonshank.manyfragments.Sprite.Weapon;

import java.util.ArrayList;

public class CharacterEquipmentFragment extends Fragment {
    private static final String TAG = "SecondFragment";
    //ImageButtons for every item
    ImageButton ib,ib2,ib3,ib4;
    ArrayList<ImageButton> buttons = new ArrayList<ImageButton>();

    static int switchWepSlot = 0;
    static View statView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_equipment, container, false);
        statView = v;

        return v;
    }

    public class MyOnclickListener implements View.OnClickListener {
        Sprite loot;
        ImageButton button;

        public MyOnclickListener(Sprite loot, ImageButton button){
            this.loot = loot;
            this.button = button;
        }
        @Override
        public void onClick(View v) {
            String ch = "You equipped " + loot.getPrefix() + " "
                    + loot.getName() + " "
                    + loot.getSuffix();
            if (loot.getName().contains("Gloves")) {
                CharacterData.setGloves((Armor) loot);
                Toast toast = Toast.makeText(v.getContext(), ch, Toast.LENGTH_SHORT);
                toast.show();
            } else if (loot.getName().contains("Chestplate")) {
                CharacterData.setArmor((Armor) loot);
                Toast toast = Toast.makeText(v.getContext(), ch, Toast.LENGTH_SHORT);
                toast.show();
            } else if (loot.getName().contains("Shoes")) {
                CharacterData.setShoes((Armor) loot);
                Toast toast = Toast.makeText(v.getContext(), ch, Toast.LENGTH_SHORT);
                toast.show();
            } else if (!loot.getClass().getName().equals("com.gorgonshank.manyfragments.Sprite.Item")) {
                CharacterData.setWeapon1((Weapon) loot);
                Toast toast = Toast.makeText(v.getContext(), ch + " into weapon slot 1", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        // Make sure that we are currently visible
        if (this.isVisible()) {
            // Method to change things when coming into focus
            onShowFragment();
            if (!isVisibleToUser) {
                // Method to change things when leaving focus
                onHideFragment();
            }
        }
    }

    public void onShowFragment() {
        Log.i("Stuff", "Calling On show fragment");
        View v = statView;
        ib=(ImageButton)getActivity().findViewById(R.id.ib);
        ib2=(ImageButton)v.findViewById(R.id.ib2);
        ib3=(ImageButton)v.findViewById(R.id.ib3);
        ib4=(ImageButton)v.findViewById(R.id.ib4);
        GridLayout gl = (GridLayout) v.findViewById(R.id.gridlayout);
        gl.removeAllViewsInLayout();
        gl.setColumnCount(2);
        gl.setPadding(5, 5, 5, 5);
        for(Sprite loot : CharacterData.getLoot()){
            ImageButton icon = new ImageButton(v.getContext());
            icon.setImageDrawable(loot.getDrawable());
            icon.setClickable(true);
            gl.addView(icon);
            MyOnclickListener listener = new MyOnclickListener(loot, icon);
            icon.setOnClickListener(listener);

            TextView text = new TextView(v.getContext());
            //stuff.getClass().getName().equals("com.gorgonshank.manyfragments.Sprite.Enemy"
            if (loot.getClass().getName().equals("com.gorgonshank.manyfragments.Sprite.Armor")) {
                Armor armorLoot = (Armor) loot;
                text.setText(armorLoot.getPrefix() + " " + armorLoot.getName() + " " + armorLoot.getSuffix()
                        + "\n" + "Defense Boost: " + armorLoot.getDefense()
                        + "\n" + "HP Boost: " + armorLoot.getHp());
            } else if (loot.getClass().getName().equals("com.gorgonshank.manyfragments.Sprite.Weapon")) {
                Weapon wepLoot = (Weapon) loot;
                text.setText(wepLoot.getPrefix() + " " + wepLoot.getName() + " " + wepLoot.getSuffix()
                        + "\n" + "Attack Boost: " + wepLoot.getAttack());
            } else if (loot.getClass().getName().equals("com.gorgonshank.manyfragments.Sprite.Item")) {
                text.setText(loot.getPrefix() + " " + loot.getName() + " " + loot.getSuffix());
            }

            WindowManager wm = (WindowManager) v.getContext().getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;

            text.setWidth(width);
            gl.addView(text);
        }
    }

    public void onHideFragment() {

    }

    public static CharacterEquipmentFragment newInstance(String text) {

        CharacterEquipmentFragment f = new CharacterEquipmentFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}