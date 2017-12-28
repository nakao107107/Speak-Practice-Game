package com.example.nakao.speakpracticegame;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by nakao on 2017/06/18.
 */

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    Button homebtn,rstbtn;
    EditText pnedit;
    CheckBox chkbox;

    SharedPreferences sp1,sp2;
    SharedPreferences.Editor editor1,editor2;

    Intent intent;

    int num;
    int cnum;
    boolean judge;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        homebtn=(Button) findViewById(R.id.home);
        rstbtn=(Button)findViewById(R.id.reset);
        pnedit=(EditText)findViewById(R.id.program_number);
        chkbox=(CheckBox)findViewById(R.id.judge);

        homebtn.setOnClickListener(this);
        rstbtn.setOnClickListener(this);

        sp1 = getSharedPreferences("Setting", Context.MODE_PRIVATE);
        sp2 = getSharedPreferences("Level", Context.MODE_PRIVATE);
        editor1 = sp1.edit();
        editor2 = sp2.edit();

        //EditTextがからであった場合エラーが起こるので、それをなくす
        cnum=sp1.getInt("mProgramNumber",5);
        pnedit.setText(String.valueOf(cnum));
        judge=sp1.getBoolean("judge",false);
        chkbox.setChecked(judge);

        intent = new Intent(this, MainActivity.class);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.home:

                //EditTextが空だった場合の対策

                if(pnedit.length()!=0){
                    num=Integer.parseInt(pnedit.getText().toString());
                }else{
                    num=cnum;
                }
                judge=chkbox.isChecked();

                //Preferenceファイルに設定を保存

                editor1.putInt("mProgramNumber", num);
                editor1.putBoolean("judge", judge);
                editor1.apply();

                startActivity(intent);

                break;

            case R.id.reset:
                // アラートダイアログ
                DialogFragment newFragment = new CustomDialogFragment();
                newFragment.show(getFragmentManager(), "test");
                break;


        }

    }

    @SuppressLint("ValidFragment")
    public class CustomDialogFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            Dialog dialog = new Dialog(getActivity());
            // タイトル非表示
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            // フルスクリーン
            dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
            dialog.setContentView(R.layout.game_dialog_custom);
            // 背景を透明にする
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            ((TextView)dialog.findViewById(R.id.message)).setText("ゲームの記録を削除します\n本当によろしいですか？");

            // OK ボタンのリスナ
            dialog.findViewById(R.id.positive_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(intent);

                    //全Preferenceファイルを削除
                    editor1.clear();
                    editor2.clear();
                    editor1.commit();
                    editor2.commit();

                    startActivity(intent);

                }
            });

            return dialog;
        }
    }
}
