package com.example.xuxin.myapplication.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;

import com.example.xuxin.myapplication.Fragment.BaseTabFragment;
import com.example.xuxin.myapplication.Fragment.ChanelFragment;
import com.example.xuxin.myapplication.Fragment.NewsFragment;
import com.example.xuxin.myapplication.Fragment.MenuFragment;
import com.example.xuxin.myapplication.Fragment.RecommondNewsFragment;
import com.example.xuxin.myapplication.R;
import com.example.xuxin.myapplication.Utils.OkHttpGet;
import com.example.xuxin.myapplication.adapter.TitleFragmentAdapter;
import com.example.xuxin.myapplication.widge.MySliding;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends BaseActivity {
    public static final String TAG = "MainActivity_xuxin";
    public static final String MENU_FRAGMENT = "menu_fragment";
    public static final String CHANEL_FRAGMENT = "chanel_fragment";

    public static final String [] TAB_TITLE = {"推荐","热门","娱乐","体育","NBA","军事","文化","游戏"};

    private ArrayList<HashMap<String, Object>> mList = new ArrayList<>();
    private ArrayList<String> mListTitle = new ArrayList<>();

    private Toolbar mToolbar;
    private TabLayout mTableLayout;
    private ViewPager mViewPager;

    private TitleFragmentAdapter mTitleFragmentAdapter;

    private MySliding mSliding;
    private MenuFragment mMenuFragment;
    private ChanelFragment mChanelFragment;

    private boolean mDrawerShow = false;

    public class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSliding = new MySliding(this);
        initDrawerFragment(MENU_FRAGMENT);
        initDrawerFragment(CHANEL_FRAGMENT);
        OkHttpGet.getOkHttpResponse();

        MyHandler myHandle = new MyHandler();
        myHandle.sendEmptyMessage(0);

        //Toolbar
        initToolBar();
        initView();
    }

    private void initView() {
        mTableLayout = (TabLayout) findViewById(R.id.tab_fragment_title);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_fragment);
        mTableLayout.setupWithViewPager(mViewPager);
        createFragmetList();


        mTitleFragmentAdapter = new TitleFragmentAdapter(getSupportFragmentManager(), mList);
        mViewPager.setAdapter(mTitleFragmentAdapter);
        mTableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void createFragmetList() {
        BaseTabFragment fragment;
        for (int i = 0; i < TAB_TITLE.length; i++) {
            mListTitle.add(TAB_TITLE[i]);
            if (TAB_TITLE[i].equals("推荐")) {
                fragment = new RecommondNewsFragment();
            } else {
                fragment = new NewsFragment();
            }
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("title", mListTitle.get(i));
            hashMap.put("fragment", fragment);
            mList.add(hashMap);
        }
    }

    private void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.menu_toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.ic_menu_wht_24dp);
        mToolbar.setTitle(null);
        mToolbar.inflateMenu(R.menu.menu_check);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSliding != null) {
                    mSliding.showMenu();
                }
            }
        });
    }


    private void initDrawerFragment(String fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        switch (fragment) {
            case MENU_FRAGMENT:
                mMenuFragment = new MenuFragment();
                ft.replace(R.id.menu_drawer, mMenuFragment);
                break;
            case CHANEL_FRAGMENT:
                mChanelFragment = new ChanelFragment();
                break;
        }
        ft.commit();
    }

    @Override
    protected int getcontentViewId() {
        return 0;
    }

    @Override
    protected int getFragmentViewId() {
        return 0;
    }
}
