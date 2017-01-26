package com.atguigu.shoujiyingyin.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;

import com.atguigu.shoujiyingyin.R;
import com.atguigu.shoujiyingyin.base.BaseFragment;
import com.atguigu.shoujiyingyin.fragment.LoaclAudioFragment;
import com.atguigu.shoujiyingyin.fragment.LoaclVideoFragment;
import com.atguigu.shoujiyingyin.fragment.netAudioFragment;
import com.atguigu.shoujiyingyin.fragment.netVideoFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG=MainActivity.class.getSimpleName();

    private RadioGroup rg_main;

    private ArrayList<BaseFragment>fragments;

    private int position;

    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        rg_main= (RadioGroup) findViewById(R.id.rg_main);



        initFragment();

        //设置RadioGroup的监听
        initListener();
    }

    private void initListener() {


        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.rb_local_video:
                        position=0;
                        break;
                    case R.id.rb_local_audio:
                        position=1;
                        break;
                    case R.id.rb_net_audio:
                        position=2;
                        break;
                    case R.id.rb_net_video:
                        position=3;
                        break;
                }

                Fragment currentFragment=fragments.get(position);

                switchFragment (currentFragment);
            }
        });

        //默认选中本地视频
        rg_main.check(R.id.rb_local_video);

    }

    private void switchFragment(Fragment currentFragment) {



       if(tempFragment!=currentFragment){

           /**
            * 开启事务
            */
           FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
           /**
            * 切换
            */
           if(currentFragment!=null){
               //如果没有添加就添加
               if(!currentFragment.isAdded()){

                   //把之前的隐藏
                   if(tempFragment!=null){

                       ft.hide(tempFragment);
                   }
                   //如果添加了就直接显示
                   ft.add(R.id.fl_mainc_content,currentFragment);


               }else {

                   //把之前的隐藏
                   if(tempFragment!=null){

                       ft.hide(tempFragment);
                   }

                  //如果添加了就直接显示
                   ft.show(currentFragment);




               }

               //最后一步，提交事务
             ft.commit();


           }

           tempFragment=currentFragment;

       }

    }

    private void initFragment() {

        fragments=new ArrayList<>();
        fragments.add(new LoaclVideoFragment());
        fragments.add(new LoaclAudioFragment());
        fragments.add(new netAudioFragment());
        fragments.add(new netVideoFragment());

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }
}
