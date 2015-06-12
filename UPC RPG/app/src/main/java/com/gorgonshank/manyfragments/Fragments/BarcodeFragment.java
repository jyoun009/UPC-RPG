package com.gorgonshank.manyfragments.Fragments;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gorgonshank.manyfragments.Data.CharacterData;
import com.gorgonshank.manyfragments.Main.BarcodeActivity;
import com.gorgonshank.manyfragments.Main.BattleActivity;
import com.gorgonshank.manyfragments.Main.SpriteGenerator;
import com.gorgonshank.manyfragments.R;
import com.gorgonshank.manyfragments.Sprite.*;
import com.gorgonshank.manyfragments.Sprite.Character;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class BarcodeFragment extends Fragment {

    ImageView canvas;
    Button scanButton;
    TextView item_textview;
    Sprite loot;
    String end;
    MediaPlayer mp;
    ArrayList<Drawable> drawables = new ArrayList<Drawable>();
    int drawableWidth = 200;
    int drawableHeight = 200;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.barcode_fragment, container, false);

        canvas = (ImageView)v.findViewById(R.id.canvas);

        scanButton = (Button)v.findViewById(R.id.scanButton);
        scanButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BarcodeActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });

        item_textview = (TextView)v.findViewById(R.id.item);

        Intent bcActivity = getActivity().getIntent();
        String barcode = bcActivity.getStringExtra("barcode");
        if(barcode != null){
            Log.i("Info", "Barcode is " + barcode);
            BigInteger bigBarcode = new BigInteger(barcode);
            Sprite stuff = SpriteGenerator.generateSprite(bigBarcode.intValue());
            Log.i("Sprite", stuff.getClass().toString());
            canvas.setImageDrawable(stuff.getDrawable());
            item_textview.setText(stuff.toString());

            // This is the condition where we determine which method to send it to
            Log.i("Info", stuff.getClass().getName() + "");

            if(stuff.getClass().getName().equals("com.gorgonshank.manyfragments.Sprite.Enemy")) {
                Intent intent = new Intent(getActivity(), BattleActivity.class);
                Log.i("Info", "Yay it worked and we got here!");
                intent.putExtra("Sprite", stuff);
                startActivity(intent);
                getActivity().finish();
            } else {
                canvas.setImageDrawable(stuff.getDrawable());
                item_textview.setText(stuff.toString());
                CharacterData.addLoot(stuff);
                Log.i("Stuff", "HERE GOT");
            }
        }
        else{
            Log.i("Info", "Barcode is null");
        }

        loot = (Sprite)bcActivity.getSerializableExtra("loot");

        if(loot != null) {
            canvas.setImageDrawable(loot.getDrawableFromName());
            item_textview.setText(loot.toString());
            CharacterData.addLoot(loot);
        }


        return v;
    }



    public static BarcodeFragment newInstance(String text) {

        BarcodeFragment f = new BarcodeFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}
