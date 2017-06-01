package com.ustc.lifeasst.ui.fragment;

import com.ustc.lifeasst.R;
import com.ustc.lifeasst.ui.activity.WebViewActivity;
import com.ustc.lifeasst.ui.view.LoadingPage.ResultState;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

/**
 * 推荐
 * @author Kevin
 * @date 2015-10-27
 */
public class RecommendFragment extends BaseFragment {

	private WebView mWebView;

	private String baseurl= "http://go.uc.cn/page/life/life#!/meituan";

	@SuppressLint("ValidFragment")
	public RecommendFragment() {

	}

	@SuppressLint("ValidFragment")
	public RecommendFragment(Context context) {
		super(context);
		mContext = context;
	}

	@Override
	public View onCreateSuccessView() {
		View view = View.inflate(mContext, R.layout.fragment_recommend_friend, null);

		mWebView = (WebView) view.findViewById(R.id.wv_news_detail_webview);
		mWebView.loadUrl(baseurl);
		WebSettings settings = mWebView.getSettings();
		settings.setBuiltInZoomControls(true);// 显示缩放按钮(wap网页不支持)
		settings.setUseWideViewPort(true);// 支持双击缩放(wap网页不支持)
		settings.setJavaScriptEnabled(true);// 支持js功能
		settings.setDomStorageEnabled(true);

		mWebView.setWebViewClient(new WebViewClient() {

			// 所有链接跳转会走此方法
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				System.out.println("跳转链接:" + url);
				if (url.contains(baseurl)) {
					view.loadUrl(url);// 在跳转链接时强制在当前webview中加载
				} else {
					Intent intent = new Intent(mContext, WebViewActivity.class);
					intent.putExtra("url", url);
					mContext.startActivity(intent);
				}
				return true;
			}

		});

		mWebView.setWebChromeClient(new WebChromeClient() {

			//配置权限（同样在WebChromeClient中实现）
			@Override
			public void onGeolocationPermissionsShowPrompt(String origin,
														   GeolocationPermissions.Callback callback) {
				callback.invoke(origin, true, false);
				super.onGeolocationPermissionsShowPrompt(origin, callback);
			}
		});



		return view;
	}

	@Override
	public ResultState onLoad() {
		// TODO Auto-generated method stub
		return null;
	}

}
