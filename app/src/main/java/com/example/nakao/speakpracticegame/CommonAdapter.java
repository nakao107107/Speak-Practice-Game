package com.example.nakao.speakpracticegame;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ryosuke on 17/11/02.
 */

public class CommonAdapter extends BaseAdapter {
    Context mContext;
    int[] mArray;
    LayoutInflater mLayoutInfrater = null;
    int mLayoutID = 0;

    private static String GOJUON = "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわをん！?#$";

    static class ViewHolder{
        TextView moji;
        TextView number;
    }



    public CommonAdapter(Context context, @LayoutRes int id,  int[] i_array) {
        this.mContext = context;
        this.mLayoutID = id;
        this.mArray = i_array;
        this.mLayoutInfrater = LayoutInflater.from(mContext);
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

        holder.moji.setText(String.valueOf(GOJUON.charAt(position)));
        holder.number.setText(String.valueOf(mArray[position]));

        return convertView;
    }




}
