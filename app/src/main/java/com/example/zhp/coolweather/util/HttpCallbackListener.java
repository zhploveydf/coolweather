package com.example.zhp.coolweather.util;

/**
 * Created by zhp on 2014/12/13 0013.
 */
public interface HttpCallbackListener {
	
	void onFinish(String response);
	
	void onError(Exception e);
}
