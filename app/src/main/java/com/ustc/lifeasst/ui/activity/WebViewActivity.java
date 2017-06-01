package com.ustc.lifeasst.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ustc.lifeasst.R;

public class WebViewActivity extends Activity implements View.OnClickListener{


    private TextView tvTitle;
    private LinearLayout llControl;
    private ImageButton btnBack;
    private ImageButton btnShare;
    private ImageButton btnMenu;
    private Button btnClose;
    private WebView mWebView;
    private ProgressBar pbLoading;
    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        llControl = (LinearLayout) findViewById(R.id.ll_control);
        btnBack = (ImageButton) findViewById(R.id.btn_back);
        btnShare = (ImageButton) findViewById(R.id.btn_share);
        btnMenu = (ImageButton) findViewById(R.id.btn_menu);
        mWebView = (WebView) findViewById(R.id.wv_news_detail);
        pbLoading = (ProgressBar) findViewById(R.id.pb_loading);
        btnClose = (Button) findViewById(R.id.btn_close);

        llControl.setVisibility(View.VISIBLE);
        btnBack.setVisibility(View.VISIBLE);
        btnMenu.setVisibility(View.GONE);

        btnBack.setOnClickListener(this);
        btnShare.setOnClickListener(this);
        btnClose.setOnClickListener(this);


        mUrl = getIntent().getStringExtra("url");

        // mWebView.loadUrl("http://www.itheima.com");
        mWebView.loadUrl(mUrl);

        WebSettings settings = mWebView.getSettings();
        settings.setBuiltInZoomControls(true);// 显示缩放按钮(wap网页不支持)
        settings.setUseWideViewPort(true);// 支持双击缩放(wap网页不支持)
        settings.setJavaScriptEnabled(true);// 支持js功能
        settings.setDomStorageEnabled(true);


        // //启用数据库
        settings.setDatabaseEnabled(true);
        String dir = this.getApplicationContext().getDir("database", this.MODE_PRIVATE).getPath();

        //启用地理定位
        settings.setGeolocationEnabled(true);
        //设置定位的数据库路径
        settings.setGeolocationDatabasePath(dir);

        mWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {  //表示按返回键
                        mWebView.goBack();   //后退

                        //webview.goForward();//前进
                        return true;    //已处理
                    }
                }
                return false;
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {
            // 开始加载网页
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                System.out.println("开始加载网页了");
                pbLoading.setVisibility(View.VISIBLE);


            }

            // 网页加载结束
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                System.out.println("网页加载结束");
                pbLoading.setVisibility(View.INVISIBLE);

                if (mWebView.canGoBack()) {
                    btnClose.setVisibility(View.VISIBLE);
                }
            }

            // 所有链接跳转会走此方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                System.out.println("跳转链接:" + url);
                view.loadUrl(url);// 在跳转链接时强制在当前webview中加载
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

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                // 进度发生变化
                System.out.println("进度:" + newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                // 网页标题
                System.out.println("网页标题:" + title);
                tvTitle.setText(title);
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                if (mWebView.canGoBack()) {
                    mWebView.goBack();
                } else {
                    finish();
                }
                break;

            case R.id.btn_close:
//                showShare();
                finish();
                break;

            default:
                break;
        }
    }
}
