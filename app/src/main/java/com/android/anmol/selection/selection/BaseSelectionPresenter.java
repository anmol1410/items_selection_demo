package com.android.anmol.selection.selection;

import android.content.Context;
import android.support.annotation.StringRes;

import com.android.anmol.selection.R;
import com.android.anmol.selection.utils.recycler_view_utils.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 3/4/2018.
 */
public abstract class BaseSelectionPresenter<A extends BaseRecyclerViewAdapter, I extends BaseSelectionModel> {

    private BaseSelectionView mView;

    protected BaseSelectionPresenter(BaseSelectionView baseView) {
        mView = baseView;
    }

    public final void handleCancelSelectionClick(A adapter) {
        selectDeselectAllItems(adapter, false);
        adapter.setSelectionMode(false);
        adapter.notifyDataSetChanged();
        configureSelectionMode(false, adapter.getItemCount() == 0);
    }

    public final void handleSelectOptionsClick(Context context, A adapter) {
        boolean areAllItemsSelected = areAllItemsSelected(adapter);

        selectDeselectAllItems(adapter, !areAllItemsSelected);
        adapter.notifyDataSetChanged();
        mView.setSelectionOptionsTo(areAllItemsSelected ? R.string.select_all : R.string.deselect_all);
        mView.setSelectedItemsCount(formatCount(context, getItemsSelectedCount(adapter)));
    }

    /**
     * Selects or deselects all Recycler view items.
     *
     * @param adapter     Adapter to get list of items.
     * @param setSelected true to select all items, false to deselect all of them.
     */
    private void selectDeselectAllItems(A adapter, boolean setSelected) {
        for (int x = 0; x < adapter.getItemCount(); x++) {
            adapter.setItemAt(x, getItemSelectedAs(adapter, x, setSelected));
        }
    }

    /**
     * Deselect the item at position if selected already, or vice versa otherwise.
     *
     * @param context  Context, to get String for changing the state of "SelectAll/DeselectAll" textView if all items get selected/deselected.
     * @param position position to get item to work upon.
     * @param adapter  Adapter to get the item from.
     */
    private void toggleItemSelectionAt(Context context, int position, A adapter) {
        adapter.setItemAt(position, getSelectionToggledItem(adapter, position));

        // Update the item view after toggling its selection.
        adapter.notifyItemChanged(position);

        // update "SelectAll/DeselectAll" textView.
        mView.setSelectionOptionsTo(areAllItemsSelected(adapter) ? R.string.deselect_all : R.string.select_all);

        int selectedItemsCount = getItemsSelectedCount(adapter);
        // update "Items selected" textView.
        mView.setSelectedItemsCount(formatCount(context, selectedItemsCount));

        mView.enableSelectOptions(selectedItemsCount != 0);
    }

    protected abstract I getSelectionToggledItem(A adapter, int position);

    protected abstract I getItemSelectedAs(A adapter, int position, boolean setSelected);

    /**
     * Handles item long click.
     * It turns ON the selection Mode.
     * Unhide the selection menu.
     * And select/deselect the item long clicked on.
     *
     * @param context  Context required for {@link BaseSelectionPresenter#toggleItemSelectionAt(Context, int, BaseRecyclerViewAdapter)}
     * @param adapter  Adapter to get the item from.
     * @param position position to get item to work upon.
     */
    public final void handleLongItemClick(Context context, A adapter, int position) {
        adapter.setSelectionMode(true);
        adapter.notifyDataSetChanged();
        configureSelectionMode(true, adapter.getItemCount() == 0);
        toggleItemSelectionAt(context, position, adapter);
    }

    /**
     * Handles item click.
     * And select/deselect the item clicked on only if it is in  selection mode.
     *
     * @param context  Context required for {@link BaseSelectionPresenter#toggleItemSelectionAt(Context, int, BaseRecyclerViewAdapter)}
     * @param adapter  Adapter to get the item from.
     * @param position position to get item to work upon.
     */
    public final void handleItemClick(Context context, A adapter, int position) {
        boolean isSelectionMode = adapter.getSelectionMode();
        if (isSelectionMode) {
            toggleItemSelectionAt(context, position, adapter);
        } else {
            handleOnItemClick(adapter, position);
        }
    }

    protected abstract void handleOnItemClick(A adapter, int item);

    /**
     * Format the "num items selected" accordingly.
     * e.g. it would be "1 item selected" or "3 items selected".
     *
     * @param context            {@link Context} required to get String resource value.
     * @param itemsSelectedCount Number of items selected.
     * @return Formatted String.
     */
    private String formatCount(Context context, int itemsSelectedCount) {
        @StringRes final int resId = itemsSelectedCount == 1 ? R.string.one_item_selected : R.string.mult_item_selected;
        return context.getResources().getString(resId, itemsSelectedCount);
    }

    /**
     * Checks if all items are selected.
     *
     * @param adapter Adapter to check the items from.
     * @return true, if all items are selected, false otherwise.
     */
    private boolean areAllItemsSelected(A adapter) {
        return getItemsSelectedCount(adapter) == adapter.getList().size();
    }

    /**
     * Get number of items selected.
     *
     * @param adapter Adapter to check the items from.
     * @return Number of items selected.
     */
    private int getItemsSelectedCount(A adapter) {
        return getItemsSelected(adapter).size();
    }

    private List<I> getItemsSelected(A adapter) {
        List<I> selectedItemsPos = new ArrayList<>();
        for (int x = 0; x < adapter.getItemCount(); x++) {
            I itemModel = (I) adapter.getItemAt(x);
            if (itemModel.isSelected()) {
                selectedItemsPos.add(itemModel);
            }
        }
        return selectedItemsPos;
    }

    /**
     * Delete the item which are marked as selected from the given adapter.
     *
     * @param adapter Adapter to fetch the selected items from.
     */
    public final void deleteSelectedItems(A adapter) {
        List<I> selectedItems = getItemsSelected(adapter);
        for (I itemModel : selectedItems) {
            adapter.remove(itemModel);
        }
        adapter.setSelectionMode(false);
        adapter.notifyDataSetChanged();
        configureSelectionMode(false, adapter.getItemCount() == 0);
    }

    private void configureSelectionMode(boolean showSelectionMode, boolean showNoItemError) {
        mView.setSelectionMenuVisible(showSelectionMode);
        mView.showNoItemsError(showNoItemError);
    }

    final void handleOnScreenChange(A adapter) {
        boolean isSelectionMode = adapter.getSelectionMode();
    }
}
