package com.ustc.lifeasst.base;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ustc.lifeasst.R;
import com.ustc.lifeasst.ui.fragment.BaseFragment;
import com.ustc.lifeasst.ui.view.LoadingPage;
import com.ustc.lifeasst.utils.UIUtils;

/**
 * 五个标签页的基类
 * 
 * @author Kevin
 * @date 2015-10-18
 */
public class BasePager {

	public Activity mActivity;

	public TextView tvTitle;
	public ImageButton btnMenu;
	public FrameLayout flContent;// 空的帧布局对象, 要动态添加布局

	public View mRootView;// 当前页面的布局对象


	private static final int STATE_LOAD_UNDO = 1;// 未加载
	private static final int STATE_LOAD_LOADING = 2;// 正在加载
	private static final int STATE_LOAD_ERROR = 3;// 加载失败
	private static final int STATE_LOAD_EMPTY = 4;// 数据为空
	private static final int STATE_LOAD_SUCCESS = 5;// 加载成功

	private int mCurrentState = STATE_LOAD_SUCCESS;// 当前状态

	private View mLoadingPage;
	private View mErrorPage;
	private View mEmptyPage;
	private View mSuccessPage;

	public RelativeLayout relativeLayout;

	public BasePager(Activity activity) {
		mActivity = activity;
		mRootView = initView();
	}

	// 初始化布局
	public View initView() {
		View view = View.inflate(mActivity, R.layout.base_pager, null);
		relativeLayout = (RelativeLayout) view.findViewById(R.id.title_bar_layout);
		tvTitle = (TextView) view.findViewById(R.id.tv_title);
		btnMenu = (ImageButton) view.findViewById(R.id.btn_menu);
		flContent = (FrameLayout) view.findViewById(R.id.fl_content);

		if (mLoadingPage == null) {
			mLoadingPage = View.inflate(mActivity, R.layout.page_loading, null);
			flContent.addView(mLoadingPage);
		}

		if (mErrorPage == null) {
			mErrorPage = View.inflate(mActivity,R.layout.page_error,null);
			flContent.addView(mErrorPage);
		}

		if (mEmptyPage == null) {
			mEmptyPage = View.inflate(mActivity,R.layout.page_empty,null);
			flContent.addView(mEmptyPage);
		}



		btnMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		return view;
	}


	// 根据当前状态,决定显示哪个布局
	private void showRightPage() {

		mLoadingPage
				.setVisibility((mCurrentState == STATE_LOAD_UNDO || mCurrentState == STATE_LOAD_LOADING) ? View.VISIBLE
						: View.GONE);

		mErrorPage
				.setVisibility(mCurrentState == STATE_LOAD_ERROR ? View.VISIBLE
						: View.GONE);

		mEmptyPage
				.setVisibility(mCurrentState == STATE_LOAD_EMPTY ? View.VISIBLE
						: View.GONE);

		// 当成功布局为空,并且当前状态为成功,才初始化成功的布局
		if (mSuccessPage == null && mCurrentState == STATE_LOAD_SUCCESS) {
			mSuccessPage = onCreateSuccessView();
			if (mSuccessPage != null) {
				flContent.addView(mSuccessPage);
			}
		}

		if (mSuccessPage != null) {
			mSuccessPage
					.setVisibility(mCurrentState == STATE_LOAD_SUCCESS ? View.VISIBLE
							: View.GONE);
		}
	}

	public View onCreateSuccessView() {
		return null;
	}

	// 初始化数据
	public void initData() {
		showRightPage();
	}
}
