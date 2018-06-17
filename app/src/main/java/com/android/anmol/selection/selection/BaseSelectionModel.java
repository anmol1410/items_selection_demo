package com.android.anmol.selection.selection;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created on 3/5/2018.
 */

public class BaseSelectionModel implements Parcelable {

    protected String mFriendName;

    protected boolean mIsSelected;

    protected BaseSelectionModel(Builder<?> builder) {
        mFriendName = builder.mFriendName;
        mIsSelected = builder.mIsSelected;
    }

    protected BaseSelectionModel(Parcel in) {
        mFriendName = in.readString();
        mIsSelected = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mFriendName);
        dest.writeByte((byte) (mIsSelected ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BaseSelectionModel> CREATOR = new Creator<BaseSelectionModel>() {
        @Override
        public BaseSelectionModel createFromParcel(Parcel in) {
            return new BaseSelectionModel(in);
        }

        @Override
        public BaseSelectionModel[] newArray(int size) {
            return new BaseSelectionModel[size];
        }
    };

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(BaseSelectionModel copy) {
        Builder builder = new Builder();
        builder.mFriendName = copy.getFriendName();
        builder.mIsSelected = copy.isSelected();
        return builder;
    }

    public String getFriendName() {
        return mFriendName;
    }

    public boolean isSelected() {
        return mIsSelected;
    }

    public static class Builder<T extends Builder<T>> {
        private String mFriendName;
        private boolean mIsSelected;

        protected Builder() {
        }

        public T friendName(String val) {
            mFriendName = val;
            return (T) this;
        }

        public T isSelected(boolean val) {
            mIsSelected = val;
            return (T) this;
        }

        public BaseSelectionModel build() {
            return new BaseSelectionModel(this);
        }
    }
}
