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
        if (viewModelCollection != null) {
            viewModelCollection.setObserver(this);
            mViewModelCollection = viewModelCollection;
            viewTypes.clear();
            for (BaseViewModel baseViewModel : mViewModelCollection) {
                viewTypes.put(baseViewModel.getClass(), baseViewModel);
            }
            notifyDataSetChanged();
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewModel viewModel = viewTypes.valueAt(viewType);
        if (viewModel != null) {
            return viewModel.getViewHolder(parent);
        } else {
            return null;
        }
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
    public void onInserted(int index, BaseViewModel viewModel) {
        viewTypes.put(viewModel.getClass(), viewModel);
        notifyItemInserted(index);
    }

    @Override
    public void onRemoved(int index, BaseViewModel viewModel) {
        if (viewModel != null) {
            boolean noLongerExists = true;
            for (BaseViewModel model : mViewModelCollection) {
                if (model.getClass().equals(viewModel.getClass())) {
                    noLongerExists = false;
                }
            }

            if (noLongerExists) {
                viewTypes.remove(viewModel.getClass());
            }
            notifyItemRemoved(index);
        }
    }

    @Override
    public void onDataSetChange(ViewModelCollection viewModelCollection) {
        setViewModelCollection(viewModelCollection);
    }
}
