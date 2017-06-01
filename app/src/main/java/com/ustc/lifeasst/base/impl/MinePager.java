package com.ustc.lifeasst.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.ustc.lifeasst.R;
import com.ustc.lifeasst.base.BasePager;
import com.ustc.lifeasst.ui.domain.GuessLikeData;
import com.ustc.lifeasst.ui.domain.MineListData;

import java.util.ArrayList;

/**
 * 设置
 * 
 * @author Kevin
 * @date 2015-10-18
 */
public class MinePager extends BasePager {

	private static int NORAMAL = 1;
	private static int OTHER = 2;

	private ArrayList<MineListData> mNewsList = new ArrayList<MineListData>();

	private ListView listView;

	private TextView usernameTv;
	private TextView signTv;

	public MinePager(Activity activity) {
		super(activity);
	}

	@Override
	public View onCreateSuccessView() {
		View view = View.inflate(mActivity, R.layout.mine_pager, null);

		tvTitle.setText("我的");

		btnMenu.setVisibility(View.GONE);

		String[] rowNames = {"我的钱包","好友去哪","我的收藏","我的分享","客服中心","关于我们"};
		int[] iconNames = {R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,
				R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};

		for (int i = 0; i < rowNames.length; i++) {
			MineListData listData = new MineListData();
			listData.setIconName(iconNames[i]);
			listData.setRowName(rowNames[i]);
			mNewsList.add(listData);
		}

		listView = (ListView) view.findViewById(R.id.mine_listview);
		listView.setAdapter(new MineAdapter());

		View headerView = View.inflate(mActivity,R.layout.mine_listview_header,null);
		usernameTv = (TextView) headerView.findViewById(R.id.mine_username_tv);
		signTv = (TextView) headerView.findViewById(R.id.mine_sign_tv);

		listView.addHeaderView(headerView);

		return view;
	}

	class MineAdapter extends BaseAdapter {

		private BitmapUtils mBitmapUtils;

		public MineAdapter() {
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
//			if (position == 0 || position == 3) return OTHER;
//			else return NORAMAL;
//		}

		@Override
		public int getCount() {

			return mNewsList.size();
		}

		@Override
		public MineListData getItem(int position) {
			return mNewsList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			OtherViewHolder holder2;

//			if (getItemViewType(position) == NORAMAL) {
				if (convertView == null) {
					convertView = View.inflate(mActivity, R.layout.mine_listview_item,
							null);
					holder = new ViewHolder();
					holder.ivIcon = (ImageView) convertView
							.findViewById(R.id.mine_listview_icon);
					holder.tvTitle = (TextView) convertView
							.findViewById(R.id.mine_listview_name);
					convertView.setTag(holder);
				} else {
					holder = (ViewHolder) convertView.getTag();
				}

				MineListData row = getItem(position);
				holder.tvTitle.setText(row.getRowName());

//			mBitmapUtils.display(holder.ivIcon, news.listimage);

				holder.ivIcon.setImageResource(row.getIconName());
//			} else {
//				if (convertView == null) {
//					convertView = View.inflate(mActivity, R.layout.mine_listview_other_item,
//							null);
//					holder2 = new OtherViewHolder();
//					convertView.setTag(holder2);
//				} else {
//					holder2 = (OtherViewHolder) convertView.getTag();
//				}
//
//			}



			return convertView;
		}

	}


	static class ViewHolder {
		public ImageView ivIcon;
		public TextView tvTitle;
	}

	static class OtherViewHolder {

	}

}
