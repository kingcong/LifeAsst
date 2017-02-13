package com.ustc.lifeasst.ui.fragment;

import com.ustc.lifeasst.ui.view.LoadingPage.ResultState;

import android.view.View;

/**
 * 应用
 * @author Kevin
 * @date 2015-10-27
 */
public class AppFragment extends BaseFragment {

	//只有成功才走此方法
	@Override
	public View onCreateSuccessView() {
		return null;
	}

	@Override
	public ResultState onLoad() {
		return ResultState.STATE_ERROR;
	}

}
