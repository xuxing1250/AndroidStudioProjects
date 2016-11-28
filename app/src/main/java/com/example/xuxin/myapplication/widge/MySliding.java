package com.example.xuxin.myapplication.widge;

import android.app.Activity;

import com.example.xuxin.myapplication.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * Created by xuxin on 16-11-14.
 */

public class MySliding extends SlidingMenu {

    public MySliding(Activity activity) {
        super(activity);
        setMode(SlidingMenu.LEFT_RIGHT);
        //设置滑动范围
        setTouchModeAbove(SlidingMenu.LEFT);
        setShadowWidth(600);
        setBehindOffset(200);
        setMenu(R.layout.slide_left);
        setFadeDegree(0.35f);
        setSecondaryMenu(R.layout.slide_right);
        attachToActivity(activity, SlidingMenu.RIGHT);
    }

}
