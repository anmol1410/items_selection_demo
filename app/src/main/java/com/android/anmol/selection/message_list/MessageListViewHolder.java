package com.android.anmol.selection.message_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.anmol.selection.R;
import com.android.anmol.selection.utils.recycler_view_utils.RecyclerViewClickListener;
import com.android.anmol.selection.utils.recycler_view_utils.RecyclerViewItemClickListener;
import com.android.anmol.selection.utils.recycler_view_utils.RecyclerViewLongItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 3/3/2018.
 */

class MessageListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.rl_item_container)
    RelativeLayout mRvContainer;

    @BindView(R.id.iv_msg_read_status_row_item)
    ImageView mIvMsgReadStatus;

    @BindView(R.id.cb_row_item)
    CheckBox mCbFriendItem;

    @BindView(R.id.tv_friend_row_item)
    TextView mTvFriendName;

    @BindView(R.id.tv_item_details)
    TextView mTvCallDetail;

    @BindView(R.id.tv_msg_time_row_item)
    TextView mTvMsgTime;

    MessageListViewHolder(View view, RecyclerViewClickListener listener) {
        super(view);
        ButterKnife.bind(this, view);

        view.setOnClickListener(new RecyclerViewItemClickListener(this, listener));
        view.setOnLongClickListener(new RecyclerViewLongItemClickListener(this, listener));
    }

    void update(Context context, MessagesDataModel messagesDataModel, boolean selectionMode) {
        mIvMsgReadStatus.setVisibility(messagesDataModel.isMsgReadStatus() ? View.INVISIBLE : View.VISIBLE);

        mTvMsgTime.setText(messagesDataModel.getMsgTime());
        mTvFriendName.setText(messagesDataModel.getFriendName());
        mTvCallDetail.setText(messagesDataModel.getMsgText());

        setSelected(context, messagesDataModel.isSelected(), selectionMode);
    }

    private void setSelected(Context context, boolean isSelected, boolean isSelectionMode) {
        mCbFriendItem.setVisibility(isSelectionMode ? View.VISIBLE : View.GONE);
        mCbFriendItem.setChecked(isSelected);

        mRvContainer.setBackgroundColor(context.getResources().getColor(isSelected && isSelectionMode ? R.color.colorPrimary : R.color.colorPrimaryLight));
        mTvFriendName.setTextColor(context.getResources().getColor(isSelected && isSelectionMode ? R.color.colorGray : R.color.colorDark));
    }
}
