package com.example.nakao.speakpracticegame;

import android.os.AsyncTask;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.ArrayList;

/**
 * Created by ryosuke on 17/11/22.
 */

public class KuroPredictStrategy implements PredictStrategy {

    @Override
    public String predict(ArrayList<String> arrayList) {
        TokenizerUtil tokenizerUtil = new TokenizerUtil();
        //kuromojiにかけてカタカナ変換
        String tmp_string =tokenizerUtil.getKatakana(arrayList.get(0));

        Log.d("進捗","kuromojiの処理が完了しました"+tmp_string);
        return tmp_string;
    }
}
