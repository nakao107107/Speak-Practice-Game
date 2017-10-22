package com.example.nakao.speakpracticegame;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by nakao on 2017/10/22.
 */

public class ArrayUtil {

    boolean judgeArray(String word, ArrayList<String> array){

        boolean judge=false;
        Log.d("正解のワード",word);

        for(String index:array){
            if(index.equals(word)){
                judge=true;
                Log.d("判定結果","正解のワードを発見しました");
            }
        }

        Log.d("判定結果",String.valueOf(judge));

        return judge;

    }
}
