package com.atguigu.shoujiyingyin.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.shoujiyingyin.base.BaseFragment;

/**
 * Created by 潘鹏程 on 2017/1/25.
 * 微信:13212223597
 * QQ:591738374
 * 作用:
 */

public class netAudioFragment extends BaseFragment {

    private TextView textView;
    @Override
    public View initview() {
        Log.e("TAG","网络音频被初始化了....");
        textView=new TextView(mContext);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG","网络音频数据被初始化了....");
        textView.setText("网络音频");
    }
}
