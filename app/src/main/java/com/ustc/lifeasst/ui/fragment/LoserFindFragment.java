package com.ustc.lifeasst.ui.fragment;

import com.ustc.lifeasst.ui.view.LoadingPage.ResultState;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * 游戏
 * @author Kevin
 * @date 2015-10-27
 */
public class LoserFindFragment extends BaseFragment {

	@SuppressLint("ValidFragment")
	public LoserFindFragment() {

	}

	@SuppressLint("ValidFragment")
	public LoserFindFragment(Context context) {
		super(context);
		mContext = context;
	}

	@Override
	public View onCreateSuccessView() {
		TextView view = new TextView(mContext);
		view.setText(getClass().getSimpleName());
		return view;
	}

	@Override
	public ResultState onLoad() {
		return ResultState.STATE_EMPTY;
	}

}
