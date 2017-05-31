package com.ustc.lifeasst.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.baidu.mapapi.SDKInitializer;

/**
 * 自定义application, 进行全局初始化
 * 
 * @author Kevin
 * @date 2015-10-27
 */
public class LifeAsstApplication extends Application {

	private static Context context;
	private static Handler handler;
	private static int mainThreadId;

	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
		//注意该方法要再setContentView方法之前实现
		SDKInitializer.initialize(context);

		handler = new Handler();
		mainThreadId = android.os.Process.myTid();
	}

	public static Context getContext() {
		return context;
	}

	public static Handler getHandler() {
		return handler;
	}

	public static int getMainThreadId() {
		return mainThreadId;
	}
}
