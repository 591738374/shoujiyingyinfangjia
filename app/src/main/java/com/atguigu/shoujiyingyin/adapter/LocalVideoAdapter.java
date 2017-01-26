package com.atguigu.shoujiyingyin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.shoujiyingyin.R;
import com.atguigu.shoujiyingyin.bean.MediaItem;
import com.atguigu.shoujiyingyin.utils.Utils;

import java.util.ArrayList;

/**
 * Created by 潘鹏程 on 2017/1/26.
 * 微信:13212223597
 * QQ:591738374
 * 作用:
 */

public class LocalVideoAdapter extends BaseAdapter{

    private final Context mContext;
    private final ArrayList<MediaItem> mediaItems;

    private Utils utils;
    public LocalVideoAdapter(Context context, ArrayList<MediaItem>mediaItems){

        utils=new Utils();
        this.mContext=context;
        this.mediaItems=mediaItems;
    }


    @Override
    public int getCount() {
        return mediaItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView==null){

            convertView=View.inflate(mContext, R.layout.item_local_video,null);
            viewHolder=new ViewHolder();
            viewHolder.iv_icon= (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_size= (TextView) convertView.findViewById(R.id.tv_size);
            viewHolder.tv_duration= (TextView) convertView.findViewById(R.id.tv_duration);

            convertView.setTag(viewHolder);
        }else {

            viewHolder= (ViewHolder) convertView.getTag();
        }
        //绑定数据
        //根据位置得到对应的数据
        MediaItem mediaItem=mediaItems.get(position);
        viewHolder.tv_name.setText(mediaItem.getName());
        viewHolder.tv_size.setText(android.text.format.Formatter.formatFileSize(mContext,mediaItem.getSize()));

        viewHolder.tv_duration.setText(utils.stringForTime((int) mediaItem.getDuration()));
        return convertView;

    }


    static class  ViewHolder{
        ImageView iv_icon;
        TextView tv_name;
        TextView tv_size;
        TextView tv_duration;

    }
}
