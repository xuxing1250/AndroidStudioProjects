package com.example.xuxin.myapplication.Utils;

import android.content.Context;

import com.example.xuxin.myapplication.R;

/**
 * Created by xuxin on 16-11-21.
 */

public class NewsUtils {
    public static Context mContext = MyApplication.getContextObj();
    public static final int RES_ID[] = {
            R.drawable.ic_drawer_search_normal,
            R.drawable.ic_drawer_favorite_normal,
            R.drawable.ic_drawer_message_normal,
            R.drawable.ic_drawer_offline_normal,
            R.drawable.left_drawer_activity,
            R.drawable.ic_drawer_setting_normal,
            R.drawable.ic_drawer_feedback_normal,
            R.drawable.ic_drawer_appstore_normal
    };
    public static final int TITLE_ID[] = {
            R.string.search_drawer,
            R.string.collect_drawer,
            R.string.message_drawer,
            R.string.unline_drawer,
            R.string.campaign_drawer,
            R.string.setting_drawer,
            R.string.replay_drawer,
            R.string.app_drawer
    };
}
