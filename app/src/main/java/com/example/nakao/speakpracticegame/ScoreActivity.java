package com.example.nakao.speakpracticegame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ScoreActivity extends AppCompatActivity {
    BaseAdapter mBaseAdapter = null;
    Button mSortButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        final int[] evalue_array = ArrayUtil.getArray(ArrayUtil.EVALUE_KEY_WORD, this.getApplicationContext());

        mSortButton = (Button)findViewById(R.id.sortButton);

        final ListView listView = (ListView)findViewById(R.id.listView);
        mBaseAdapter = new AscendAdapter(this.getApplicationContext(), R.layout.itemrow, evalue_array);
        listView.setAdapter(mBaseAdapter);

        mSortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBaseAdapter = new DescendAdapter(getApplicationContext(), R.layout.itemrow, evalue_array);
                listView.setAdapter(mBaseAdapter);
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mBaseAdapter == null){

        }

    }
}
