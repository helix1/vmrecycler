package io.agileninja.vmrecycler;

import android.support.v4.util.ArrayMap;

public class ViewTypes extends ArrayMap<Class, Integer> {

    public void add(BaseViewModel viewModel) {
        Integer count = get(viewModel.getClass());
        put(viewModel.getClass(), count == null ? 1 : count + 1);
    }

    public void remove(BaseViewModel viewModel) {
        Integer count = get(viewModel.getClass());
        if (count != null) {
            count--;
            if (count.equals(0)) {
                remove(viewModel.getClass());
            } else {
                put(viewModel.getClass(), count);
            }
        }
    }

    public int getViewType(BaseViewModel viewModel) {
        return indexOfKey(viewModel.getClass());
    }
}
