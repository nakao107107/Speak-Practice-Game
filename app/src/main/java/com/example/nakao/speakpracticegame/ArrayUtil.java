package com.example.nakao.speakpracticegame;

import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by nakao on 2017/10/22.
 */

public class ArrayUtil {

    private static String GOJUON = "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわをん";


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

    public static int[] correctWord(String right_word, String sample_word,@Nullable int[] eval_array) {
        if(eval_array == null){
            int[] jarray = new int[50];
        }
        int[] jarray = eval_array;
        Arrays.fill(jarray,0);
        int p = 0;

        char[] rascii = right_word.toCharArray();
        char[] sascii = sample_word.toCharArray();

        int i=0;
        while (i<rascii.length && i<sascii.length){
            p= GOJUON.indexOf(rascii[i]) ;
            Log.d("TAG",String.valueOf(p));
            if(p<0 || p>50) {
                i++;
                continue;
            }

            if(rascii[i] == sascii[i]){
                jarray[p] += 1;
            } else {
                jarray[p] -= 1;
            }

            i++;
        }
        return  jarray;
    }

    public static int[] correctWord(String right_word, String sample_word){
        return correctWord(right_word, sample_word, null);
    }
}
