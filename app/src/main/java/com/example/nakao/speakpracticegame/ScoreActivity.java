package com.example.nakao.speakpracticegame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ScoreActivity extends AppCompatActivity {

    private BaseAdapter mBaseAdapter = null;
    private Button mSortButton;
    private ListView mListView = null;
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

        mSortButton = (Button)findViewById(R.id.sortButton);

        mListView = (ListView)findViewById(R.id.listView);
        mBaseAdapter = new AscendAdapter(this.getApplicationContext(), R.layout.itemrow, evalue_array);
        mListView.setAdapter(mBaseAdapter);

        mSortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter_type == AdapterSwitch.Ascend){
                    adapter_type = AdapterSwitch.Descend;
                    mBaseAdapter = new DescendAdapter(getApplicationContext(), R.layout.itemrow, evalue_array);
                } else if(adapter_type == AdapterSwitch.Descend){
                    adapter_type = AdapterSwitch.Ascend;
                    mBaseAdapter = new AscendAdapter(getApplicationContext(), R.layout.itemrow, evalue_array);
                }
                mListView.setAdapter(mBaseAdapter);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        if (mBaseAdapter == null){
            int[] evalue_array = ArrayUtil.getArray(ArrayUtil.EVALUE_KEY_WORD, this.getApplicationContext());
            if(adapter_type == AdapterSwitch.Ascend){
                mBaseAdapter = new AscendAdapter(getApplicationContext(), R.layout.itemrow, evalue_array);
            } else if(adapter_type == AdapterSwitch.Descend){
                mBaseAdapter = new DescendAdapter(getApplicationContext(), R.layout.itemrow, evalue_array);
            }
            mListView.setAdapter(mBaseAdapter);
        }

    }
}
