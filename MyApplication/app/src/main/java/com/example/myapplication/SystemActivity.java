package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.fragment.*;

import java.util.ArrayList;
import java.util.List;

public class SystemActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ViewPager mViewPager;
    private BottomNavigationView navigation;
    List<Fragment> listFragment; //存储页面对象

    private void initView(){
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);

        listFragment = new ArrayList<>();
        listFragment.add(new fragment_leave());
        listFragment.add(new fragment_communicate());
        listFragment.add(new fragment_mine());

        MyFragAdapte myAdapter = new MyFragAdapte(getSupportFragmentManager(), this, listFragment);
        mViewPager.setAdapter(myAdapter);

        //相互关联导航栏点击事件和ViewPager滑动事件
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //这里设置为：当点击到某子项，ViewPager就滑动到对应位置
                switch (item.getItemId()) {
                    case R.id.navigation_leave:
                        mViewPager.setCurrentItem(0);
                        return true;
                    case R.id.navigation_communicate:
                        mViewPager.setCurrentItem(1);
                        return true;
                    case R.id.navigation_mine:
                        mViewPager.setCurrentItem(2);
                        return true;
                    default:
                        break;
                }
                return false;
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                /**
                 *注意这个方法滑动时会调用多次，参数解释：
                 * position当前所处页面索引,滑动调用的最后一次绝对是滑动停止所在页面
                 * positionOffset:表示从位置的页面偏移的[0,1]的值。
                 * positionOffsetPixels:以像素为单位的值，表示与位置的偏移
                 */
            }

            @Override
            public void onPageSelected(int position) {
                //该方法只在滑动停止时调用，position滑动停止所在页面位置当滑动到某一位置，导航栏对应位置被按下
                navigation.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);

        initView();
    }

}
