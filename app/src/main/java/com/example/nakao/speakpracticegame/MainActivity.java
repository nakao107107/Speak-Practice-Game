package com.example.nakao.speakpracticegame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

/**
 * Created by nakao on 2017/06/18.
 */

public class MainActivity extends AppCompatActivity{

    Button mGameStartButton;
    Button mSettingButton;
    Button mScoreButton;
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

        linearlayout=(LinearLayout)findViewById(R.id.background);

        //Preferenceファイルから現在の最高クリアレベルを取得
        SharedPreferences process = getSharedPreferences("Level", Context.MODE_PRIVATE);
        currentlevel = process.getInt("currentlevel",0 );


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

        linearlayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Intent intent=new Intent(v.getContext(),ModeSelectActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                return false;
            }
        });


    }

}


