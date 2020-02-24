package com.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Data;
import com.example.myapplication.MyAdapter;
import com.example.myapplication.R;
import com.example.myapplication.SystemActivity;

import java.util.LinkedList;
import java.util.List;

public class fragment_leave extends Fragment {

    private ListView list_one;
    private Button btn_add;
    private TextView txt_empty;
    private MyAdapter mAdapter = null;
    private List<Data> mData = null;
    private fragment_leave mContext = null;
    private int flag = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leave, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        list_one = (ListView)view.findViewById(R.id.list_one);
        txt_empty = (TextView)view.findViewById(R.id.txt_empty);
        txt_empty.setText("暂无数据~");
        list_one.setAdapter(mAdapter);
        list_one.setEmptyView(txt_empty);
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn_add = (Button)getActivity().findViewById(R.id.btn_add);
        mContext = fragment_leave.this;
        mData = new LinkedList<Data>();
        mAdapter = new MyAdapter((LinkedList<Data>) mData, mContext);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"点击了"+flag+"按钮！！",Toast.LENGTH_SHORT).show();
                /*就是下面的这句，有错*/
                mAdapter.add(new Data("新增第" + flag + "个项 "));
                flag++;
            }
        });
    }




}
