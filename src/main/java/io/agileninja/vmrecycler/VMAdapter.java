package io.agileninja.vmrecycler;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public class VMAdapter extends RecyclerView.Adapter<BaseViewHolder> implements VMObserver {

    private ViewModelCollection mViewModelCollection;

    public VMAdapter() {
    }

    public void setViewModelCollection(ViewModelCollection viewModelCollection) {
        if (viewModelCollection == null) {
            if (mViewModelCollection == null) {
                return;
            }
            mViewModelCollection.clear();
            return;
        }
        viewModelCollection.setObserver(this);
        mViewModelCollection = viewModelCollection;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewModel viewModel = mViewModelCollection.getViewModelFromType(viewType);
        return viewModel == null ? null : viewModel.getViewHolder(parent);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bind(mViewModelCollection.get(position));
    }

    @Override
    public int getItemCount() {
        return mViewModelCollection == null ? 0 : mViewModelCollection.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mViewModelCollection.getViewType(position);
    }

    @Override
    public void onItemChanged(int index) {
        notifyItemChanged(index);
    }

    @Override
    public void onItemInserted(int index) {
        notifyItemInserted(index);
    }

    @Override
    public void onItemRemoved(int index) {
        notifyItemRemoved(index);
    }

    @Override
    public void onDataSetChange() {
        notifyDataSetChanged();
    }
}
