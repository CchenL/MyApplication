package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fragment.fragment_leave;

import java.util.LinkedList;

/**
 * Created by Administrator on 2018-4-27.
 */
public class MyAdapter extends BaseAdapter {
    private Context mContext;
    private LinkedList<Data> mData;

    public MyAdapter(LinkedList<Data> mData, fragment_leave mContext){}
    public MyAdapter(LinkedList<Data> mData, Context mContext){
        this.mData = mData;
        this.mContext = mContext;
    }
    @Override
    /*下面这一句话有错误*/
    public int getCount(){
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fragment_leave, parent, false);
            holder = new ViewHolder();
            holder.txt_content = (TextView) convertView.findViewById(R.id.txt_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txt_content.setText(mData.get(position).getContent());
        return convertView;
    }
    //添加一个元素
    public void add(Data data) {
        if (mData == null) {
            mData = new LinkedList<>();
        }
        mData.add(data);
        notifyDataSetChanged();
    }
    private class ViewHolder {
        TextView txt_content;
    }
}
