package com.example.xuxin.myapplication.adapter;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.xuxin.myapplication.R;
import com.example.xuxin.myapplication.Utils.Options;
import com.example.xuxin.myapplication.beans.NewsBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuxin on 16-11-23.
 */

public class NewsAdapter extends BaseAdapter {
    public static final String TAG = "NewsAdapter";

    private Activity mActivity;
    private ArrayList<NewsBean> mList;
    private LayoutInflater mInflater;
    private ImageLoader mLoader;
    private DisplayImageOptions mOptions;
    private PopupMenu mPopupMenu;

    private List<Integer> mPositions;
    private List<String> mSections;

    public NewsAdapter(Activity mActivity, ArrayList<NewsBean> mList) {
        Log.d(TAG, "Contract");
        this.mActivity = mActivity;
        this.mList = mList;
        mInflater = LayoutInflater.from(mActivity);
        mOptions = Options.getListOptions();
        
    }


    private void initPopmeunu() {}

    private void initDateHead() {}

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int i) {
        if (mList != null && mList.size() != 0) {
            return mList.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        View itemView = view;
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            itemView = mInflater.inflate(R.layout.news_list_item, null);
            viewHolder.mContent = (TextView) itemView.findViewById(R.id.title_content);
            itemView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) itemView.getTag();
        }

        //填充数据
        NewsBean newsBean = mList.get(i);
        viewHolder.mContent.setText(newsBean.getTitle());

        return itemView;
    }
    private static class ViewHolder {
        TextView mContent;
    }
}
