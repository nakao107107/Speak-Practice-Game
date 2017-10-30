package com.example.nakao.speakpracticegame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private SpeechRecognizer sr;

    private Button mButton;
    private TextView mJudgeText,mQuestionText,mTimeText;
    private ImageView mImageView;

    private String mRightAnsText;

    final String[][] Array={{"あお","あか"},{"きいろ","みどり"},{"おれんじ","だいだい"},{"こんにちは","ありがとう"}};

    int Rand;//問題の選択のための乱数
    int level=0;//レベル格納用
    static int mTimes;//回数格納用
    int RightAnswerNumber;//正解問数格納用
    static int ProgramNumber;//問題数格納用

    TokenizerUtil tokenizerUtil;

    Handler mHandler=new Handler();




    // 音声認識を開始する
    protected void startListening(String right_word) {
        try {
            if (sr == null) {
                sr = SpeechRecognizer.createSpeechRecognizer(this);
                if (!SpeechRecognizer.isRecognitionAvailable(getApplicationContext())) {
                    Toast.makeText(getApplicationContext(), "音声認識が使えません",
                            Toast.LENGTH_LONG).show();
                    finish();
                }
                sr.setRecognitionListener(new Listener(right_word));
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

        setContentView(R.layout.game_activity_main);

        Intent intent=getIntent();
        level=intent.getIntExtra("LEVEL",0)-1;

        mButton=(Button)findViewById(R.id.button);
        mJudgeText=(TextView)findViewById(R.id.Judge);
        mQuestionText=(TextView)findViewById(R.id.question);
        mTimeText=(TextView)findViewById(R.id.Times);
        mImageView = (ImageView) findViewById(R.id.character);
        GlideDrawableImageViewTarget target = new GlideDrawableImageViewTarget(mImageView);
        Glide.with(this).load(R.drawable.ordinary).into(target);

        mTimes=1;//問題数の初期化

        //Preferenceファイルから問題数を取得（設定なしの場合10問）
        SharedPreferences data = getSharedPreferences("Setting", Context.MODE_PRIVATE);
        ProgramNumber = data.getInt("ProgramNumber",10 );

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mButton.setEnabled(false);//誤作動防止用にスタートボタンを使用不能にする
                mButton.setText("ゲームちゅう");

                GlideDrawableImageViewTarget target = new GlideDrawableImageViewTarget(mImageView);
                Glide.with(getApplicationContext()).load(R.drawable.ordinary).into(target);

                if(mTimes==ProgramNumber+1) {
                    Intent intent = new Intent(v.getContext(), ResultActivity.class);
                    intent.putExtra("RightAnswerNumber", RightAnswerNumber);
                    startActivity(intent);

                }else{

                    mTimeText.setText("だい"+mTimes+"もん");
                    mJudgeText.setText("");

                    //問題の設定
                    Rand=new Random().nextInt(2);
                    mRightAnsText=Array[level][Rand];
                    mQuestionText.setText(mRightAnsText);

                    startListening(mRightAnsText);
                    mTimes++;
                }
            }
        });
    }


    // RecognitionListenerの定義
    // 中が空でも全てのメソッドを書く必要がある
    class Listener implements RecognitionListener {
        private String mRightString;

        public Listener(String right_string) {
            mRightString = right_string;
        }

        // 話し始めたときに呼ばれる
        public void onBeginningOfSpeech() {

        }

        @Override
        public void onRmsChanged(float rmsdB) {

        }

        // 結果に対する反応などで追加の音声が来たとき呼ばれる
        // しかし呼ばれる保証はないらしい
        public void onBufferReceived(byte[] buffer) {
        }

        // 話し終わった時に呼ばれる
        public void onEndOfSpeech() {

            mJudgeText.setText("かんがえちゅう");

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
                    mTimes--;
                    mButton.setText("もう１どチャレンジ");
                    mButton.setEnabled(true);
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
                    reason = "わからなかったよ　もう１かいやってみて！";
                    mTimes--;
                    mButton.setText("もう１どチャレンジ");
                    mButton.setEnabled(true);
                    break;
            }
            mJudgeText.setText(reason);
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
            mJudgeText.setText("はなしてみて！");
        }

        // 認識結果が準備できた時に呼ばれる
        public void onResults(Bundle results) {

            stopListening();

            // 結果をArrayListとして取得
            ArrayList results_array = results.getStringArrayList(
                    SpeechRecognizer.RESULTS_RECOGNITION);

            //１番可能性の高いものを取得
            final String result_string =(String)(results_array.get(0));

            judgeAndNext(result_string);
        }

        private void judgeAndNext(String result_string){

            AsyncTask<String,String,String> asyncTask = new AsyncTask<String, String, String>() {
                @Override
                protected String doInBackground(String... params) {

                    //kuromojiにかけてカタカナ変換
                    String tmp_string =tokenizerUtil.getKatakana(params[0]);

                    Log.d("進捗","kuromojiの処理が完了しました");

                    //ひらがなに変換
                    tmp_string= HiraganaKatakanaMatch.zenkakuHiraganaToZenkakuKatakana(params[0]);

                    return tmp_string;
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    StringBuilder sbuilder = new StringBuilder();
                    int drawableInt;

                    if(mRightString.equals(s)){
                        sbuilder.append("せいかい");
                        drawableInt = R.drawable.happy;
                    } else {
                        sbuilder.append("ざんねん「"+ s + "」ときこえたよ");
                        drawableInt = R.drawable.sad;
                    }

                    String setting_text = sbuilder.toString();
                    mJudgeText.setText(setting_text);
                    GlideDrawableImageViewTarget target = new GlideDrawableImageViewTarget(mImageView);
                    Glide.with(getApplicationContext()).load(drawableInt).into(target);
                    if(mTimes==ProgramNumber+1) {
                        mButton.setText("けっかはっぴょうへすすむ");
                    } else {
                        mButton.setText("つぎのもんだいにチャレンジ");
                    }
                    mButton.setEnabled(true);
                }
            };

            asyncTask.execute(result_string);
        }
    }

}
