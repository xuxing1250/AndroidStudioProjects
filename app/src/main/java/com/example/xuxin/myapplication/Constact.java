package com.example.xuxin.myapplication;

import com.example.xuxin.myapplication.beans.NewsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuxin on 16-11-23.
 */

public class Constact {

    public static ArrayList<NewsBean> getNewsList() {
        ArrayList<NewsBean> newsList = new ArrayList<NewsBean>();
        for(int i =0 ; i < 10 ; i++){
            NewsBean news = new NewsBean();
            news.setId(i);
            news.setNewsId(i);
            news.setCollectStatus(false);
            news.setCommentNum(i + 10);
            news.setInterestedStatus(true);
            news.setLikeStatus(true);
            news.setReadStatus(false);
            news.setNewsCategory("推荐");
            news.setNewsCategoryId(1);
            news.setSource_url("http://news.sina.com.cn/c/2014-05-05/134230063386.shtml");
            news.setTitle("可以用谷歌眼镜做的10件酷事：导航、玩游戏");
            newsList.add(news);

        }
        return newsList;
    }
}



