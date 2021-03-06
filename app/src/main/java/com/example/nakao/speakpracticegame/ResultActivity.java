package com.example.nakao.speakpracticegame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
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
    ImageView star1,star2,star3;
    Intent intent;
    int Result;
    int ProgramNumber;
    double Percentage;
    Button goHomeButton,goGameButton;
    ImageView image;
    int level;
    int currentlevel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        star1=(ImageView)findViewById(R.id.star1);
        star2=(ImageView)findViewById(R.id.star2);
        star3=(ImageView)findViewById(R.id.star3);

        intent=getIntent();
        Result=intent.getIntExtra("RightAnswerNumber",0);
        level=intent.getIntExtra("level",1);

        //Preferenceファイルから問題数を取得（設定なしの場合10問）
        SharedPreferences data = getSharedPreferences("Setting", Context.MODE_PRIVATE);
        ProgramNumber = data.getInt("mProgramNumber",5 );

        Percentage=Result/ProgramNumber;

        Comment=(TextView)findViewById(R.id.comment);

        goHomeButton=(Button)findViewById(R.id.goHomeButton);
        goGameButton=(Button)findViewById(R.id.goGameButton);

        goGameButton.setOnClickListener(this);
        goHomeButton.setOnClickListener(this);

        image=(ImageView)findViewById(R.id.judge);


        if(Percentage==1.0){
            Comment.setText("やったね！パーフェクト！");
            GlideDrawableImageViewTarget target = new GlideDrawableImageViewTarget(image);
            Glide.with(this).load(R.drawable.tori_happy).into(target);

            //Preferenceファイルから現在の最高クリアレベルを取得（設定なしの場合10問）
            SharedPreferences process = getSharedPreferences("Level", Context.MODE_PRIVATE);
            currentlevel = process.getInt("currentlevel",0 );

            //現在で最高のlevelをクリアした場合、Preferenceファイルに設定を保存

            if(level>currentlevel){

                SharedPreferences.Editor editor = process.edit();
                editor.putInt("currentlevel",level);
                editor.apply();

                Log.d("level",String.valueOf(level));


            }


        }else if(Result>0.6){
            Comment.setText("いいかんじ！つぎはまんてんをめざそう！");
            GlideDrawableImageViewTarget target = new GlideDrawableImageViewTarget(image);
            Glide.with(this).load(R.drawable.tori_ordinary).into(target);
            star3.setImageResource(R.drawable.star_shadow);
        }else{

            Comment.setText("ざんねん…つぎはもっとがんばろう…");
            GlideDrawableImageViewTarget target = new GlideDrawableImageViewTarget(image);
            Glide.with(this).load(R.drawable.tori_sad).into(target);
            star3.setImageResource(R.drawable.star_shadow);
            star2.setImageResource(R.drawable.star_shadow);

        }



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goHomeButton:
                Intent intent1=new Intent(v.getContext(),ModeSelectActivity.class);
                startActivity(intent1);
                break;
            case R.id.goGameButton:
                Intent intent2=new Intent(v.getContext(),GameActivity.class);
                intent2.putExtra("LEVEL",level);
                startActivity(intent2);
                break;
        }
    }
}
