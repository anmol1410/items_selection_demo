package com.android.anmol.selection.message_list;

import com.android.anmol.selection.selection.BaseSelectionPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 3/2/2018.
 */

class MessageListPresenter extends BaseSelectionPresenter<MessageListAdapter, MessagesDataModel> {

    private MessageListView mView;

    MessageListPresenter(MessageListView view) {
        super(view);
        mView = view;
    }

    void retrieveMessages() {
        List<MessagesDataModel> friends = new ArrayList<>();

        // Fetch the data from data source,  e.g. remote or local like server or db
        // for simplicity the data is hard coded here.
        friends.add(MessagesDataModel.newBuilder().friendName("one").msgReadStatus(true).msgTime("10:09AM").msgText("Android is a software package and linux based operating system for mobile devices such as tablet computers and smartphones.").build());
        friends.add(MessagesDataModel.newBuilder().friendName("two").msgReadStatus(false).msgTime("11:09AM").msgText("It is developed by Google and later the OHA (Open Handset Alliance). Java language is mainly used to write the android code even though other languages can be used.").build());
        friends.add(MessagesDataModel.newBuilder().friendName("three").msgReadStatus(true).msgTime("8:09AM").msgText("The goal of android project is to create a successful real-world product that improves the mobile experience for end users.").build());
        friends.add(MessagesDataModel.newBuilder().friendName("four").msgReadStatus(true).msgTime("2:09AM").msgText("There are many code names of android such as Lollipop, Kitkat, Jelly Bean, Ice cream Sandwich, Froyo, Ecliar, Donut etc which is covered in next page.").build());
        friends.add(MessagesDataModel.newBuilder().friendName("five").msgReadStatus(false).msgTime("15:09AM").msgText("There are many android applications in the market.").build());
        friends.add(MessagesDataModel.newBuilder().friendName("apple").msgReadStatus(true).msgTime("10:09AM").msgText("Android is a software package and linux based operating system for mobile devices such as tablet computers and smartphones.").build());
        friends.add(MessagesDataModel.newBuilder().friendName("bat").msgReadStatus(false).msgTime("11:09AM").msgText("It is developed by Google and later the OHA (Open Handset Alliance). Java language is mainly used to write the android code even though other languages can be used.").build());
        friends.add(MessagesDataModel.newBuilder().friendName("cat").msgReadStatus(true).msgTime("8:09AM").msgText("The goal of android project is to create a successful real-world product that improves the mobile experience for end users.").build());
        friends.add(MessagesDataModel.newBuilder().friendName("dog").msgReadStatus(true).msgTime("2:09AM").msgText("There are many code names of android such as Lollipop, Kitkat, Jelly Bean, Ice cream Sandwich, Froyo, Ecliar, Donut etc which is covered in next page.").build());
        friends.add(MessagesDataModel.newBuilder().friendName("eagle").msgReadStatus(false).msgTime("15:09AM").msgText("There are many android applications in the market.").build());
        friends.add(MessagesDataModel.newBuilder().friendName("falcon").msgReadStatus(true).msgTime("10:09AM").msgText("Android is a software package and linux based operating system for mobile devices such as tablet computers and smartphones.").build());
        friends.add(MessagesDataModel.newBuilder().friendName("giraffe").msgReadStatus(false).msgTime("11:09AM").msgText("It is developed by Google and later the OHA (Open Handset Alliance). Java language is mainly used to write the android code even though other languages can be used.").build());
        friends.add(MessagesDataModel.newBuilder().friendName("hen").msgReadStatus(true).msgTime("8:09AM").msgText("The goal of android project is to create a successful real-world product that improves the mobile experience for end users.").build());
        friends.add(MessagesDataModel.newBuilder().friendName("insect").msgReadStatus(true).msgTime("2:09AM").msgText("There are many code names of android such as Lollipop, Kitkat, Jelly Bean, Ice cream Sandwich, Froyo, Ecliar, Donut etc which is covered in next page.").build());
        friends.add(MessagesDataModel.newBuilder().friendName("jaguar").msgReadStatus(false).msgTime("15:09AM").msgText("There are many android applications in the market.").build());

        mView.populateMessages(friends);
    }

    @Override
    protected MessagesDataModel getSelectionToggledItem(MessageListAdapter adapter, int position) {
        MessagesDataModel itemModel = adapter.getItemAt(position);
        return MessagesDataModel.newBuilder(itemModel).isSelected(!itemModel.isSelected()).build();
    }

    @Override
    protected MessagesDataModel getItemSelectedAs(MessageListAdapter adapter, int position, boolean setSelected) {
        MessagesDataModel model = adapter.getItemAt(position);
        return MessagesDataModel.newBuilder(model).isSelected(setSelected).build();
    }

    @Override
    protected void handleOnItemClick(MessageListAdapter adapter, int item) {
    }

    private List<Integer> getSelectedUnreadMsgsPos(MessageListAdapter adapter) {
        List<Integer> selectedItemsPos = new ArrayList<>();
        int itemCount = adapter.getItemCount();
        for (int x = 0; x < itemCount; x++) {
            MessagesDataModel msgDataModel = adapter.getItemAt(x);
            if (msgDataModel.isSelected() && !msgDataModel.isMsgReadStatus()) {
                selectedItemsPos.add(x);
            }
        }
        return selectedItemsPos;
    }

    /**
     * It gets all Unread Messages, mark them as read, and refreshes the UI.
     *
     * @param adapter Adapter to check the items from.
     */
    void markSelectedMsgsAsRead(MessageListAdapter adapter) {
        List<Integer> selectedItems = getSelectedUnreadMsgsPos(adapter);

        for (Integer pos : selectedItems) {
            MessagesDataModel changedMsgModel = MessagesDataModel.newBuilder((MessagesDataModel) adapter.getItemAt(pos)).msgReadStatus(true).build();
            adapter.setItemAt(pos, changedMsgModel);
        }
        handleCancelSelectionClick(adapter);
    }
}
