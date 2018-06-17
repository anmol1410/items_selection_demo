package com.android.anmol.selection.message_list;

import android.os.Parcel;
import android.os.Parcelable;

import com.android.anmol.selection.selection.BaseSelectionModel;

/**
 * Created on 3/3/2018.
 */

public class MessagesDataModel extends BaseSelectionModel implements Parcelable {

    private String mMsgTime;
    private boolean mMsgReadStatus;
    private String mMsgText;

    protected MessagesDataModel(Parcel in) {
        super(in);
        mMsgTime = in.readString();
        mMsgReadStatus = in.readByte() != 0;
        mMsgText = in.readString();
    }

    public static final Creator<MessagesDataModel> CREATOR = new Creator<MessagesDataModel>() {
        @Override
        public MessagesDataModel createFromParcel(Parcel in) {
            return new MessagesDataModel(in);
        }

        @Override
        public MessagesDataModel[] newArray(int size) {
            return new MessagesDataModel[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessagesDataModel)) return false;

        MessagesDataModel that = (MessagesDataModel) o;

        return mFriendName != null ? mFriendName.equals(that.mFriendName) : that.mFriendName == null;
    }

    @Override
    public int hashCode() {
        return mFriendName != null ? mFriendName.hashCode() : 0;
    }

    public String getMsgTime() {
        return mMsgTime;
    }

    public boolean isMsgReadStatus() {
        return mMsgReadStatus;
    }

    public String getMsgText() {
        return mMsgText;
    }

    private MessagesDataModel(Builder builder) {
        super(builder);
        mMsgTime = builder.mMsgTime;
        mMsgReadStatus = builder.mMsgReadStatus;
        mMsgText = builder.mMsgText;
        mFriendName = builder.mFriendName;
        mIsSelected = builder.mIsSelected;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(MessagesDataModel copy) {
        Builder builder = new Builder();
        builder.mMsgTime = copy.getMsgTime();
        builder.mMsgReadStatus = copy.isMsgReadStatus();
        builder.mMsgText = copy.getMsgText();
        builder.mFriendName = copy.getFriendName();
        builder.mIsSelected = copy.isSelected();
        return builder;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mMsgTime);
        parcel.writeByte((byte) (mMsgReadStatus ? 1 : 0));
        parcel.writeString(mMsgText);
    }


    public static final class Builder extends BaseSelectionModel.Builder<MessagesDataModel.Builder> {
        private String mMsgTime;
        private boolean mMsgReadStatus;
        private String mMsgText;
        private String mFriendName;
        private boolean mIsSelected;

        private Builder() {
        }

        public Builder msgTime(String val) {
            mMsgTime = val;
            return this;
        }

        public Builder msgReadStatus(boolean val) {
            mMsgReadStatus = val;
            return this;
        }

        public Builder msgText(String val) {
            mMsgText = val;
            return this;
        }

        public Builder friendName(String val) {
            mFriendName = val;
            return this;
        }

        public Builder isSelected(boolean val) {
            mIsSelected = val;
            return this;
        }

        public MessagesDataModel build() {
            return new MessagesDataModel(this);
        }
    }
}
