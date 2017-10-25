package com.example.nakao.speakpracticegame;

import java.util.ArrayList;

/**
 * Created by nakao on 2017/10/20.
 */

public class HiraganaKatakanaMatch {

    boolean judgeHiragana(String word){

        if(word.matches("^[\\u3040-\\u309F]+$")){
            return true;
        }else{
            return false;
        }

    }

    boolean judgeKatakana(String word){

        if(word.matches("^[\\u30A0-\\u30FF]+$")){

            return true;

        }else{

            return false;

        }

    }

    ArrayList makeHiraganaArray(ArrayList<String> results_array){

        boolean judge;

        ArrayList<String> answer_array=new ArrayList<String>();

        for(String Answer:results_array){

            judge=judgeHiragana(Answer);

            if (judge) {
                answer_array.add(Answer);
            }

        }

        return answer_array;

    }

    public static String zenkakuHiraganaToZenkakuKatakana(String s) {
        StringBuffer sb = new StringBuffer(s);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c >= 'ァ' && c <= 'ン') {
                sb.setCharAt(i, (char)(c - 'ァ' + 'ぁ'));
            } else if (c == 'ヵ') {
                sb.setCharAt(i, 'か');
            } else if (c == 'ヶ') {
                sb.setCharAt(i, 'け');
            } else if (c == 'ヴ') {
                sb.setCharAt(i, 'う');
                sb.insert(i + 1, '゛');
                i++;
            }
        }
        return sb.toString();
    }
}


