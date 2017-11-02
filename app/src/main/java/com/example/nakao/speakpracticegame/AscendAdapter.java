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

public class AscendAdapter extends BaseAdapter {
    Context mContext;
    int[] mArray;
    LayoutInflater mLayoutInfrater = null;
    int mLayoutID = 0;
    Map<String,Integer> mMap = new HashMap<>(50);
    List<Map.Entry<String, Integer>> mList;

    private static String GOJUON = "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわをん！?#$";

    static class ViewHolder{
        TextView moji;
        TextView number;
    }



    public AscendAdapter(Context context, @LayoutRes int id,  int[] i_array) {
        this.mContext = context;
        this.mLayoutID = id;
        this.mArray = i_array;
        this.mLayoutInfrater = LayoutInflater.from(mContext);
        fillMap();
    }

    @Override
    public int getCount() {
        return mArray.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null){
            convertView = mLayoutInfrater.inflate(mLayoutID, null);
            holder = new ViewHolder();
            holder.moji = (TextView)convertView.findViewById(R.id.moji);
            holder.number = (TextView)convertView.findViewById(R.id.number);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.moji.setText(String.valueOf(mList.get(position).getKey()));
        holder.number.setText(String.valueOf(mList.get(position).getValue()));

        return convertView;
    }

    private void fillMap(){
        for(int i=0; i<mArray.length; i++){
            String key = String.valueOf(GOJUON.charAt(i));
            mMap.put(key, mArray[i]);
        }
        mList = new ArrayList<Map.Entry<String, Integer>>(mMap.entrySet());
        // 比較関数Comparatorを使用してMap.Entryの値を比較する(昇順)
        Collections.sort(mList, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> obj1, Map.Entry<String, Integer> obj2) {
                //  昇順
                return obj1.getValue().compareTo(obj2.getValue());
            }
        });


    }



}
