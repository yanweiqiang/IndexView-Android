package cn.yanwq.indexview.demo.entity;

import com.yan.indexview.RecyclerIndex;

/**
 * yanweiqiang
 * 2017/12/4.
 */

public class Index implements RecyclerIndex.IIndex{

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
