package com.example.xuxin.myapplication.Fragment;


import android.support.v4.app.Fragment;
import android.view.View;

import com.example.xuxin.myapplication.activity.BaseActivity;

/**
 * Created by xuxin on 16-11-23.
 */

public abstract class BaseTabFragment extends Fragment {
    abstract void setViewVisiable(View v);
    abstract void setViewInvisiable(View v);

    protected abstract void replaceFragment();
}
