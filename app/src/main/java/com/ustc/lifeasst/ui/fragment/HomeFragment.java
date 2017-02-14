package com.ustc.lifeasst.ui.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.ustc.lifeasst.R;
import com.ustc.lifeasst.base.impl.HomePager;
import com.ustc.lifeasst.ui.domain.FriendViewData;
import com.ustc.lifeasst.ui.domain.GuessLikeData;
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

	private ArrayList<FriendViewData> mNewsList  = new ArrayList<FriendViewData>();


	public HomeFragment() {

	}

	public HomeFragment(Context context) {
		super(context);
		mContext = context;
	}

	// 如果加载数据成功, 就回调此方法, 在主线程运行
	@Override
	public View onCreateSuccessView() {
		View view = View.inflate(mContext, R.layout.friend_pager, null);

		initData();

		listView = (ListView) view.findViewById(R.id.friend_listview);
		listView.setAdapter(new FriendAdapter());

		return view;
	}

	public void initData() {
		for (int i = 0; i < 20; i++) {
			FriendViewData data = new FriendViewData();
			int[] localImgs = {R.drawable.img1,R.drawable.img2,R.drawable.img3};
			data.setLocalImgs(localImgs);
			data.setTitle("情人节的冰与火之歌：玫瑰遇上冰淇淋"+i);
			data.setSource("深夜访谈"+i);
			data.setTime("45分钟前"+i);
			mNewsList.add(data);
		}
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
//			mBitmapUtils = new BitmapUtils(mActivity);
//			mBitmapUtils.configDefaultLoadingImage(R.drawable.news_pic_default);
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
		public FriendViewData getItem(int position) {
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

				FriendViewData news = getItem(position);
				holder.tvTitle.setText(news.getTitle());
				holder.source.setText(news.getSource());
				holder.time.setText(news.getTime());

				holder.ivIcon.setImageResource(news.getLocalImgs()[0]);
				holder.ivIcon2.setImageResource(news.getLocalImgs()[1]);
				holder.ivIcon3.setImageResource(news.getLocalImgs()[2]);
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
