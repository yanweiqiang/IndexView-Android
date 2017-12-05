package cn.yanwq.indexview.demo.entity;

import com.yan.indexview.RecyclerIndex;

/**
 * yanweiqiang
 * 2017/12/4.
 */

public class Data implements RecyclerIndex.IData {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
