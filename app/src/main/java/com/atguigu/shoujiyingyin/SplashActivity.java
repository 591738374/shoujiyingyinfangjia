package com.atguigu.shoujiyingyin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;

import com.atguigu.shoujiyingyin.activity.MainActivity;

public class SplashActivity extends Activity {

    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //两秒延迟进入页面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //主线程
                startMainActivity();
            }
        },2000);
    }

    private boolean isStartMain=false;

    private void  startMainActivity(){

//        if(!isStartMain){
//
//            isStartMain=true;

            startActivity(new Intent(this,MainActivity.class));

//            //关闭当前页面
            finish();
//        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        startMainActivity();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
