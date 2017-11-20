package com.example.nakao.speakpracticegame;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

/**
 * Created by nakao on 2017/06/18.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button GameStartButton;
    Button SettingButton;
    ImageView image;
    LinearLayout linearlayout;
    int currentlevel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image=(ImageView)findViewById(R.id.birdgif);
        GlideDrawableImageViewTarget target = new GlideDrawableImageViewTarget(image);
        Glide.with(this).load(R.drawable.ordinary).into(target);

        GameStartButton = (Button)findViewById(R.id.button1);
        SettingButton = (Button) findViewById(R.id.button2);
        linearlayout=(LinearLayout)findViewById(R.id.background);

        GameStartButton.setOnClickListener(this);
        SettingButton.setOnClickListener(this);

        //Preferenceファイルから現在の最高クリアレベルを取得
        SharedPreferences process = getSharedPreferences("level", Context.MODE_PRIVATE);
        currentlevel = process.getInt("currentlevel",0 );

        Log.d("currentlevel",String.valueOf(currentlevel));

        switch (currentlevel){
            case 0:
                linearlayout.setBackgroundResource(R.drawable.background0);
                break;
            case 1:
                linearlayout.setBackgroundResource(R.drawable.background1);
                break;
            case 2:
                linearlayout.setBackgroundResource(R.drawable.background2);
                break;
            case 3:
                linearlayout.setBackgroundResource(R.drawable.background3);
                break;
            case 4:
                linearlayout.setBackgroundResource(R.drawable.background4);
                break;
            default:
                linearlayout.setBackgroundResource(R.drawable.background1);
                break;
        }

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                Intent intent=new Intent(v.getContext(),StageSelectActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent2=new Intent(v.getContext(),SettingActivity.class);
                startActivity(intent2);
                break;
        }
    }




}


