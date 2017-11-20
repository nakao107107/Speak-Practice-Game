package com.example.nakao.speakpracticegame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

/**
 * Created by nakao on 2017/06/15.
 */

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    TextView RightAnswer,Comment;
    Intent intent;
    int Result;
    int ProgramNumber;
    double Percentage;
    Button goHomeButton,goGameButton;
    ImageView image;
    int level;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        intent=getIntent();
        Result=intent.getIntExtra("RightAnswerNumber",0);
        level=intent.getIntExtra("level",1);

        //Preferenceファイルから問題数を取得（設定なしの場合10問）
        SharedPreferences data = getSharedPreferences("Setting", Context.MODE_PRIVATE);
        ProgramNumber = data.getInt("ProgramNumber",10 );

        Percentage=Result/ProgramNumber;

        RightAnswer=(TextView)findViewById(R.id.result);
        RightAnswer.setText(Result+"/"+ProgramNumber);

        Comment=(TextView)findViewById(R.id.comment);

        goHomeButton=(Button)findViewById(R.id.goHomeButton);
        goGameButton=(Button)findViewById(R.id.goGameButton);

        goGameButton.setOnClickListener(this);
        goHomeButton.setOnClickListener(this);

        image=(ImageView)findViewById(R.id.judge);

        Log.d("level",String.valueOf(level));




        if(Percentage==1.0){
            Comment.setText("やったね！パーフェクト！");
            GlideDrawableImageViewTarget target = new GlideDrawableImageViewTarget(image);
            Glide.with(this).load(R.drawable.happy).into(target);


        }else if(Result>0.6){
            Comment.setText("いいかんじ！つぎはまんてんをめざそう！");
            GlideDrawableImageViewTarget target = new GlideDrawableImageViewTarget(image);
            Glide.with(this).load(R.drawable.ordinary).into(target);
        }else{
            Comment.setText("ざんねん…つぎはもっとがんばろう…");
            GlideDrawableImageViewTarget target = new GlideDrawableImageViewTarget(image);
            Glide.with(this).load(R.drawable.sad).into(target);
        }



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goHomeButton:
                Intent intent1=new Intent(v.getContext(),MainActivity.class);
                startActivity(intent1);
                break;
            case R.id.goGameButton:
                Intent intent2=new Intent(v.getContext(),StageSelectActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
