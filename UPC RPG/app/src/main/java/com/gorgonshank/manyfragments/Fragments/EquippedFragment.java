package com.gorgonshank.manyfragments.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.gorgonshank.manyfragments.Data.CharacterData;
import com.gorgonshank.manyfragments.Main.MainActivity;
import com.gorgonshank.manyfragments.R;

public class EquippedFragment extends Fragment {
    private static final String TAG2 = "EquippedFragment";
    ImageButton ib,ib2,ib3,ib4;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.equipped, container, false);
        ib=(ImageButton)v.findViewById(R.id.ib);
        ib2=(ImageButton)v.findViewById(R.id.ib2);
        ib3=(ImageButton)v.findViewById(R.id.ib3);
        ib4=(ImageButton)v.findViewById(R.id.ib4);

        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ib.setImageResource(R.drawable.armor2);
            }
        });

        return v;
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


        //ib.setImageResource();
        //characterHP = (TextView) getView().findViewById(R.id.characterHP);
        //String text = "myHitPoints: " + CharacterData.getMyHitPoints() + "/" + CharacterData.getMyMaxHitPoints();
        //characterHP.setText(text);
    }

    public void onHideFragment() {

    }

    public static EquippedFragment newInstance(String text) {

        EquippedFragment f = new EquippedFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}