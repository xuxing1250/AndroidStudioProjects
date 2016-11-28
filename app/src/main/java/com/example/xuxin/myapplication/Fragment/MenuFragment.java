package com.example.xuxin.myapplication.Fragment;



import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;


import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.xuxin.myapplication.activity.MainActivity;
import com.example.xuxin.myapplication.R;
import com.example.xuxin.myapplication.Utils.NewsUtils;
import com.example.xuxin.myapplication.adapter.MenuAdpter;
import com.example.xuxin.myapplication.widge.MenuListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xuxin on 16-11-15.
 */

public class MenuFragment extends Fragment {
    public static final String TAG = "MenuFragment";
    private MenuListView mList;
    private MenuAdpter mAdapter;
    private ArrayAdapter  mAdapters;
    private MainActivity mActivity;
    private ArrayList<HashMap<String, Object>> mData;
    private List<String> mTitles;
    private List<Bitmap> mIcons;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = (MainActivity) getActivity();
        View view = inflater.inflate(R.layout.sliding_fragment_layout, null);
        mList = (MenuListView) view.findViewById(R.id.menu_listview);
        initAdapter();
        mList.addHeaderView(LayoutInflater.from(mActivity).inflate(R.layout.menu_header_view, null));

        return view;
    }

    public void initAdapter() {
        mData = new ArrayList<>();
        mTitles = new ArrayList<>();
        mIcons = new ArrayList<>();
        initInfo();

        for (int i = 0; i < mTitles.size(); i++) {
            HashMap<String, Object> hash = new HashMap<String, Object>();
            hash.put("info", mTitles.get(i));
            hash.put("icon", mIcons.get(i));
            mData.add(hash);
        }

        mAdapter = new MenuAdpter(mActivity, mData);
            mList.setAdapter(mAdapter);


    }
    private void initInfo() {
        int id[] = NewsUtils.RES_ID;
        for (int i = 0; i < id.length; i++) {
            Bitmap bitmap = getBitmapIcon(id[i]);
            mIcons.add(bitmap);
        }
        int title[] = NewsUtils.TITLE_ID;
        for (int i = 0; i < title.length; i++) {
            String s = getTitleStr(title[i]);
            mTitles.add(s);
        }
    }

    private Bitmap getBitmapIcon(int id) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getActivity().getResources().getDrawable(id);
        Bitmap bitmap = bitmapDrawable.getBitmap();
        return bitmap;
    }
    private String getTitleStr(int id) {
        String s = getActivity().getResources().getString(id);
        return s;

    }
}
