package com.ustc.lifeasst.ui.domain;

/**
 * Created by kingcong on 2017/2/14.
 */

public class FriendViewData {

    private String title;
    private String source;
    private String time;
    private String[] imgs;
    private int[] localImgs;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String[] getImgs() {
        return imgs;
    }

    public void setImgs(String[] imgs) {
        this.imgs = imgs;
    }

    public int[] getLocalImgs() {
        return localImgs;
    }

    public void setLocalImgs(int[] localImgs) {
        this.localImgs = localImgs;
    }
}
