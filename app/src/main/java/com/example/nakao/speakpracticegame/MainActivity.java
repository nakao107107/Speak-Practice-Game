package com.example.nakao.speakpracticegame;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

/**
 * Created by nakao on 2017/06/18.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button GameStartButton;
    Button SettingButton;
    ImageView image;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image=(ImageView)findViewById(R.id.birdgif);
        GlideDrawableImageViewTarget target = new GlideDrawableImageViewTarget(image);
        Glide.with(this).load(R.drawable.ordinary).into(target);

        GameStartButton = (Button)findViewById(R.id.button1);
        SettingButton = (Button) findViewById(R.id.button2);

        GameStartButton.setOnClickListener(this);
        SettingButton.setOnClickListener(this);

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


