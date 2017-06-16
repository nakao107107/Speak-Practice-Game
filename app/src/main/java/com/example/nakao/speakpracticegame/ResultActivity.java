package com.example.nakao.speakpracticegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by nakao on 2017/06/15.
 */

public class ResultActivity extends AppCompatActivity {

    TextView RightAnswer,Comment;
    Intent intent;
    int Result;
    Button goHomeButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        intent=getIntent();
        Result=intent.getIntExtra("RightAnswerNumber",0);

        RightAnswer=(TextView)findViewById(R.id.result);
        RightAnswer.setText(Result+"/10");

        Comment=(TextView)findViewById(R.id.comment);

        goHomeButton=(Button)findViewById(R.id.goHomeButton);



        if(Result==10){
            Comment.setText("やったね！パーフェクト！");
            ((ImageView) findViewById(R.id.judge)).setImageResource(R.drawable.bird4);
        }else if(Result>6){
            Comment.setText("いいかんじ！つぎはまんてんをめざそう！");
            ((ImageView) findViewById(R.id.judge)).setImageResource(R.drawable.bird1);
        }else{
            Comment.setText("ざんねん…つぎはもっとがんばろう…");
            ((ImageView) findViewById(R.id.judge)).setImageResource(R.drawable.bird3);
        }

        goHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
