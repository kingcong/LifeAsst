package com.ustc.lifeasst.ui.model;

import java.util.ArrayList;

/**
 * Created by kingcong on 2017/5/31.
 */

public class NewsModel {

    public String reason;
    public  NewsModelData result;

    public class NewsModelData {
        public String stat;
        public ArrayList<NewsModelDataDetail> data;
    }

}
