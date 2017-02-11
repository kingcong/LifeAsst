package com.ustc.lifeasst.fragment;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.ustc.lifeasst.R;
import com.ustc.lifeasst.base.BasePager;
import com.ustc.lifeasst.base.impl.GovAffairsPager;
import com.ustc.lifeasst.base.impl.HomePager;
import com.ustc.lifeasst.base.impl.NearbyPager;
import com.ustc.lifeasst.base.impl.MinePager;
import com.ustc.lifeasst.base.impl.FriendCirclePager;
import com.ustc.lifeasst.view.NoScrollViewPager;

/**
 * 主页面fragment
 * 
 * @author Kevin
 * @date 2015-10-18
 */
public class ContentFragment extends BaseFragment {

	private NoScrollViewPager mViewPager;
	private RadioGroup rgGroup;

	private ArrayList<BasePager> mPagers;// 五个标签页的集合

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.fragment_content, null);
		mViewPager = (NoScrollViewPager) view.findViewById(R.id.vp_content);
		rgGroup = (RadioGroup) view.findViewById(R.id.rg_group);
		return view;
	}

	@Override
	public void initData() {
		mPagers = new ArrayList<BasePager>();

		// 添加四个标签页
		mPagers.add(new HomePager(mActivity));
		mPagers.add(new NearbyPager(mActivity));
		mPagers.add(new FriendCirclePager(mActivity));
//		mPagers.add(new GovAffairsPager(mActivity));
		mPagers.add(new MinePager(mActivity));

		mViewPager.setAdapter(new ContentAdapter());

		// 底栏标签切换监听
		rgGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_home:
					// 首页
					// mViewPager.setCurrentItem(0);
					mViewPager.setCurrentItem(0, false);// 参2:表示是否具有滑动动画
					break;
				case R.id.rb_news:
					// 新闻中心
					mViewPager.setCurrentItem(1, false);
					break;
				case R.id.rb_smart:
					// 智慧服务
					mViewPager.setCurrentItem(2, false);
					break;
//				case R.id.rb_gov:
//					// 政务
//					mViewPager.setCurrentItem(3, false);
//					break;
				case R.id.rb_setting:
					// 设置
					mViewPager.setCurrentItem(3, false);
					break;

				default:
					break;
				}
			}
		});

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				BasePager pager = mPagers.get(position);
				pager.initData();

			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {

			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

		// 手动加载第一页数据
		mPagers.get(0).initData();

	}



	class ContentAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return mPagers.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			BasePager pager = mPagers.get(position);
			View view = pager.mRootView;// 获取当前页面对象的布局

			// pager.initData();// 初始化数据, viewpager会默认加载下一个页面,
			// 为了节省流量和性能,不要在此处调用初始化数据的方法

			container.addView(view);

			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

	}

	// 获取新闻中心页面
	public NearbyPager getNewsCenterPager() {
		NearbyPager pager = (NearbyPager) mPagers.get(1);
		return pager;
	}

}