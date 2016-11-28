package com.example.xuxin.myapplication.adapter;




import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by xuxin on 16-11-22.
 */

public class TitleFragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<HashMap<String, Object>> mList;
    private FragmentManager mManager;

    public TitleFragmentAdapter(FragmentManager fm, ArrayList<HashMap<String, Object>> list) {
        super(fm);
        mList = list;
        mManager = fm;
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment) mList.get(position).get("fragment");
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return (CharSequence) mList.get(position).get("title");
    }
}
