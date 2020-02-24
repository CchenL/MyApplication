package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etID;
    private EditText etPWD;
    private Button btnLOG;
    private Button btnREG;
    private CheckBox cb;
    private CheckBox cb_auto;
    private static String idstr;
    private static String pwdstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etID = (EditText) findViewById(R.id.editUerID);
        etPWD = (EditText) findViewById(R.id.editUserPwd);
        cb = (CheckBox) findViewById(R.id.checkRem);
        cb_auto = cb = (CheckBox) findViewById(R.id.checkAuto);
        btnLOG = (Button) findViewById(R.id.btnLogin);
        btnREG = (Button) findViewById(R.id.btnReg);
        btnLOG.setOnClickListener(this);
        btnREG.setOnClickListener(this);

        IfRemember();
    }

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.obj.toString()) {
                case "100":
                    Toast.makeText(MainActivity.this, "登陆成功！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, SystemActivity.class);
                    startActivity(intent);
                    MainActivity.this.finish();
                    break;
                case "200":
                    Toast.makeText(MainActivity.this, "账号或密码错误！", Toast.LENGTH_SHORT).show();
                    break;
                case "300":
                    Toast.makeText(MainActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                    break;
                case "400":
                    Toast.makeText(MainActivity.this, "账号已注册！", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(MainActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnReg:
                if (!TextUtils.isEmpty(etID.getText().toString()) && !TextUtils.isEmpty(etPWD.getText().toString())) {
                    register(etID.getText().toString(), etPWD.getText().toString());
                } else {
                    Toast.makeText(MainActivity.this, "账号、密码不能为空！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnLogin:
                if (!TextUtils.isEmpty(etID.getText().toString()) && !TextUtils.isEmpty(etPWD.getText().toString())) {
                    login(etID.getText().toString(), etPWD.getText().toString());
                } else {
                    Toast.makeText(MainActivity.this, "账号、密码不能为空！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void register(String account, String password) {
        String registerUrlStr = Constant.URL_Register + "?account=" + account + "&password=" + password;
        sendHttp(registerUrlStr);
    }

    private void login(String account, String password) {
        String loginUrlStr = Constant.URL_Login + "?account=" + account + "&password=" + password;
        sendHttp(loginUrlStr);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(cb.isChecked()){
            idstr = etID.getText().toString().trim();
            pwdstr = etID.getText().toString().trim();
            RememberMe(idstr,pwdstr);
        }
    }

//    protected void onDestory(){
//        super.onDestroy();
//    }

    //判断记住密码
    public void IfRemember() {
        SharedPreferences sp = getSharedPreferences(Constant.SP_INFO, MODE_PRIVATE); //获取Preferences
        idstr = sp.getString(Constant.USERID, null);
        pwdstr = sp.getString(Constant.USERPWD, null);
        if (idstr != null && pwdstr != null) {
            etID.setText(idstr);
            etPWD.setText(pwdstr);
            cb.setChecked(true);
        }
    }

    //记住密码
    public void RememberMe(String id, String pwd) {
        SharedPreferences sp = getSharedPreferences(Constant.SP_INFO, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit(); //获取Editor
        editor.putString(Constant.USERID, id); //保存用户
        editor.putString(Constant.USERPWD, pwd);
        editor.commit(); //返回boolean，apply无
    }

    public void AutoLogin(){

    }

    public void sendHttp(String url){
        try{
            HttpUtil.sendHttpRequest(url, new HttpCallbackListener() {
                @Override
                public void onFinish(String response) {
                    Message message = new Message();
                    message.obj = response;
                    mHandler.sendMessage(message);
                }

                @Override
                public void onError(Exception e) {
                    Message message = new Message();
                    message.obj = e.toString();
                    mHandler.sendMessage(message);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

