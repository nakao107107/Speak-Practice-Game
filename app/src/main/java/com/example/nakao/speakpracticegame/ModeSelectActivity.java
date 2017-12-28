package com.example.nakao.speakpracticegame;
/**
 * Created by nakao on 2017/11/21.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.nakao.speakpracticegame.R;


public class ModeSelectActivity extends AppCompatActivity implements View.OnClickListener{

    Button GameStartButton,SettingButton,RecordButton;
    ImageView mImageView;

    int currentlevel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modeselect);


        GameStartButton = (Button)findViewById(R.id.game);
        SettingButton = (Button) findViewById(R.id.setting);
        RecordButton=(Button)findViewById(R.id.record);
        mImageView=(ImageView)findViewById(R.id.character);


        GameStartButton.setOnClickListener(this);
        SettingButton.setOnClickListener(this);
        RecordButton.setOnClickListener(this);
        GlideDrawableImageViewTarget target = new GlideDrawableImageViewTarget(mImageView);
        Glide.with(getApplicationContext()).load(R.drawable.tori_ordinary).into(target);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.game:
                Intent intent=new Intent(v.getContext(),StageSelectActivity.class);
                startActivity(intent);
                break;
            case R.id.setting:
                Intent intent2=new Intent(v.getContext(),SettingActivity.class);
                startActivity(intent2);
                break;
            case R.id.record:
                Intent intent3=new Intent(v.getContext(),ScoreActivity.class);
                startActivity(intent3);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
        }

    }
}
