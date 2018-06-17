package com.android.anmol.selection.base;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created on 13-05-2017.
 */
public class RecyclerViewSpaceItemDecorator extends RecyclerView.ItemDecoration {

    private final int mSpaceSize;

    public RecyclerViewSpaceItemDecorator(@NonNull final Context context,
                                          @DimenRes final int dimenRes) {
        mSpaceSize = context.getResources().getDimensionPixelOffset(dimenRes);
    }

    @Override
    public void getItemOffsets(final Rect outRect
            , final View view
            , final RecyclerView parent
            , final RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = mSpaceSize;
        outRect.left = mSpaceSize;
    }
}
