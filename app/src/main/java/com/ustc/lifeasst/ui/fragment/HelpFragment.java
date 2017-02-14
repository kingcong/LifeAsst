package com.ustc.lifeasst.ui.fragment;

import com.ustc.lifeasst.ui.view.LoadingPage.ResultState;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * 应用
 * @author Kevin
 * @date 2015-10-27
 */
public class HelpFragment extends BaseFragment {

	public HelpFragment(Context context) {
		super(context);
		mContext = context;
	}

	//只有成功才走此方法
	@Override
	public View onCreateSuccessView() {

		TextView view = new TextView(mContext);
		view.setText(getClass().getSimpleName());
		return view;
	}

	@Override
	public ResultState onLoad() {
		return ResultState.STATE_ERROR;
	}

}
