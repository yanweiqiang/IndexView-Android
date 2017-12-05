package com.yan.indexview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.SparseIntArray;

import java.util.ArrayList;
import java.util.List;

/**
 * yanweiqiang
 * 2017/11/24.
 */

public class RecyclerIndexBar<T> extends RecyclerView {
    private final String tag = RecyclerIndexBar.class.getSimpleName();

    private List<T> indexList;
    private SparseIntArray posMap;//list to index
    private SparseIntArray reversePosMap;//index to list
    private RecyclerView recyclerView;
    private RecyclerIndex recyclerIndex;

    public RecyclerIndexBar(Context context) {
        super(context);
    }

    public RecyclerIndexBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        indexList = new ArrayList<>();
        posMap = new SparseIntArray();
        reversePosMap = new SparseIntArray();
    }

    public List<T> transformData(List<T> list) {
        indexList.clear();
        posMap.clear();
        reversePosMap.clear();

        if (list == null) {
            return null;
        }

        for (int i = 0; i < list.size(); i++) {
            T data = list.get(i);
            if (data instanceof RecyclerIndex.IIndex) {
                indexList.add(data);
                posMap.put(i, indexList.size() - 1);
                reversePosMap.put(indexList.size() - 1, i);
            }
        }

        return indexList;
    }

    public List<T> getIndexList() {
        return indexList;
    }

    public SparseIntArray getPosMap() {
        return posMap;
    }

    public SparseIntArray getReversePosMap() {
        return reversePosMap;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public RecyclerIndex getRecyclerIndex() {
        return recyclerIndex;
    }

    public void attach(RecyclerView recyclerView, RecyclerIndex<T> recyclerIndex) {
        this.recyclerView = recyclerView;
        this.recyclerIndex = recyclerIndex;
        recyclerIndex.setOnIndexChangeListener(new RecyclerIndex.OnIndexChangeListener<T>() {
            @Override
            public void onChange(int position, T data) {
                int barPos = getPosMap().get(position);
                setCurrentIndexPosition(barPos);
            }
        });
    }

    /**
     * Combine with {@link RecyclerIndex} for auto select in {@link RecyclerIndexBar}
     *
     * @param position
     */
    public void setCurrentIndexPosition(int position) {
        ((IAdapter) getAdapter()).onCurrentIndexPosition(position);
    }

    public interface IAdapter {
        void onCurrentIndexPosition(int position);
    }

    public void scrollToMappingPosition(int position) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) RecyclerIndexBar.this.recyclerView.getLayoutManager();
        layoutManager.scrollToPositionWithOffset(RecyclerIndexBar.this.getReversePosMap().get(position), 0);
    }
}
