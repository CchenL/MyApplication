package com.example.myapplication;

/**
 *  请求回调接口
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
