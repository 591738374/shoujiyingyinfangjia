package com.atguigu.shoujiyingyin.fragment;

import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.atguigu.shoujiyingyin.R;
import com.atguigu.shoujiyingyin.adapter.LocalVideoAdapter;
import com.atguigu.shoujiyingyin.base.BaseFragment;
import com.atguigu.shoujiyingyin.bean.MediaItem;
import com.atguigu.shoujiyingyin.utils.Utils;

import java.util.ArrayList;

/**
 * Created by 潘鹏程 on 2017/1/25.
 * 微信:13212223597
 * QQ:591738374
 * 作用:
 */

public class LoaclVideoFragment extends BaseFragment {

    private static final String TAG=LoaclVideoFragment.class.getSimpleName();

    private ListView lv_local_video;

    private TextView tv_local_no_video;

    private ArrayList<MediaItem> mediaItems;

    private  LocalVideoAdapter adapter;

    private Utils utils;


    @Override
    public View initview() {
        Log.e("TAG","本地视频被初始化了....");

        View view= LayoutInflater.from(mContext).inflate(R.layout.fragment_local_video,null);

        lv_local_video= (ListView) view.findViewById(R.id.lv_cocal_video);
        tv_local_no_video= (TextView) view.findViewById(R.id.tv_local_no_video);
        return view;
    }

    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(mediaItems!=null&&mediaItems.size()>0){
               //有数据

                //隐藏文本
                tv_local_no_video.setVisibility(View.GONE);
                //设置适配器
                adapter=new LocalVideoAdapter(mContext,mediaItems);
                lv_local_video.setAdapter(adapter);

            }else {
                 //没有数据
                //显示文本
                tv_local_no_video.setVisibility(View.VISIBLE);

            }
        }
    };



    @Override
    public void initData() {
        super.initData();
        Log.e("TAG","本地视频数据被初始化了....");
        getDataFromLoacl();

    }

    private void getDataFromLoacl() {


        //在子线程加载数据
        new Thread(){

            @Override
            public void run() {
                super.run();

                mediaItems=new ArrayList<MediaItem>();

                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                String[] objects={
                     MediaStore.Video.Media.DISPLAY_NAME,
                     MediaStore.Video.Media.DURATION,
                     MediaStore.Video.Media.SIZE,
                     MediaStore.Video.Media.DATA,
                     MediaStore.Video.Media.ARTIST,

                };
               Cursor cursor= mContext.getContentResolver().query(uri,objects,null,null,null);
                if(cursor!=null){
                    while(cursor.moveToNext()){



                       String name= cursor.getString(0);

                      long duration=cursor.getLong(1);

                      long size=cursor.getLong(2);

                      String data=cursor.getString(3);

                        String artist=cursor.getString(4);

                        MediaItem mediaItem=new MediaItem(name,duration,size,data,artist);

                        //添加到集合中
                        mediaItems.add(mediaItem);

                    }
                    //
                    handler.sendEmptyMessage(0);

                }
               //发消息


            }
        }.start();

    }
}
