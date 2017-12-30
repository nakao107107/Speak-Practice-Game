package com.example.nakao.speakpracticegame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    private BaseAdapter baseAdapter = null;
    private Button sortButton;
    private TextView scoreTextView;
    private ListView listView = null;
    private AdapterSwitch adapter_type = AdapterSwitch.Ascend;
    private enum AdapterSwitch{
        Ascend,
        Descend
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        final int[] evalue_array = ArrayUtil.getArray(ArrayUtil.EVALUE_KEY_WORD, this.getApplicationContext());

        sortButton = (Button)findViewById(R.id.sortButton);
        scoreTextView = (TextView)findViewById(R.id.scoreTextView);

        listView = (ListView)findViewById(R.id.listView);
        baseAdapter = new AscendAdapter(this.getApplicationContext(), R.layout.itemrow, evalue_array);
        listView.setAdapter(baseAdapter);

        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter_type == AdapterSwitch.Ascend){

                    adapter_type = AdapterSwitch.Descend;
                    baseAdapter = new DescendAdapter(getApplicationContext(), R.layout.itemrow, evalue_array);
                    sortButton.setText("よかったじゅんにする");
                    scoreTextView.setText("わるかったじゅん");
                } else if(adapter_type == AdapterSwitch.Descend){
                    adapter_type = AdapterSwitch.Ascend;
                    baseAdapter = new AscendAdapter(getApplicationContext(), R.layout.itemrow, evalue_array);
                    sortButton.setText("わるかったじゅんにする");
                    scoreTextView.setText("よかったじゅん");
                }
                listView.setAdapter(baseAdapter);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        if (baseAdapter == null){
            int[] evalue_array = ArrayUtil.getArray(ArrayUtil.EVALUE_KEY_WORD, this.getApplicationContext());
            if(adapter_type == AdapterSwitch.Ascend){
                baseAdapter = new AscendAdapter(getApplicationContext(), R.layout.itemrow, evalue_array);
            } else if(adapter_type == AdapterSwitch.Descend){
                baseAdapter = new DescendAdapter(getApplicationContext(), R.layout.itemrow, evalue_array);
            }
            listView.setAdapter(baseAdapter);
        }

    }
}
