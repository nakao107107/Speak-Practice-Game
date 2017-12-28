package com.example.nakao.speakpracticegame;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by nakao on 2017/10/15.
 */

public class StageSelectActivity extends AppCompatActivity implements View.OnClickListener{


    Intent intent,intent2;
    Button button1,button2,button3,button4,button5;
    int level;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stageselect);

        intent=new Intent(this,GameActivity.class);
        intent2=new Intent(this,ModeSelectActivity.class);

        button1=(Button)findViewById(R.id.forest);
        button2=(Button)findViewById(R.id.desert);
        button3=(Button)findViewById(R.id.mountain);
        button4=(Button)findViewById(R.id.island);
        button5=(Button)findViewById(R.id.house);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.forest:
                level=1;
                makeDialog(level);
                break;
            case R.id.desert:
                level=2;
                makeDialog(level);
                break;
            case R.id.mountain:
                level=3;
                makeDialog(level);
                break;
            case R.id.island:
                level=4;
                makeDialog(level);
                break;
            case R.id.house:
                level=5;
                makeDialog(level);
                break;
        }
    }

    void goNextActivity(int level){

        if (level == 5) {
            startActivity(intent2);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }else{
            intent.putExtra("LEVEL" ,level);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    }


    void makeDialog(int level){

        DialogFragment newFragment = new CustomDialogFragment(level);
        newFragment.show(getFragmentManager(), "test");

    }


    @SuppressLint("ValidFragment")
    public class CustomDialogFragment extends DialogFragment {

        String name;
        int image;


        CustomDialogFragment(int stage){

            switch (stage){
                case 1:
                    name="ざわざわもり";
                    image=R.drawable.forest_icon;
                    break;
                case 2:
                    name="さらさらさばく";
                    image=R.drawable.desert_icon;
                    break;
                case 3:
                    name="とんがりやま";
                    image=R.drawable.mountain_icon;
                    break;
                case 4:
                    name="ぽかぽかじま";
                    image=R.drawable.island_icon;
                    break;
                case 5:
                    name="おうち";
                    image=R.drawable.house_icon;
                    break;
            }


        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            Dialog dialog = new Dialog(getActivity());
            // タイトル非表示
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            // フルスクリーン
            dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
            dialog.setContentView(R.layout.dialog_custom);
            // 背景を透明にする
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            ((TextView)dialog.findViewById(R.id.message)).setText(name);
            ((ImageView)dialog.findViewById(R.id.stage)).setImageResource(image);

            // OK ボタンのリスナ
            dialog.findViewById(R.id.positive_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    goNextActivity(level);


                }
            });

            return dialog;
        }
    }
}




