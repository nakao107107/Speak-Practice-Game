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
import android.widget.LinearLayout;

import com.example.nakao.speakpracticegame.R;


public class ModeSelectActivity extends AppCompatActivity implements View.OnClickListener{

    Button GameStartButton,SettingButton,RecordButton;
    LinearLayout linearlayout;
    SharedPreferences process;

    int currentlevel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modeselect);



        GameStartButton = (Button)findViewById(R.id.button1);
        SettingButton = (Button) findViewById(R.id.button2);
        RecordButton=(Button)findViewById(R.id.button3);
        linearlayout=(LinearLayout)findViewById(R.id.background);

        GameStartButton.setOnClickListener(this);
        SettingButton.setOnClickListener(this);
        RecordButton.setOnClickListener(this);

        //Preferenceファイルから現在の最高クリアレベルを取得
        process = getSharedPreferences("level", Context.MODE_PRIVATE);
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
            case R.id.button3:
                Intent intent3=new Intent(v.getContext(),ScoreActivity.class);
                startActivity(intent3);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
        }

    }
}
