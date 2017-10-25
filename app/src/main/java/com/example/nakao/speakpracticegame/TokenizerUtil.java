package com.example.nakao.speakpracticegame;

import android.util.Log;

import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;


/**
 * Created by nakao on 2017/10/17.
 */

public class TokenizerUtil {

    public static String getKatakana(String word) {

        Tokenizer mTokenizer;
        mTokenizer= new Tokenizer.Builder().build();
        StringBuilder sb=new StringBuilder();


        for (Token token : mTokenizer.tokenize(word)) {

            String feature=token.getReading();
            Log.d("getAllFeatureArrayの長さ", feature);
            
            sb.append(feature);
        }
        return sb.toString();
    }
}
