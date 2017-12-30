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

public abstract class CommonAbstractAdapter extends BaseAdapter {
    private Context mContext;
    protected int[] mArray;
    private LayoutInflater mLayoutInfrater = null;
    private int mLayoutID = 0;

    protected Map<String,Integer> mMap = new HashMap<>(46);
    protected List<Map.Entry<String, Integer>> mList;

    static class ViewHolder{
        TextView moji;
    }



    public CommonAbstractAdapter(Context context, @LayoutRes int id,  int[] i_array) {
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
            holder.moji = (TextView)convertView.findViewById(R.id.score_moji);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.moji.setText(String.valueOf(Gojuon.getGojuon().charAt(position)));

        return convertView;
    }
    abstract void fillMap();




}
