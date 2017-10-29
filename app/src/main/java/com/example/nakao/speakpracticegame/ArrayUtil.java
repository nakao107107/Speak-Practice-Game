package com.example.nakao.speakpracticegame;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by nakao on 2017/10/22.
 */

public class ArrayUtil implements ScoreAPIs {

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


    /**
     * 渡された言葉に対し、正しければ判定配列の該当ぶを+1,間違っていれば-1して返す。
     * @param right_word 正解の言葉
     * @param sample_word　判定する言葉
     * @param eval_array　判定配列
     * @return　判定配列
     */
    public static int[] correctWord(String right_word, String sample_word,@Nullable int[] eval_array) {
        int[] jarray;
        if(eval_array == null){
            jarray = new int[50];
        } else {
            jarray =  eval_array;
        }
        Arrays.fill(jarray,0);
        int p = 0;

        char[] r_chars = right_word.toCharArray();
        char[] s_chars = sample_word.toCharArray();

        int i=0;
        while (i<r_chars.length && i<s_chars.length){
            p= GOJUON.indexOf(r_chars[i]) ;
            Log.d("TAG",String.valueOf(p));
            if(p<0 || p>50) {
                i++;
                continue;
            }

            if(r_chars[i] == s_chars[i]){
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

    @Override
    public void save(int[] eval_array, String key) {

    }
}
