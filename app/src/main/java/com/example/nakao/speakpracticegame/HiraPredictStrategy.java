package com.example.nakao.speakpracticegame;

import java.util.ArrayList;

/**
 * Created by ryosuke on 17/11/22.
 */

public class HiraPredictStrategy {

    public String predict(ArrayList<String> arrayList) {

        HiraganaKatakanaMatch hiraganaMatch=new HiraganaKatakanaMatch();
        String answer="null";

        for(String candidate:arrayList){
            if(hiraganaMatch.judgeHiragana(candidate)==true){

                answer=candidate;
                break;
            }

        }


        return answer;
    }
}
