package com.example.nakao.speakpracticegame;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ryosuke on 17/11/02.
 */

public class DescendAdapter extends CommonAbstractAdapter {

    public DescendAdapter(Context context, int id, int[] i_array) {
        super(context, id, i_array);
    }

    @Override
    void fillMap(){
        for(int i=0; i<mArray.length; i++){
            String key = String.valueOf(Gojuon.getGojuon().charAt(i));
            mMap.put(key, mArray[i]);
        }
        mList = new ArrayList<Map.Entry<String, Integer>>(mMap.entrySet());

        //  比較関数Comparatorを使用してMap.Entryの値を比較する（降順）
        Collections.sort(mList, new Comparator<Map.Entry<String, Integer>>() {
            //compareを使用して値を比較する
            public int compare(Map.Entry<String, Integer> obj1, Map.Entry<String, Integer> obj2)
            {
                //降順
                return obj2.getValue().compareTo(obj1.getValue());
            }
        });
    }



}
