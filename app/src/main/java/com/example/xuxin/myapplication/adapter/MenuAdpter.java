package com.example.xuxin.myapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xuxin.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by xuxin on 16-11-14.
 */

public class MenuAdpter extends BaseAdapter {
    private ArrayList<HashMap<String, Object>> mData;
    private LayoutInflater mInflater;
    private Context mContext;

    public MenuAdpter(Context context, ArrayList<HashMap<String, Object>> data) {
        mData = data;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.menu_list_item, null);
            viewHolder.mIcon = (ImageView) view.findViewById(R.id.menu_icon);
            viewHolder.mTitle = (TextView) view.findViewById(R.id.menu_title);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.mTitle.setText((String) mData.get(i).get("info"));
        viewHolder.mIcon.setImageBitmap((Bitmap) mData.get(i).get("icon"));
        return view;
    }

    public final class ViewHolder {
        ImageView mIcon;
        TextView mTitle;
    }
}
