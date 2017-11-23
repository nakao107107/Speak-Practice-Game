package com.example.nakao.speakpracticegame;

import android.os.AsyncTask;
import android.util.Log;

import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.ArrayList;

/**
 * Created by ryosuke on 17/11/22.
 */

public class KuroPredictStrategy {

    public String predict(ArrayList<String> arrayList) {

        //kuromojiにかけてカタカナ変換
        String tmp_string = getKatakana(arrayList.get(0));

        Log.d("進捗","kuromojiの処理が完了しました"+tmp_string);
        return tmp_string;
    }

    public String getKatakana(String word) {

        Tokenizer mTokenizer;
        mTokenizer= new Tokenizer.Builder().build();
        StringBuilder sb=new StringBuilder();


        for (Token token : mTokenizer.tokenize(word)) {

            String feature=token.getReading();
            sb.append(feature);
        }
        return sb.toString();
    }
}






