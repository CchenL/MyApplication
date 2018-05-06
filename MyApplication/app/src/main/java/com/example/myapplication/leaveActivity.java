package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.LinkedList;
import java.util.List;

public class leaveActivity extends AppCompatActivity  {
    private static final String[] vocations = {"事假","病假","公假"};
    private static final String[] grades = {"2014级","2015级","2016级","2017级"};
    private static final String[] classes = {"软件1班","软件2班","软件3班"};
    private Button save;
    /*private TextView test;*/
    private EditText dateStart;
    private EditText timeStart;
    private EditText dateEnd;
    private EditText timeEnd;
    private DatePickerDialog dateDialogStart;
    private TimePickerDialog timeDialogStart;
    private DatePickerDialog dateDialogEnd;
    private TimePickerDialog timeDialogEnd;
    private TextView text_grade;
    private TextView text_class;
    private TextView text_type;
    private Spinner spinner_type;
    private Spinner spinner_grade;
    private Spinner spinner_class;
    private int year,monthOfYear,dayOfMonth,hourOfDay,minute;
    //菜单项ID常量
    private static final int ITEM1 = Menu.FIRST;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);//面的三个参数对应于上面的年、月、日
    initView();
    //通过Calendar对象来获取年、月、日、时、分的信息
    Calendar calendar = Calendar.getInstance();
    year = calendar.get(calendar.YEAR);
    monthOfYear = calendar.get(calendar.MONTH);
    dayOfMonth = calendar.get(calendar.DAY_OF_MONTH);
    hourOfDay = calendar.get(calendar.HOUR_OF_DAY);
    minute = calendar.get(calendar.MINUTE);
        /*
        *开始时间
         */
    dateDialogStart = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker arg0, int year, int monthOfYear,
        int dayOfMonth) {
            // 把获取的日期显示在文本框内，月份从0开始计数，所以要加1
            String text = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
            dateStart.setText(text);
        }
    }, year, monthOfYear, dayOfMonth); // 后
    dateStart.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View arg0){
            dateDialogStart.show();
        }
    });
         /*
         * 实例化TimePickerDialog
         */
    timeDialogStart = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // TODO Auto-generated method stub
            String text = hourOfDay + ":" + minute;
            timeStart.setText(text);
        }
    }, hourOfDay, minute, true); // 最后一个参数设置是否为24小时制
    /**
     * 对时间选择器按钮设置监听事件
     */
    timeStart.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View arg0) {
            // 点击时间选择器按钮时显示出时间对话框
            timeDialogStart.show();
        }
    });
        /*
        *以上为开始时间，以下为结束时间
         */
    dateDialogEnd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker arg0, int year, int monthOfYear,
        int dayOfMonth) {
            // 把获取的日期显示在文本框内，月份从0开始计数，所以要加1
            String text = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
            dateEnd.setText(text);
        }
    }, year, monthOfYear, dayOfMonth); // 后面的三个参数对应于上面的年、月、日
    dateEnd.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View arg0){
            dateDialogEnd.show();
        }
    });
         /*
         * 实例化TimePickerDialog
         */
    timeDialogEnd = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // TODO Auto-generated method stub
            String text = hourOfDay + ":" + minute;
            timeEnd.setText(text);
        }
    }, hourOfDay, minute, true); // 最后一个参数设置是否为24小时制
    /**
     * 对时间选择器按钮设置监听事件
     */
    timeEnd.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View arg0) {
            // 点击时间选择器按钮时显示出时间对话框
            timeDialogEnd.show();
        }
    });

    //实例化ArrayAdapter;
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item,
            vocations);
    ArrayAdapter<String>adapter1 = new ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item,
            grades
    );
    ArrayAdapter<String>adapter2 = new ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item,
            classes
    );
    //设置Spinner的下拉列表显示样式
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    //将adapter添加到Spinner中，数据对象+adapter捆绑
    spinner_type.setAdapter(adapter);
    spinner_grade.setAdapter(adapter1);
    spinner_class.setAdapter(adapter2);
    //设置Spainner的一些属性
    spinner_type.setPrompt("请选择请假类型");
    spinner_type.setSelection(0,true);
    spinner_grade.setPrompt("请选择所在年级");
    spinner_grade.setSelection(1,true);
    spinner_class.setPrompt("请选择所在班级");
    spinner_class.setSelection(2,true);
    //添加Spinner事件监听
    spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            text_type.setText("你选择的请假类型是：" + vocations[arg2]);
            text_type.setText("你选择的请假类型是：" + arg0.getItemAtPosition(arg2).toString());
            //设置显示当前选择的项
            arg0.setVisibility(View.VISIBLE);
        }
    public void onNothingSelected(AdapterView<?>arg0){}
});
    }

public void initView(){
        text_type= (TextView)findViewById(R.id.TextView_Show);
        spinner_type = (Spinner)findViewById(R.id.spinner_type);
        text_grade = (TextView)findViewById(R.id.textView_grade);
        spinner_grade = (Spinner)findViewById(R.id.spinner_grade);
        text_class = (TextView)findViewById(R.id.textView_class);
        spinner_class = (Spinner)findViewById(R.id.spinner_class);
        dateStart = (EditText)findViewById(R.id.dateStart);
        timeStart = (EditText)findViewById(R.id.timeStart);
        dateEnd = (EditText)findViewById(R.id.dateEnd);
        timeEnd = (EditText)findViewById(R.id.timeEnd);
        /*save = (Button)findViewById(R.id.save);
        test = (TextView)findViewById(R.id.test);
        save.setOnClickListener(this);*/
        }

/*    @Override
    public void onClick(View v) {
        //保存按钮
        Toast.makeText(this,"您点击了保存",Toast.LENGTH_SHORT);
    }*/
}
