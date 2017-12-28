package com.example.nakao.speakpracticegame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.widget.Toast;

import com.example.nakao.speakpracticegame.GameActivity;

/**
 * Created by nakao on 2017/12/18.
 */

public class SpeechRecognision {

    private SpeechRecognizer sr;


    protected void startListening(Context context) {
        try {
            if (sr == null) {
                sr = SpeechRecognizer.createSpeechRecognizer(context);
                if (!SpeechRecognizer.isRecognitionAvailable(context)) {
                    Toast.makeText(context, "音声認識が使えません",
                            Toast.LENGTH_LONG).show();
                }
                sr.setRecognitionListener(new Listener());
            }
            // インテントの作成
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            // 言語モデル指定
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
            sr.startListening(intent);
        } catch (Exception ex) {
            Toast.makeText(context, "startListening()でエラーが起こりました",
                    Toast.LENGTH_LONG).show();
        }

    }

    // 音声認識を終了する
    protected void stopListening() {
        if (sr != null) sr.destroy();
        sr = null;

    }

    class Listener implements RecognitionListener{

        @Override
        public void onReadyForSpeech(Bundle params) {

        }

        @Override
        public void onBeginningOfSpeech() {

        }

        @Override
        public void onRmsChanged(float rmsdB) {

        }

        @Override
        public void onBufferReceived(byte[] buffer) {

        }

        @Override
        public void onEndOfSpeech() {

        }

        @Override
        public void onError(int error) {



        }

        @Override
        public void onResults(Bundle results) {

        }

        @Override
        public void onPartialResults(Bundle partialResults) {

        }

        @Override
        public void onEvent(int eventType, Bundle params) {

        }
    }

}
