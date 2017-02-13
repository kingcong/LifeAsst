package com.ustc.lifeasst.ui.fragment;

import com.ustc.lifeasst.ui.view.LoadingPage.ResultState;

import android.view.View;

/**
 * 游戏
 * @author Kevin
 * @date 2015-10-27
 */
public class GameFragment extends BaseFragment {

	@Override
	public View onCreateSuccessView() {
		return null;
	}

	@Override
	public ResultState onLoad() {
		return ResultState.STATE_EMPTY;
	}

}
