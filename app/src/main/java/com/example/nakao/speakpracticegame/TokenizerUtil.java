package com.example.nakao.speakpracticegame;

import android.util.Log;

import org.atilika.kuromoji.Token;
import org.atilika.kuromoji.Tokenizer;


/**
 * Created by nakao on 2017/10/17.
 */

public class TokenizerUtil {

    public static String getKatakana(String word) {

        Tokenizer mTokenizer;
        mTokenizer= new Tokenizer.Builder().build();
        StringBuilder sb=new StringBuilder();


        for (Token token : mTokenizer.tokenize("私の名前はなかおです")) {

            String feature=token.getAllFeatures();
            Log.d("getAllFeatureArrayの長さ", feature);
            
            sb.append(feature);
        }
        return sb.toString();
    }
}
