package com.example.nakao.speakpracticegame;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import org.atilika.kuromoji.Token;
import org.atilika.kuromoji.Tokenizer;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private SpeechRecognizer sr;

    Button button;
    TextView Judge,Question,Time;
    ImageView image;

    String RealAnswer;

    String[] Array={"こんにちは","青"};

    int Rand;//問題の選択のための乱数
    int Times=1;//回数格納用
    int RightAnswerNumber;//正解問数格納用



    // 音声認識を開始する
    protected void startListening() {
        try {
            if (sr == null) {
                sr = SpeechRecognizer.createSpeechRecognizer(this);
                if (!SpeechRecognizer.isRecognitionAvailable(getApplicationContext())) {
                    Toast.makeText(getApplicationContext(), "音声認識が使えません",
                            Toast.LENGTH_LONG).show();
                    finish();
                }
                sr.setRecognitionListener(new listener());
            }
            // インテントの作成
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            // 言語モデル指定
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
            sr.startListening(intent);
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "startListening()でエラーが起こりました",
                    Toast.LENGTH_LONG).show();
            finish();
        }

    }

    // 音声認識を終了する
    protected void stopListening() {
        if (sr != null) sr.destroy();
        sr = null;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tokenizer= Tokenizer.builder().build();

        button=(Button)findViewById(R.id.button);
        Judge=(TextView)findViewById(R.id.Judge);
        Question=(TextView)findViewById(R.id.question);
        Time=(TextView)findViewById(R.id.Times);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Times==4) {
                    Intent intent = new Intent(v.getContext(), ResultActivity.class);
                    intent.putExtra("RightAnswerNumber", RightAnswerNumber);
                    startActivity(intent);

                }else{
                    Time.setText("だい"+Times+"もん");
                    Judge.setText("");
                    ((ImageView) findViewById(R.id.character)).setImageResource(R.drawable.bird1);

                    //問題の設定
                    Rand=new Random().nextInt(2);
                    RealAnswer=Array[Rand];
                    Question.setText(RealAnswer);

                    startListening();

                    Times++;
                }
            }
        });
    }


    // RecognitionListenerの定義
    // 中が空でも全てのメソッドを書く必要がある
    class listener implements RecognitionListener {
        // 話し始めたときに呼ばれる
        public void onBeginningOfSpeech() {

        }

        // 結果に対する反応などで追加の音声が来たとき呼ばれる
        // しかし呼ばれる保証はないらしい
        public void onBufferReceived(byte[] buffer) {
        }

        // 話し終わった時に呼ばれる
        public void onEndOfSpeech() {

        }

        // ネットワークエラーか認識エラーが起きた時に呼ばれる
        public void onError(int error) {
            String reason = "";
            switch (error) {
                // Audio recording error
                case SpeechRecognizer.ERROR_AUDIO:
                    reason = "ERROR_AUDIO";
                    break;
                // Other client side errors
                case SpeechRecognizer.ERROR_CLIENT:
                    reason = "ERROR_CLIENT";
                    break;
                // Insufficient permissions
                case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                    reason = "ERROR_INSUFFICIENT_PERMISSIONS";
                    break;
                // 	Other network related errors
                case SpeechRecognizer.ERROR_NETWORK:
                    reason = "ERROR_NETWORK";
                    /* ネットワーク接続をチェックする処理をここに入れる */
                    break;
                // Network operation timed out
                case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                    reason = "ERROR_NETWORK_TIMEOUT";
                    break;
                // No recognition result matched
                case SpeechRecognizer.ERROR_NO_MATCH:
                    reason = "わからなかったよ　もう１かいやってみて！";
                    Times--;
                    break;
                // RecognitionService busy
                case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                    reason = "ERROR_RECOGNIZER_BUSY";
                    break;
                // Server sends error status
                case SpeechRecognizer.ERROR_SERVER:
                    reason = "ERROR_SERVER";
                    /* ネットワーク接続をチェックをする処理をここに入れる */
                    break;
                // No speech input
                case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                    reason = "ERROR_SPEECH_TIMEOUT";
                    break;
            }
            Toast.makeText(getApplicationContext(), reason, Toast.LENGTH_SHORT).show();
            stopListening();
        }

        // 将来の使用のために予約されている
        public void onEvent(int eventType, Bundle params) {
        }

        // 部分的な認識結果が利用出来るときに呼ばれる
        // 利用するにはインテントでEXTRA_PARTIAL_RESULTSを指定する必要がある
        public void onPartialResults(Bundle partialResults) {
        }

        // 音声認識の準備ができた時に呼ばれる
        public void onReadyForSpeech(Bundle params) {
            Toast.makeText(getApplicationContext(), "はなしてみて！",
                    Toast.LENGTH_SHORT).show();
        }

        // 認識結果が準備できた時に呼ばれる
        public void onResults(Bundle results) {

            stopListening();

            // 結果をArrayListとして取得
            ArrayList results_array = results.getStringArrayList(
                    SpeechRecognizer.RESULTS_RECOGNITION);

            //１番可能性の高いものを取得
            String resultsString =(String)(results_array.get(0));

            if(RealAnswer.equals(resultsString)){
                Judge.setText("せいかい！");
                ((ImageView) findViewById(R.id.character)).setImageResource(R.drawable.bird4);
                RightAnswerNumber++;
            }else{
                Judge.setText("ざんねん…"+resultsString+"ってきこえたよ");
                ((ImageView) findViewById(R.id.character)).setImageResource(R.drawable.bird2);
            }

            if(Times==4){
                button.setText("けっかはっぴょうへすすむ");
            }
        }

        // サウンドレベルが変わったときに呼ばれる
        // 呼ばれる保証はない
        public void onRmsChanged(float rmsdB) {
        }
    }
}
