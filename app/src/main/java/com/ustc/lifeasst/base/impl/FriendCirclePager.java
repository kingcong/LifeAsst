package com.ustc.lifeasst.base.impl;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.ustc.lifeasst.R;
import com.ustc.lifeasst.base.BasePager;
import com.ustc.lifeasst.ui.fragment.BaseFragment;
import com.ustc.lifeasst.ui.fragment.FragmentFactory;
import com.ustc.lifeasst.ui.view.NoScrollViewPager;


import java.util.ArrayList;
import java.util.List;

/**
 * 智慧服务
 * 
 * @author Kevin
 * @date 2015-10-18
 */
public class FriendCirclePager extends BasePager {

	private TextView item1, item2, item3, item4;
	private ViewPager mPageVp;
	List<Fragment> mFragmentList = new ArrayList<Fragment>();

	private int currentPosition = 0;

	public FriendCirclePager(Activity activity) {
		super(activity);
	}

	@Override
	public View onCreateSuccessView() {
		View view = View.inflate(mActivity, R.layout.friendview_pager, null);

		mPageVp = (ViewPager) view.findViewById(R.id.id_foodview_vp);

		FragmentActivity activity = (FragmentActivity) mActivity;
		mPageVp.setAdapter(new MyAdapter(activity.getSupportFragmentManager()));

		item1 = (TextView) view.findViewById(R.id.tv_friendview_tab_item1);
		item1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mPageVp.setCurrentItem(0);
			}
		});
		item2 = (TextView) view.findViewById(R.id.tv_friendview_tab_item2);
		item2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mPageVp.setCurrentItem(1);
			}
		});
		item3 = (TextView) view.findViewById(R.id.tv_friendview_tab_item3);
		item3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mPageVp.setCurrentItem(2);

			}
		});
		item4 = (TextView) view.findViewById(R.id.tv_friendview_tab_item4);
		item4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mPageVp.setCurrentItem(3);
			}
		});

		mPageVp.setCurrentItem(0);

		mPageVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				currentPosition = position;
				resetOtherTextView();
				switch (position) {
					case 0:
						item1.setTextColor(mActivity.getResources().getColor(R.color.selectedItemColor));
						break;
					case 1:
						item2.setTextColor(mActivity.getResources().getColor(R.color.selectedItemColor));
						break;
					case 2:
						item3.setTextColor(mActivity.getResources().getColor(R.color.selectedItemColor));
						break;
					case 3:
						item4.setTextColor(mActivity.getResources().getColor(R.color.selectedItemColor));
						break;
				}
			}

			@Override
			public void onPageSelected(int position) {

			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

		return view;
	}

	private void resetOtherTextView() {
		item1.setTextColor(mActivity.getResources().getColor(R.color.nomalItemColor));
		item2.setTextColor(mActivity.getResources().getColor(R.color.nomalItemColor));
		item3.setTextColor(mActivity.getResources().getColor(R.color.nomalItemColor));
		item4.setTextColor(mActivity.getResources().getColor(R.color.nomalItemColor));
	}

	/**
	 * FragmentPagerAdapter是PagerAdapter的子类, 如果viewpager的页面是fragment的话,就继承此类
	 */
	class MyAdapter extends FragmentPagerAdapter {

		private String[] mTabNames = {"1","2","3","4"};

		public MyAdapter(FragmentManager fm) {
			super(fm);
//			mTabNames = UIUtils.getStringArray(R.array.tab_names);// 加载页面标题数组
		}

		// 返回页签标题
		@Override
		public CharSequence getPageTitle(int position) {
			return mTabNames[position];
		}

		// 返回当前页面位置的fragment对象
		@Override
		public Fragment getItem(int position) {
			BaseFragment fragment = FragmentFactory.createFragment(position,mActivity);
			return fragment;
		}

		// fragment数量
		@Override
		public int getCount() {
			return mTabNames.length;
		}

	}

}