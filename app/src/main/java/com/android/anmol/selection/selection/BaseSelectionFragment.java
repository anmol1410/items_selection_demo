package com.android.anmol.selection.selection;

import com.android.anmol.selection.base.BaseFragment;
import com.android.anmol.selection.utils.recycler_view_utils.BaseRecyclerViewAdapter;

/**
 * Created on 10-03-2018.
 */

public abstract class BaseSelectionFragment<P extends BaseSelectionPresenter, A extends BaseRecyclerViewAdapter> extends BaseFragment {

    protected P mPresenter;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isActivityAlive()) {
            return;
        }

        getActivity().invalidateOptionsMenu();
        mPresenter.handleOnScreenChange(getAdapter());
    }

    protected abstract A getAdapter();
}
