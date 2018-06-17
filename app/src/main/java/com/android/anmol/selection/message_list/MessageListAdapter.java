package com.android.anmol.selection.message_list;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.anmol.selection.R;
import com.android.anmol.selection.utils.recycler_view_utils.BaseRecyclerViewAdapter;
import com.android.anmol.selection.utils.recycler_view_utils.RecyclerViewClickListener;

import java.util.List;

/**
 * Created on 3/3/2018.
 */
class MessageListAdapter extends BaseRecyclerViewAdapter<MessagesDataModel> {
    private Activity mActivity;
    private List<MessagesDataModel> mMessages;
    private RecyclerViewClickListener mListener;

    MessageListAdapter(Activity activity, List<MessagesDataModel> messages, RecyclerViewClickListener listener) {
        super(messages);
        mActivity = activity;
        mMessages = messages;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(mActivity).inflate(R.layout.item_row_layout, parent, false);
        return new MessageListViewHolder(inflatedView, mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MessagesDataModel messagesDataModel = mMessages.get(position);
        ((MessageListViewHolder) holder).update(mActivity, messagesDataModel, getSelectionMode());
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }
}
