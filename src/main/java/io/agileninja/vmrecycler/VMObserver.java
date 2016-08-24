package io.agileninja.vmrecycler;

public interface VMObserver {
    void onItemChanged(int index);
    void onItemInserted(int index);
    void onItemRemoved(int index);
    void onDataSetChange();
}
