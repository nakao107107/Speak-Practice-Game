package com.example.nakao.speakpracticegame;

import android.util.Log;
import java.util.ArrayList;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import java.util.Arrays;


/**
 * Created by nakao on 2017/10/22.
 */

public class ArrayUtil {

    private static String GOJUON = "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわをん";
    private static final String SHARED_KEY = "DATABASE";
    public static final String EVALUE_KEY_WORD = "value"; //評価配列ようのキーセット.

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
     * @param eval_array　評価配列
     * @return 評価配列
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

    /**
     * 評価配列（長さ５０）を受け取り、Stringに型変換してからsharedpreferenceに保存
     * @param key_word 配列のキーセット。
     * @param evalue_array　評価配列（長さ５０）
     * @param context　
     */
    public static void saveArray(String key_word,int[] evalue_array,Context context){

        //Preferenceファイルを取得
        int[] predata = getArray(key_word,context);
        for(int i=0; i< predata.length; i++){
            evalue_array[i] += predata[i];
        }

        StringBuilder builder = new StringBuilder();
        for(int ev:evalue_array){
            builder.append(String.valueOf(ev)).append(",");
        }
        Log.d("saveArray:",builder.toString());

        //Preferenceファイルに設定を保存
        SharedPreferences data = context.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        editor.putString(key_word, builder.toString());
        editor.apply();
    }

    /**
     * sharedpreferenceに保存されているstringを数字ごとに分けて配列に格納し、返す。
     * @param key_word
     * @param context
     * @return
     */
    public static int[] getArray(String key_word,Context context) {
        String default_string = "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,";  //key_wordに対するオブジェクトがない場合に返すもの
        SharedPreferences data = context.getSharedPreferences(SHARED_KEY, Context.MODE_PRIVATE);
        String[] predata = data.getString(key_word,default_string).split(",",0); //","ごとに文字を分ける。引数の0は最後の空白を含まないことを意味する。
        int[] i_array = new int[50];
        for(int i=0; i<i_array.length; i++){
            i_array[i] = Integer.parseInt(predata[i]);
        }
        return i_array;
    }

}
