package com.ustc.lifeasst.base.impl;

import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.slidebar.ScrollBar;
import com.ustc.lifeasst.R;
import com.ustc.lifeasst.base.BasePager;

/**
 * 首页
 * 
 * @author Kevin
 * @date 2015-10-18
 */
public class HomePager extends BasePager {

//	@ViewInject(R.id.homepager_listview)
//	private ListView lvList;
//
//	@ViewInject(R.id.homepager_searchbar)
//	private SearchView searchView;

	private static final String[] strs = new String[] {
		"first", "second", "third", "fourth", "fifth","first", "second", "third", "fourth", "fifth","first", "second", "third", "fourth", "fifth"
	};

	public HomePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {
		System.out.println("首页初始化啦...");
		Indicator indicator = new Indicator() {
			@Override
			public void setAdapter(IndicatorAdapter indicatorAdapter) {

			}

			@Override
			public void setOnItemSelectListener(OnItemSelectedListener onItemSelectedListener) {

			}

			@Override
			public OnItemSelectedListener getOnItemSelectListener() {
				return null;
			}

			@Override
			public void setOnTransitionListener(OnTransitionListener onTransitionListener) {

			}

			@Override
			public OnTransitionListener getOnTransitionListener() {
				return null;
			}

			@Override
			public void setScrollBar(ScrollBar scrollBar) {

			}

			@Override
			public IndicatorAdapter getIndicatorAdapter() {
				return null;
			}

			@Override
			public void setCurrentItem(int i) {

			}

			@Override
			public void setCurrentItem(int i, boolean b) {

			}

			@Override
			public View getItemView(int i) {
				return null;
			}

			@Override
			public int getCurrentItem() {
				return 0;
			}

			@Override
			public int getPreSelectItem() {
				return 0;
			}

			@Override
			public void onPageScrolled(int i, float v, int i1) {

			}

			@Override
			public void onPageScrollStateChanged(int i) {

			}

			@Override
			public void setItemClickable(boolean b) {

			}

			@Override
			public boolean isItemClickable() {
				return false;
			}
		};

		View view = View.inflate(mActivity, R.layout.home_pager, null);
		View mHeaderView = View.inflate(mActivity, R.layout.homepager_listview_header,
				null);

		flContent.addView(view);

		// 修改页面标题
		tvTitle.setText("高教区助手");

		// 隐藏菜单按钮
		btnMenu.setVisibility(View.GONE);

//		SearchView searchView = (SearchView) view.findViewById(R.id.homepager_searchbar);
		ListView listView = (ListView) view.findViewById(R.id.homepager_listview);
		listView.addHeaderView(mHeaderView);
		listView.setAdapter(new ArrayAdapter<String>(mActivity,
				android.R.layout.simple_list_item_1, strs));
	}


}
