package com.android.anmol.selection.utils.recycler_view_utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created on 3/3/2018.
 */

public class RecyclerViewLongItemClickListener implements View.OnLongClickListener {

    private RecyclerViewClickListener mListener;
    private RecyclerView.ViewHolder mViewHolder;

    public RecyclerViewLongItemClickListener(RecyclerView.ViewHolder viewHolder, RecyclerViewClickListener listener) {
        mListener = listener;
        mViewHolder = viewHolder;
    }

    @Override
    public boolean onLongClick(View view) {
        mListener.onLongItemClick(mViewHolder.getLayoutPosition());
        return true;
    }
}