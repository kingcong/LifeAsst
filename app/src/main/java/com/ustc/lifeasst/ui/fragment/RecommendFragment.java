package com.ustc.lifeasst.ui.fragment;

import com.ustc.lifeasst.ui.view.LoadingPage.ResultState;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * 推荐
 * @author Kevin
 * @date 2015-10-27
 */
public class RecommendFragment extends BaseFragment {

	public RecommendFragment(Context context) {
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
		// TODO Auto-generated method stub
		return null;
	}

}
