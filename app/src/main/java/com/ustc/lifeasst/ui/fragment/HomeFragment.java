package com.ustc.lifeasst.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.ustc.lifeasst.R;
import com.ustc.lifeasst.base.impl.HomePager;
import com.ustc.lifeasst.global.LifeAsstApplication;
import com.ustc.lifeasst.ui.activity.WebViewActivity;
import com.ustc.lifeasst.ui.domain.FriendViewData;
import com.ustc.lifeasst.ui.domain.GuessLikeData;
import com.ustc.lifeasst.ui.model.NewsModel;
import com.ustc.lifeasst.ui.model.NewsModelDataDetail;
import com.ustc.lifeasst.ui.view.LoadingPage.ResultState;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * 首页
 * 
 * @author Kevin
 * @date 2015-10-27
 */
public class HomeFragment extends BaseFragment {

	private static int NOAMAL = 1;
	private static int OTHER = 2;

	private Context mContext;
	private ListView listView;

	private ArrayList<NewsModelDataDetail> mNewsList  = new ArrayList<>();

	private FriendAdapter friendAdapter = new FriendAdapter();

	@SuppressLint("ValidFragment")
	public HomeFragment() {

	}

	@SuppressLint("ValidFragment")
	public HomeFragment(Context context) {
		super(context);
		mContext = context;
	}

	// 如果加载数据成功, 就回调此方法, 在主线程运行
	@Override
	public View onCreateSuccessView() {
		View view = View.inflate(mContext, R.layout.friend_pager, null);

//		initData();
		getDataFromServer();

		listView = (ListView) view.findViewById(R.id.friend_listview);
		listView.setAdapter(friendAdapter);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				NewsModelDataDetail data = mNewsList.get(i);
				Intent intent = new Intent(mContext, WebViewActivity.class);
				intent.putExtra("url", data.url);
				mContext.startActivity(intent);
			}
		});

		return view;
	}

//	public void initData() {
//		for (int i = 0; i < 20; i++) {
//			FriendViewData data = new FriendViewData();
//			int[] localImgs = {R.drawable.img1,R.drawable.img2,R.drawable.img3};
//			data.setLocalImgs(localImgs);
//			data.setTitle("情人节的冰与火之歌：玫瑰遇上冰淇淋"+i);
//			data.setSource("深夜访谈"+i);
//			data.setTime("45分钟前"+i);
//			mNewsList.add(data);
//		}
//	}

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
//						Toast.makeText(mContext, msg, Toast.LENGTH_SHORT)
//								.show();
					}
				});
	}

	protected void processData(String json) {
		// Gson: Google Json
		Gson gson = new Gson();
		NewsModel newsModel = gson.fromJson(json, NewsModel.class);
		System.out.println("解析结果:" + newsModel);

		mNewsList = newsModel.result.data;
		friendAdapter.notifyDataSetChanged();
	}


	// 运行在子线程,可以直接执行耗时网络操作
	@Override
	public ResultState onLoad() {
		// 请求网络
		return ResultState.STATE_SUCCESS;
	}


	class FriendAdapter extends BaseAdapter {

		private BitmapUtils mBitmapUtils;

		public FriendAdapter() {
			mBitmapUtils = new BitmapUtils(LifeAsstApplication.getContext());
			mBitmapUtils.configDefaultLoadingImage(R.drawable.news_pic_default);
		}

//		@Override
//		public int getViewTypeCount() {
//			return 2;
//		}
//
//		@Override
//		public int getItemViewType(int position) {
//			if (position % 3 == 0) {
//				return OTHER;
//			} else {
//				return NOAMAL;
//			}
//		}

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
			ViewHolder holder2;

//			if (getItemViewType(position) == NOAMAL) {
				if (convertView == null) {
					convertView = View.inflate(mContext, R.layout.friend_listview_item,
							null);
					holder = new ViewHolder();
					holder.ivIcon = (ImageView) convertView
							.findViewById(R.id.friend_listitem_img1);
					holder.ivIcon2 = (ImageView) convertView
							.findViewById(R.id.friend_listitem_img2);
					holder.ivIcon3 = (ImageView) convertView
							.findViewById(R.id.friend_listitem_img3);
					holder.tvTitle = (TextView) convertView
							.findViewById(R.id.friend_listitem_title);
					holder.source = (TextView) convertView
							.findViewById(R.id.friend_listitem_source);
					holder.time = (TextView) convertView
							.findViewById(R.id.friend_listitem_time);
					convertView.setTag(holder);
				} else {
					holder = (ViewHolder) convertView.getTag();
				}

				NewsModelDataDetail news = getItem(position);
				holder.tvTitle.setText(news.title);
				holder.source.setText(news.author_name);
				holder.time.setText(news.date);

				mBitmapUtils.display(holder.ivIcon, news.thumbnail_pic_s);
				mBitmapUtils.display(holder.ivIcon2, news.thumbnail_pic_s02);
				mBitmapUtils.display(holder.ivIcon3, news.thumbnail_pic_s03);


//				holder.ivIcon.setImageResource(news.getLocalImgs()[0]);
//				holder.ivIcon2.setImageResource(news.getLocalImgs()[1]);
//				holder.ivIcon3.setImageResource(news.getLocalImgs()[2]);
//			} else {
//				if (convertView == null) {
//					convertView = View.inflate(mContext, R.layout.friend_listview_other_item,
//							null);
//					holder2 = new ViewHolder();
//					holder2.ivIcon = (ImageView) convertView
//							.findViewById(R.id.friend_listotheritem_img);
//					holder2.tvTitle = (TextView) convertView
//							.findViewById(R.id.friend_listotheritem_title);
//					holder2.source = (TextView) convertView
//							.findViewById(R.id.friend_listotheritem_source);
//					holder2.time = (TextView) convertView
//							.findViewById(R.id.friend_listotheritem_time);
//					convertView.setTag(holder2);
//				} else {
//					holder2 = (ViewHolder) convertView.getTag();
//				}
//
//				FriendViewData news = getItem(position);
//				holder2.tvTitle.setText(news.getTitle());
//				holder2.source.setText(news.getSource());
//				holder2.time.setText(news.getTime());
//
//				holder2.ivIcon.setImageResource(news.getLocalImgs()[0]);
//
//			}

			return convertView;
		}

	}

	static class ViewHolder {
		public ImageView ivIcon;
		public ImageView ivIcon2;
		public ImageView ivIcon3;


		public TextView tvTitle;
		public TextView source;
		public TextView time;
	}


}
