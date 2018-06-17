package com.android.anmol.selection.selection;

import android.support.annotation.StringRes;

/**
 * Created on 3/4/2018.
 */

public interface BaseSelectionView {

    void setSelectionMenuVisible(boolean setVisible);

    void setSelectedItemsCount(String itemsCountText);

    void setSelectionOptionsTo(@StringRes int selectionOption);

    void enableSelectOptions(boolean setEnabled);

    void showNoItemsError(boolean showNoItemError);
}
