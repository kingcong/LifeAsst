package com.ustc.lifeasst;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.ustc.lifeasst.fragment.ContentFragment;

public class MainActivity extends FragmentActivity {

    private static final String TAG_LEFT_MENU = "TAG_LEFT_MENU";
    private static final String TAG_CONTENT = "TAG_CONTENT";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题,
        // 必须在setContentView之前调用
        setContentView(R.layout.activity_main);

        initFragment();
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {

        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();// 开始事务
        // 用fragment替换帧布局;参1:帧布局容器的id;参2:是要替换的fragment;参3:标记
        transaction.replace(R.id.fl_main, new ContentFragment(), TAG_CONTENT);
        transaction.commit();// 提交事务
        // Fragment fragment =
        // fm.findFragmentByTag(TAG_LEFT_MENU);//根据标记找到对应的fragment
    }



    // 获取主页fragment对象
    public ContentFragment getContentFragment() {
        FragmentManager fm = getSupportFragmentManager();
        ContentFragment fragment = (ContentFragment) fm
                .findFragmentByTag(TAG_CONTENT);// 根据标记找到对应的fragment
        return fragment;
    }
}
