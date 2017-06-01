package com.ustc.lifeasst.base.impl;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.ustc.lifeasst.R;
import com.ustc.lifeasst.base.BasePager;
import com.ustc.lifeasst.ui.activity.WebViewActivity;
import com.ustc.lifeasst.ui.domain.GuessLikeData;
import com.ustc.lifeasst.ui.model.NewsModel;
import com.ustc.lifeasst.ui.model.NewsModelDataDetail;

import java.util.ArrayList;

/**
 * 首页
 * 
 * @author Kevin
 * @date 2015-10-18
 */
public class HomePager extends BasePager implements View.OnClickListener{

	private static final String[] strs = new String[] {
		"first", "second", "third", "fourth", "fifth","first", "second", "third", "fourth", "fifth","first", "second", "third", "fourth", "fifth"
	};

	private String[] urls = new String[] {
			"https://m.meishij.net/html5/",
			"http://m.meilishuo.com/?tabnum=0",
			"http://m.qyer.com/",
			"http://m.budejie.com/?frm=www",
			"http://www.lifeasst.cn"
	};

	private RollPagerView mRollViewPager;

	private ArrayList<NewsModelDataDetail> mNewsList = new ArrayList<>();

	private Activity mAciivity;

	private ListView listView;

	private NewsAdapter newsAdapter = new NewsAdapter();

	public HomePager(Activity activity) {
		super(activity);
		mAciivity = activity;
	}

	@Override
	public View onCreateSuccessView() {

		View view = View.inflate(mActivity, R.layout.home_pager, null);
		View mHeaderView = View.inflate(mActivity, R.layout.homepager_listview_header,
				null);

		Button eatButton = (Button) mHeaderView.findViewById(R.id.homepager_eat_button);
		Button drinkButton = (Button) mHeaderView.findViewById(R.id.homepager_drink_button);
		Button playButton = (Button) mHeaderView.findViewById(R.id.homepager_play_button);
		Button happyButton = (Button) mHeaderView.findViewById(R.id.homepager_happy_button);

		eatButton.setOnClickListener(this);
		drinkButton.setOnClickListener(this);
		playButton.setOnClickListener(this);
		happyButton.setOnClickListener(this);

//		flContent.addView(view);

		RelativeLayout relativeLayout = (RelativeLayout) mHeaderView.findViewById(R.id.homepager_header_all);
		relativeLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(mAciivity, WebViewActivity.class);
				intent.putExtra("url", "http://baidu.weather.com.cn/mweather/101190405.shtml");
				mAciivity.startActivity(intent);
			}
		});

		// 修改页面标题
		tvTitle.setText("高教区助手");

		// 隐藏菜单按钮
		btnMenu.setVisibility(View.GONE);

//		SearchView searchView = (SearchView) view.findViewById(R.id.homepager_searchbar);
		listView = (ListView) view.findViewById(R.id.homepager_listview);
		listView.addHeaderView(mHeaderView);

//		listView.setAdapter(new ArrayAdapter<String>(mActivity,
//				android.R.layout.simple_list_item_1, strs));

		getDataFromServer();

		listView.setAdapter(newsAdapter);
		listView.setOnItemClickListener(new ListView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//				System.out.println("i="+i);
				NewsModelDataDetail data = mNewsList.get(i-1);
				Intent intent = new Intent(mAciivity, WebViewActivity.class);
				intent.putExtra("url", data.url);
				mAciivity.startActivity(intent);
			}
		});


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

	public void getDataFromServer() {

		System.out.println("start request");

		String host = "http://toutiao-ali.juheapi.com";
		String path = "/toutiao/index";
		String appcode = "feec4bfa9ec145c48e23e380057a3bfc";
		String url = host + path;

		HttpUtils utils = new HttpUtils();

		RequestParams params = new RequestParams();
		params.addHeader("Authorization", "APPCODE " + appcode);
		params.addQueryStringParameter("type","keji");

		utils.send(HttpRequest.HttpMethod.GET, url,params,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// 请求成功
						String result = responseInfo.result;// 获取服务器返回结果
						System.out.println("服务器返回结果:" + result);

						// JsonObject, Gson
						processData(result);
//
//						// 写缓存
//						CacheUtils.setCache(GlobalConstants.CATEGORY_URL,
//								result, mActivity);
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// 请求失败
						error.printStackTrace();
						Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT)
								.show();
					}
				});
	}

	protected void processData(String json) {
		// Gson: Google Json
		Gson gson = new Gson();
		NewsModel newsModel = gson.fromJson(json, NewsModel.class);
		System.out.println("解析结果:" + newsModel);

		mNewsList = newsModel.result.data;
		newsAdapter.notifyDataSetChanged();
	}

	@Override
	public void onClick(View view) {
		String url = null;
		switch (view.getId()) {
			case R.id.homepager_eat_button:
				url = urls[0];
				break;
			case R.id.homepager_drink_button:
				url = urls[1];
				break;
			case R.id.homepager_play_button:
				url = urls[2];
				break;
			case R.id.homepager_happy_button:
				url = urls[3];
				break;
			default:
				url = urls[4];
				break;
		}

		Intent intent = new Intent(mAciivity, WebViewActivity.class);
		intent.putExtra("url", url);
		mAciivity.startActivity(intent);
	}

	class NewsAdapter extends BaseAdapter {

		private BitmapUtils mBitmapUtils;

		public NewsAdapter() {
			mBitmapUtils = new BitmapUtils(mActivity);
			mBitmapUtils.configDefaultLoadingImage(R.drawable.news_pic_default);
		}

		@Override
		public int getCount() {
			return mNewsList.size();
		}

		@Override
		public NewsModelDataDetail getItem(int position) {
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

			NewsModelDataDetail news = getItem(position);
			holder.tvTitle.setText(news.title);
			holder.tvDate.setText(news.date);

			mBitmapUtils.display(holder.ivIcon, news.thumbnail_pic_s);

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
