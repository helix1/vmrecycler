package io.agileninja.vmrecycler;


import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public class VMAdapter extends RecyclerView.Adapter<BaseViewHolder> implements VMObserver {

    private ViewModelCollection mViewModelCollection;

    private ArrayMap<Class, BaseViewModel> viewTypes = new ArrayMap<>();

    public VMAdapter() {
    }

    public void setViewModelCollection(ViewModelCollection viewModelCollection) {
        mViewModelCollection = viewModelCollection;
        viewModelCollection.setObserver(this);
        viewTypes.clear();
        for (BaseViewModel baseViewModel : mViewModelCollection) {
            viewTypes.put(baseViewModel.getClass(), baseViewModel);
        }
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return viewTypes.valueAt(viewType).getViewHolder(parent);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bind(mViewModelCollection.get(position));
    }

    @Override
    public int getItemCount() {
        if (mViewModelCollection != null) {
            return mViewModelCollection.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return viewTypes.indexOfKey(mViewModelCollection.get(position).getClass());
    }

    @Override
    public void onChange(int index) {
        notifyItemChanged(index);
    }

    @Override
    public void onInserted(int index) {
        notifyItemInserted(index);
    }

    @Override
    public void onInsertedRange(int fromIndex, int itemCount) {
        notifyItemRangeInserted(fromIndex, itemCount);
    }

    @Override
    public void onRemoved(int index) {
        notifyItemRemoved(index);
    }

    @Override
    public void onRemovedRange(int fromIndex, int itemCount) {
        notifyItemRangeRemoved(fromIndex, itemCount);
    }

    @Override
    public void onDataSetChange() {
        notifyDataSetChanged();
    }
}
