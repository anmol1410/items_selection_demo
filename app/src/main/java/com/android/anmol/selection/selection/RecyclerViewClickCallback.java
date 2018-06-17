package com.android.anmol.selection.selection;

import android.content.Context;

import com.android.anmol.selection.utils.recycler_view_utils.BaseRecyclerViewAdapter;
import com.android.anmol.selection.utils.recycler_view_utils.RecyclerViewClickListener;

/**
 * Created on 10-03-2018.
 */

public class RecyclerViewClickCallback<A extends BaseRecyclerViewAdapter, P extends BaseSelectionPresenter> implements RecyclerViewClickListener {

    private Context mContext;
    private A mAdapter;
    private P mPresenter;

    public RecyclerViewClickCallback(Context context, A adapter, P presenter) {
        mContext = context;
        mAdapter = adapter;
        mPresenter = presenter;
    }

    @Override
    public void onItemClick(int position) {
        mPresenter.handleItemClick(mContext, mAdapter, position);
    }

    @Override
    public void onLongItemClick(int position) {
        mPresenter.handleLongItemClick(mContext, mAdapter, position);
    }
}