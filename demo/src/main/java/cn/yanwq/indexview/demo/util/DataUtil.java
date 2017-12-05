package cn.yanwq.indexview.demo.util;


import com.yan.indexview.RecyclerIndex;

import java.util.ArrayList;
import java.util.List;

import cn.yanwq.indexview.demo.entity.Data;
import cn.yanwq.indexview.demo.entity.Index;

/**
 * yanweiqiang
 * 2017/11/29.
 */

public class DataUtil {

    public static <T extends RecyclerIndex.IData> List<T> getItemDataList(int size, int indexDistance) {
        List<T> itemDataList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (i % indexDistance == 0) {
                Index index = new Index();
                index.setTitle("index " + i / indexDistance);
                itemDataList.add((T) index);
                continue;
            }

            Data data = new Data();
            data.setText("data " + i);
            itemDataList.add((T) data);
        }
        return itemDataList;
    }

    public static List<Data> getDataList(int size) {
        List<Data> dataList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Data data = new Data();
            data.setText("data " + i);
            dataList.add(data);
        }
        return dataList;
    }
}
