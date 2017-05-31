package com.ustc.lifeasst.base.impl;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.ustc.lifeasst.R;
import com.ustc.lifeasst.base.BasePager;
import com.ustc.lifeasst.ui.activity.BusLineSearchDemo;
import com.ustc.lifeasst.ui.activity.LocationDemo;
import com.ustc.lifeasst.ui.activity.PoiSearchDemo;
import com.ustc.lifeasst.ui.activity.RoutePlanDemo;

/**
 * 首页
 *
 * @author Kevin
 * @date 2015-10-18
 */
public class NearbyPager extends BasePager {

	private MapView mMapView;
	private BaiduMap baiduMap;

	private Button routePlanButton;
	private Button fieldQueryButton;
	private Button busQueryButton;

	public NearbyPager(Activity activity) {
		super(activity);
	}

	@Override
	public View onCreateSuccessView() {
		View view = View.inflate(mActivity, R.layout.nearby_pager, null);

		tvTitle.setText("周边信息");

		//获取地图控件引用
		mMapView = (MapView) view.findViewById(R.id.bmapView);
		baiduMap = mMapView.getMap();
		baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
		baiduMap.setBaiduHeatMapEnabled(false);

		routePlanButton = (Button) view.findViewById(R.id.routeplanButton);
		fieldQueryButton = (Button) view.findViewById(R.id.fieldqueryButton);
		busQueryButton = (Button) view.findViewById(R.id.busqueryButton);

		routePlanButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(mActivity, RoutePlanDemo.class);
				mActivity.startActivity(intent);
			}
		});

		fieldQueryButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(mActivity, LocationDemo.class);
				mActivity.startActivity(intent);
			}
		});

		busQueryButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(mActivity, BusLineSearchDemo.class);
				mActivity.startActivity(intent);
			}
		});

		return view;
	}
}
