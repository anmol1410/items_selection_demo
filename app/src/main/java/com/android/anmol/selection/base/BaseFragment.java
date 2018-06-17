package com.android.anmol.selection.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created on 3/2/2018.
 */
public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;

    @Nullable
    @Override
    public final View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View mInflatedView = inflater.inflate(getLayout(), container, false);
        unbinder = ButterKnife.bind(this, mInflatedView);
        return mInflatedView;
    }

    /**
     * @return the layout id of view to be inflated.
     */
    @LayoutRes
    @NonNull
    protected abstract int getLayout();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected boolean isActivityAlive() {
        return getActivity() == null || getActivity().isFinishing();
    }

    final protected void finish() {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}