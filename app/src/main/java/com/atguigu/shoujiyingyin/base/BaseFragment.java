package com.atguigu.shoujiyingyin.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 潘鹏程 on 2017/1/25.
 * 微信:13212223597
 * QQ:591738374
 * 作用:
 */

public abstract class BaseFragment extends Fragment {


    public Context mContext;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getActivity();
    }

    /**
     * 当创建view的时候回掉
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initview();
    }

    /**
     * 抽象类，强制让孩子实现，实现自己特有的UI
     * @return
     */
    public   abstract  View initview();

    /**
     * 当Activity创建好的时候回调
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 初始化数据用的，当子类需要互联网请求得到数据的时候，重写该方法
     */
    public   void initData(){


    }
}
