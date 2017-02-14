package com.ustc.lifeasst.base.impl;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.lidroid.xutils.BitmapUtils;
import com.ustc.lifeasst.R;
import com.ustc.lifeasst.base.BasePager;
import com.ustc.lifeasst.ui.domain.GuessLikeData;

import java.util.ArrayList;

/**
 * 首页
 * 
 * @author Kevin
 * @date 2015-10-18
 */
public class HomePager extends BasePager {

	private static final String[] strs = new String[] {
		"first", "second", "third", "fourth", "fifth","first", "second", "third", "fourth", "fifth","first", "second", "third", "fourth", "fifth"
	};

	private RollPagerView mRollViewPager;

	private ArrayList<GuessLikeData> mNewsList;


	public HomePager(Activity activity) {
		super(activity);
	}

	@Override
	public View onCreateSuccessView() {

		View view = View.inflate(mActivity, R.layout.home_pager, null);
		View mHeaderView = View.inflate(mActivity, R.layout.homepager_listview_header,
				null);

//		flContent.addView(view);

		// 修改页面标题
		tvTitle.setText("高教区助手");

		// 隐藏菜单按钮
		btnMenu.setVisibility(View.GONE);

//		SearchView searchView = (SearchView) view.findViewById(R.id.homepager_searchbar);
		ListView listView = (ListView) view.findViewById(R.id.homepager_listview);
		listView.addHeaderView(mHeaderView);
//		listView.setAdapter(new ArrayAdapter<String>(mActivity,
//				android.R.layout.simple_list_item_1, strs));


		mNewsList = new ArrayList<GuessLikeData>();
		for (int i = 0; i < 10; i++) {
			GuessLikeData data = new GuessLikeData();
			data.setTitle("标题"+i);
			data.setListimage("图片"+i);
			data.setPubdate("当前时间"+i);
			mNewsList.add(data);
		}

		listView.setAdapter(new NewsAdapter());


		mRollViewPager = (RollPagerView) view.findViewById(R.id.roll_view_pager);

		//设置播放时间间隔
		mRollViewPager.setPlayDelay(2000);
		//设置透明度
		mRollViewPager.setAnimationDurtion(500);
		//设置适配器
		mRollViewPager.setAdapter(new TestNormalAdapter(mRollViewPager));

		//设置指示器（顺序依次）
		//自定义指示器图片
		//设置圆点指示器颜色
		//设置文字指示器
		//隐藏指示器
		//mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
		mRollViewPager.setHintView(new ColorPointHintView(mActivity, Color.YELLOW,Color.WHITE));
		//mRollViewPager.setHintView(new TextHintView(this));
		//mRollViewPager.setHintView(null);
		return view;
	}

//	@Override
//	public void initData() {
//
//		System.out.println("首页初始化啦...");

//		mLoadingPage = View.inflate(mActivity, R.layout.page_loading, null);
//		flContent.addView(mLoadingPage);

//		initFragment();

//		View view = View.inflate(mActivity, R.layout.home_pager, null);
//		View mHeaderView = View.inflate(mActivity, R.layout.homepager_listview_header,
//				null);
//
////		flContent.addView(view);
//
//		// 修改页面标题
//		tvTitle.setText("高教区助手");
//
//		// 隐藏菜单按钮
//		btnMenu.setVisibility(View.GONE);
//
////		SearchView searchView = (SearchView) view.findViewById(R.id.homepager_searchbar);
//		ListView listView = (ListView) view.findViewById(R.id.homepager_listview);
//		listView.addHeaderView(mHeaderView);
//		listView.setAdapter(new ArrayAdapter<String>(mActivity,
//				android.R.layout.simple_list_item_1, strs));
//
//
//		mRollViewPager = (RollPagerView) view.findViewById(R.id.roll_view_pager);
//
//		//设置播放时间间隔
//		mRollViewPager.setPlayDelay(2000);
//		//设置透明度
//		mRollViewPager.setAnimationDurtion(500);
//		//设置适配器
//		mRollViewPager.setAdapter(new TestNormalAdapter(mRollViewPager));
//
//		//设置指示器（顺序依次）
//		//自定义指示器图片
//		//设置圆点指示器颜色
//		//设置文字指示器
//		//隐藏指示器
//		//mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
//		mRollViewPager.setHintView(new ColorPointHintView(mActivity, Color.YELLOW,Color.WHITE));
//		//mRollViewPager.setHintView(new TextHintView(this));
//		//mRollViewPager.setHintView(null);
//	}


	class NewsAdapter extends BaseAdapter {

		private BitmapUtils mBitmapUtils;

		public NewsAdapter() {
//			mBitmapUtils = new BitmapUtils(mActivity);
//			mBitmapUtils.configDefaultLoadingImage(R.drawable.news_pic_default);
		}

		@Override
		public int getCount() {
			return mNewsList.size();
		}

		@Override
		public GuessLikeData getItem(int position) {
			return mNewsList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = View.inflate(mActivity, R.layout.list_item_home,
						null);
				holder = new ViewHolder();
				holder.ivIcon = (ImageView) convertView
						.findViewById(R.id.iv_icon);
				holder.tvTitle = (TextView) convertView
						.findViewById(R.id.tv_title);
				holder.tvDate = (TextView) convertView
						.findViewById(R.id.tv_date);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			GuessLikeData news = getItem(position);
			holder.tvTitle.setText(news.title);
			holder.tvDate.setText(news.pubdate);

//			mBitmapUtils.display(holder.ivIcon, news.listimage);

//			holder.ivIcon.setImageResource(R.drawable.image_demo);

			return convertView;
		}

	}

	static class ViewHolder {
		public ImageView ivIcon;
		public TextView tvTitle;
		public TextView tvDate;
	}


	private class TestNormalAdapter extends LoopPagerAdapter {
		private int[] imgs = {
				R.drawable.img1,
				R.drawable.img2,
				R.drawable.img3,
				R.drawable.img4,
		};

		public TestNormalAdapter(RollPagerView viewPager) {
			super(viewPager);
		}

		@Override
		public View getView(ViewGroup container, int position) {
			ImageView view = new ImageView(container.getContext());
			view.setImageResource(imgs[position]);
			view.setScaleType(ImageView.ScaleType.CENTER_CROP);
			view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
			return view;
		}

		@Override
		public int getRealCount() {
			return imgs.length;
		}


//        @Override
//        public int getCount() {
//            return imgs.length;
//        }
	}



}
