package com.android.anmol.selection.message_list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.anmol.selection.R;
import com.android.anmol.selection.base.RecyclerViewSpaceItemDecorator;
import com.android.anmol.selection.selection.BaseSelectionFragment;
import com.android.anmol.selection.utils.recycler_view_utils.RecyclerViewclickCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created on 3/2/2018.
 */

public class MessageListFragment extends BaseSelectionFragment<MessageListPresenter, MessageListAdapter> {

    @BindView(R.id.rl_selection)
    RelativeLayout mRlSelection;

    @BindView(R.id.cl_selection_delete)
    ConstraintLayout mClSelectionDelete;

    @BindView(R.id.tv_select_options)
    TextView mTvSelectOptions;

    @BindView(R.id.tv_selected_items_count)
    TextView mTvSelectedItemsCount;

    @BindView(R.id.rv_messages)
    RecyclerView mRvMessages;

    @BindView(R.id.iv_delete_selected_items)
    ImageView mIvDelete;

    @BindView(R.id.iv_mark_as_read_items)
    ImageView mIvMarkAsRead;

    public static Fragment newInstance() {
        return new MessageListFragment();
    }

    @NonNull
    @Override
    protected int getLayout() {
        return R.layout.messages_fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        mPresenter = new MessageListPresenter(new MessageListViewCallback());
        setupRecyclerView();
        mPresenter.retrieveMessages();
    }

    @OnClick({R.id.tv_cancel_selection_mode, R.id.tv_select_options, R.id.iv_delete_selected_items, R.id.iv_mark_as_read_items})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel_selection_mode:
                mPresenter.handleCancelSelectionClick(getAdapter());
                break;

            case R.id.tv_select_options:
                mPresenter.handleSelectOptionsClick(getContext(), getAdapter());
                break;

            case R.id.iv_delete_selected_items:
                mPresenter.deleteSelectedItems(getAdapter());
                break;

            case R.id.iv_mark_as_read_items:
                mPresenter.markSelectedMsgsAsRead(getAdapter());
                break;
        }
    }

    private void setupRecyclerView() {
        mRvMessages.setHasFixedSize(true);
        final LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRvMessages.setLayoutManager(layoutManager);
        mRvMessages.setItemAnimator(new DefaultItemAnimator());
        mRvMessages.addItemDecoration(new RecyclerViewSpaceItemDecorator(getContext(), R.dimen.rv_divider));
    }

    protected MessageListAdapter getAdapter() {
        return (MessageListAdapter) mRvMessages.getAdapter();
    }

    private void showMessages(List<MessagesDataModel> messages) {
        MessageListAdapter adapter = new MessageListAdapter(getActivity(), messages, new RecyclerViewClickCallback());
        mRvMessages.setAdapter(adapter);
    }

    private class MessageListViewCallback implements MessageListView {
        @Override
        public void populateMessages(List<MessagesDataModel> messages) {
            showMessages(messages);
        }

        @Override
        public void setSelectionMenuVisible(boolean setVisible) {
            mRlSelection.setVisibility(setVisible ? View.VISIBLE : View.GONE);
            mClSelectionDelete.setVisibility(setVisible ? View.VISIBLE : View.GONE);
        }

        @Override
        public void setSelectedItemsCount(String itemsCountText) {
            mTvSelectedItemsCount.setText(itemsCountText);
        }

        @Override
        public void setSelectionOptionsTo(@StringRes int selectionOption) {
            mTvSelectOptions.setText(selectionOption);
        }

        @Override
        public void enableSelectOptions(boolean setEnabled) {
            mIvDelete.setEnabled(setEnabled);
            mIvMarkAsRead.setEnabled(setEnabled);
        }

        @Override
        public void showNoItemsError(boolean showNoItemError) {

        }
    }

    private class RecyclerViewClickCallback extends RecyclerViewclickCallback {
        @Override
        public void onItemClick(int position) {
            mPresenter.handleItemClick(getContext(), getAdapter(), position);
        }

        @Override
        public void onLongItemClick(int position) {
            mPresenter.handleLongItemClick(getContext(), getAdapter(), position);
        }
    }
}
