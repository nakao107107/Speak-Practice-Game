package com.example.nakao.speakpracticegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by nakao on 2017/10/15.
 */

public class StageSelectActivity extends AppCompatActivity implements View.OnClickListener{


    Intent intent,intent2;
    Button button1,button2,button3,button4,button5;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stageselect);

        intent=new Intent(this,GameActivity.class);
        intent2=new Intent(this,MainActivity.class);

        button1=(Button)findViewById(R.id.stage1);
        button2=(Button)findViewById(R.id.stage2);
        button3=(Button)findViewById(R.id.stage3);
        button4=(Button)findViewById(R.id.stage4);
        button5=(Button)findViewById(R.id.gohome);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.stage1:
                goNextActivity(intent,1);
                break;
            case R.id.stage2:
                goNextActivity(intent,2);
                break;
            case R.id.stage3:
                goNextActivity(intent,3);
                break;
            case R.id.stage4:
                goNextActivity(intent,4);
                break;
            case R.id.gohome:
                startActivity(intent2);
                break;
        }
    }

    void goNextActivity(Intent intent,int level){
        intent.putExtra("LEVEL" ,level);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}




