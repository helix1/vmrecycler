package io.agileninja.vmrecycler;


public interface VMObserver {
    void onChange(int index);
    void onInserted(int index);
    void onInsertedRange(int fromIndex, int itemCount);
    void onRemoved(int index);
    void onRemovedRange(int fromIndex, int itemCount);
    void onDataSetChange();
}
