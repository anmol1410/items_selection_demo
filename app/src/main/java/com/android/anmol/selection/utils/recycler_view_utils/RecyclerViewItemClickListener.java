package com.android.anmol.selection.utils.recycler_view_utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created on 3/3/2018.
 */

public class RecyclerViewItemClickListener implements View.OnClickListener {

    private RecyclerViewClickListener mListener;
    private RecyclerView.ViewHolder mViewHolder;

    public RecyclerViewItemClickListener(RecyclerView.ViewHolder viewHolder, RecyclerViewClickListener listener) {
        mListener = listener;
        mViewHolder = viewHolder;
    }

    @Override
    public void onClick(View view) {
        mListener.onItemClick(mViewHolder.getAdapterPosition());
    }
}