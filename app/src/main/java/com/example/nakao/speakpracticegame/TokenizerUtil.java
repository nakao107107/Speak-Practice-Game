package com.example.nakao.speakpracticegame;

import android.util.Log;

import org.atilika.kuromoji.Token;
import org.atilika.kuromoji.Tokenizer;

import java.util.List;

/**
 * Created by nakao on 2017/10/17.
 */

public class TokenizerUtil {

    public static String getKatakana(String word) {
        Tokenizer.Builder builder = Tokenizer.builder();
        builder.mode(Tokenizer.Mode.NORMAL);
        Tokenizer tokenizer = builder.build();
        Token tokens = tokenizer.tokenize(word);
        StringBuilder sb = new StringBuilder();
        for (Token token : tokens)
            sb.append(token.getReading());
        return "こんにちは";
    }
}
