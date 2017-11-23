package com.example.nakao.speakpracticegame;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
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
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//comment for git
public class GameActivity extends AppCompatActivity {

    private SpeechRecognizer sr;

    Button mButton;
    TextView mJudgeText,mQuestionText,mTimeText;
    ImageView mImageView;
    LinearLayout mGroundView;

    String realanswer;
    private String mRightAnsText;

    final String[][] Array={{"あお","あか"},{"きいろ","みどり"},{"おれんじ","だいだい"},{"こんにちは","ありがとう"}};

    int mRand;//問題の選択のための乱数
    int mLiver=0;//レベル格納用
    static int mTimes;//回数格納用
    int mRightAnswerNumber;//正解問数格納用
    static int mProgramNumber;//問題数格納用

    //パーミッション用の数字。何でも良い
    int REQUEST_PERMISSION = 100;

    private boolean mKumojiSwitch = true;




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
                sr.setRecognitionListener(new Listener());
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
        mLiver=intent.getIntExtra("LEVEL",0)-1;

        mButton=(Button)findViewById(R.id.button);
        mJudgeText=(TextView)findViewById(R.id.Judge);
        mQuestionText=(TextView)findViewById(R.id.question);
        mTimeText=(TextView)findViewById(R.id.Times);
        mImageView = (ImageView) findViewById(R.id.character);
        mGroundView = (LinearLayout) findViewById(R.id.background);
        GlideDrawableImageViewTarget target = new GlideDrawableImageViewTarget(mImageView);
        Glide.with(this).load(R.drawable.ordinary).into(target);

        mTimes=1;//問題数の初期化

        //Preferenceファイルから問題数を取得（設定なしの場合10問）
        SharedPreferences data = getSharedPreferences("Setting", Context.MODE_PRIVATE);
        mProgramNumber = data.getInt("mProgramNumber",10 );

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mButton.setEnabled(false);//誤作動防止用にスタートボタンを使用不能にする
                mButton.setText("ゲームちゅう");

                GlideDrawableImageViewTarget target = new GlideDrawableImageViewTarget(mImageView);
                Glide.with(getApplicationContext()).load(R.drawable.ordinary).into(target);

                if(mTimes==mProgramNumber+1) {
                    Intent intent = new Intent(v.getContext(), ResultActivity.class);
                    intent.putExtra("level",mLiver+1);
                    intent.putExtra("RightAnswerNumber", mRightAnswerNumber);
                    startActivity(intent);

                }else{

                    mTimeText.setText("だい"+mTimes+"もん");
                    mJudgeText.setText("");

                    //問題の設定
                    mRand=new Random().nextInt(2);
                    mRightAnsText=Array[mLiver][mRand];
                    mQuestionText.setText(mRightAnsText);
                    mTimes++;

                }
            }
        });
    }


    // RecognitionListenerの定義
    // 中が空でも全てのメソッドを書く必要がある
    class Listener implements RecognitionListener {


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

            //正誤を確認し画面を更新
            judgeAndNext(results_array);


        }

        private void judgeAndNext(ArrayList<String> result_arrays){

            AsyncTask<ArrayList<String>,String,String> asyncTask = new AsyncTask<ArrayList<String>, String, String>() {

                //バックグラウンド処理。時間がかかる処理はここでし、return値はonPostExecuteの引数となる。
                @Override
                protected String doInBackground(ArrayList<String>... params) {

                    //Kuromojiを使うか、もしくは配列の中からひらがなを取得する。
                    PredictStrategy predictStrategy = null;
                    if(mKumojiSwitch == true){
                        predictStrategy = new KuroPredictStrategy();
                    } else {
                        predictStrategy = new HiraPredictStrategy();
                    }

                    return predictStrategy.predict(params[0]);
                }

                // 引数はdoInbackgroundで取得したひらがな。発音が正しいかどうか判断し、正しい場合画像を変化させる。
                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    StringBuilder sbuilder = new StringBuilder();
                    int drawableInt;

                    ArrayUtil.saveArray(ArrayUtil.EVALUE_KEY_WORD, ArrayUtil.correctWord(mRightAnsText, s), getApplicationContext());  //sharedpreferenceに正誤配列を保存する。

                    HiraganaKatakanaMatch hMatch = new HiraganaKatakanaMatch();

                    if(mRightAnsText.equals(hMatch.zenkakuHiraganaToZenkakuKatakana(s))){
                        sbuilder.append("せいかい");
                        drawableInt = R.drawable.happy;
                        mRightAnswerNumber++;

                    } else {
                        sbuilder.append("ざんねん「"+ s + "」ときこえたよ");
                        drawableInt = R.drawable.sad;
                    }

                    String setting_text = sbuilder.toString();
                    mJudgeText.setText(setting_text);
                    GlideDrawableImageViewTarget target = new GlideDrawableImageViewTarget(mImageView);
                    Glide.with(getApplicationContext()).load(drawableInt).into(target);
                    if(mTimes==mProgramNumber+1) {
                        mButton.setText("けっかはっぴょうへすすむ");
                    } else {
                        mButton.setText("つぎのもんだいにチャレンジ");
                    }
                    mButton.setEnabled(true);
                }
            };

            asyncTask.execute(result_arrays);


            
        }
    }

    private void startAudioRecordingSafe() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                == PackageManager.PERMISSION_GRANTED) {
            startListening();
        } else {
            requestMicrophonePermission();
        }
    }

    private void requestMicrophonePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO) && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)) {
            Snackbar.make(mGroundView, "Microphone access is required in order to record audio",
                    Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityCompat.requestPermissions(GameActivity.this, new String[]{
                            Manifest.permission.RECORD_AUDIO, Manifest.permission.MODIFY_AUDIO_SETTINGS}, REQUEST_PERMISSION);
                }

            }).show();

        } else {
            ActivityCompat.requestPermissions(GameActivity.this, new String[]{
                            Manifest.permission.RECORD_AUDIO, Manifest.permission.MODIFY_AUDIO_SETTINGS},
                    REQUEST_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION && grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startListening();
        }

    }



}
