package io.agileninja.vmrecycler;

public interface VMObserver {
    void onChange(int index);
    void onInserted(int index, BaseViewModel viewModel);
    void onRemoved(int index, BaseViewModel viewModel);
    void onDataSetChange(ViewModelCollection viewModelCollection);
}
