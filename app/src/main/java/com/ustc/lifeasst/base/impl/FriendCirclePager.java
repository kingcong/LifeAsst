package com.ustc.lifeasst.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.ustc.lifeasst.base.BasePager;

/**
 * 智慧服务
 * 
 * @author Kevin
 * @date 2015-10-18
 */
public class FriendCirclePager extends BasePager {

	public FriendCirclePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {
		System.out.println("智慧服务初始化啦...");

		// 要给帧布局填充布局对象
		TextView view = new TextView(mActivity);
		view.setText("智慧服务");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);

		flContent.addView(view);

		// 修改页面标题
		tvTitle.setText("朋友圈");

		// 显示菜单按钮
		btnMenu.setVisibility(View.VISIBLE);
	}

}
