package com.android.anmol.selection.message_list;

import com.android.anmol.selection.selection.BaseSelectionView;

import java.util.List;

/**
 * Created on 3/2/2018.
 */
public interface MessageListView extends BaseSelectionView {

    void populateMessages(List<MessagesDataModel> messages);
}