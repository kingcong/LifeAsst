package com.ustc.lifeasst.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.ustc.lifeasst.ui.view.LoadingPage.ResultState;
import com.ustc.lifeasst.utils.UIUtils;

/**
 * 首页
 * 
 * @author Kevin
 * @date 2015-10-27
 */
public class HomeFragment extends BaseFragment {

	// 如果加载数据成功, 就回调此方法, 在主线程运行
	@Override
	public View onCreateSuccessView() {
		TextView view = new TextView(UIUtils.getContext());
		view.setText(getClass().getSimpleName());
		return view;
	}

	// 运行在子线程,可以直接执行耗时网络操作
	@Override
	public ResultState onLoad() {
		// 请求网络
		return ResultState.STATE_SUCCESS;
	}

}
