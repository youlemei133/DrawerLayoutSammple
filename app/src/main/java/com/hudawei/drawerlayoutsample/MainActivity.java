package com.hudawei.drawerlayoutsample;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private DrawerLayout.DrawerListener drawerListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
        //添加回调
        drawerListener=new DrawerLayout.DrawerListener() {
            //侧滑栏滑动距离的百分比
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                log("slideOffset:"+slideOffset);
            }
            //从完全关闭状态到完全打开时调用
            @Override
            public void onDrawerOpened(View drawerView) {
                log("onDrawerOpened");
            }
            //从完全打开状态到完全关闭时调用
            @Override
            public void onDrawerClosed(View drawerView) {
                log("onDrawerClosed");
            }
            //只要状态发生改变就调用
            @Override
            public void onDrawerStateChanged(int newState) {
                log("onDrawerStateChanged");
            }
        };
        drawerLayout.addDrawerListener(drawerListener);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void drawerClick(View view){
        //关闭侧滑栏，Gravity.START代表左侧滑栏  Gravity.END代表右侧滑栏
        drawerLayout.closeDrawer(Gravity.START);
        //判断侧滑栏是打开还是关闭状态
        log("START isDrawerOpen:"+drawerLayout.isDrawerOpen(Gravity.START));
        log("END isDrawerOpen:"+drawerLayout.isDrawerOpen(Gravity.END));
        //侧滑栏是否可见,不一定是完全打开或关闭状态
        log("START isDrawerVisible:"+drawerLayout.isDrawerVisible(Gravity.START));
        log("END isDrawerVisible:"+drawerLayout.isDrawerVisible(Gravity.END));
        //移除指定的监听器
        drawerLayout.removeDrawerListener(drawerListener);
        //设置侧滑模式：
        // 1.LOCK_MODE_LOCKED_CLOSED，关闭侧滑栏，不允许侧滑栏打开
        // 2.LOCK_MODE_LOCKED_OPEN，打开侧滑栏，不允许侧滑栏关闭
        // 3.LOCK_MODE_UNLOCKED，不做任何限制
        // 4.LOCK_MODE_UNDEFINED，和LOCK_MODE_UNLOCKED一样
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED);


        //设置侧滑栏边缘的阴影图片，不起作用
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow,Gravity.START);
        //给指定的侧滑栏设置标题，不会显示
        drawerLayout.setDrawerTitle(Gravity.START,"侧滑栏标题");
        //设置侧滑栏打开时，蒙板的颜色。不起作用
        drawerLayout.setScrimColor(0x0000FFFF);
        // 设置状态栏颜色，不起作用
        drawerLayout.setStatusBarBackground(new ColorDrawable(0x00FF00));
    }


    public void log(String message){
        Log.e(this.getClass().getSimpleName(),message);
    }


}
