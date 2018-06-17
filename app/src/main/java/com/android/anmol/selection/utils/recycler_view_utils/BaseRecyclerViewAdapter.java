package com.android.anmol.selection.utils.recycler_view_utils;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created on 3/3/2018.
 */

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter {

    private List<T> mList;
    private boolean mIsSelectionMode;

    public BaseRecyclerViewAdapter(List<T> list) {
        mList = list;
    }

    public List<T> getList() {
        return mList;
    }

    public T getItemAt(int pos) {
        return mList.get(pos);
    }

    public void setItemAt(int pos, T data) {
        mList.set(pos, data);
    }

    public void removeItemAt(int pos) {
        mList.remove(pos);
    }

    public void remove(T item) {
        mList.remove(item);
    }

    public void setSelectionMode(boolean modeStatus) {
        mIsSelectionMode = modeStatus;
    }

    public boolean getSelectionMode() {
        return mIsSelectionMode;
    }

}
