package com.example.nakao.speakpracticegame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by nakao on 2017/06/18.
 */

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    Button GoHomebutton;
    Button Plus,Minus;
    TextView Number;

    int ProgramNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        GoHomebutton=(Button) findViewById(R.id.goHomeButton);
        Plus=(Button)findViewById(R.id.plus);
        Minus=(Button)findViewById(R.id.minus);

        GoHomebutton.setOnClickListener(this);
        Plus.setOnClickListener(this);
        Minus.setOnClickListener(this);

        SharedPreferences data = getSharedPreferences("Setting", Context.MODE_PRIVATE);
        ProgramNumber = data.getInt("ProgramNumber",10 );

        Number=(TextView)findViewById(R.id.number);
        Number.setText(String.valueOf(ProgramNumber));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.goHomeButton:
                //Preferenceファイルに設定を保存
                SharedPreferences data = getSharedPreferences("Setting", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = data.edit();
                editor.putInt("ProgramNumber", ProgramNumber);
                editor.apply();

                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);

                break;
            case R.id.plus:
                if(ProgramNumber<100){

                    //問題数を更新
                    ProgramNumber++;
                    Number.setText(String.valueOf(ProgramNumber));
                }
                break;
            case R.id.minus:
                if(ProgramNumber>1){

                    //問題数を更新
                    ProgramNumber--;
                    Number.setText(String.valueOf(ProgramNumber));
                }
                break;
        }
    }
}
