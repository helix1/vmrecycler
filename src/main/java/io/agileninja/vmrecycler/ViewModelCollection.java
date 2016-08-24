package io.agileninja.vmrecycler;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public abstract class ViewModelCollection implements List<BaseViewModel> {

    private List<BaseViewModel> mViewModels = new ArrayList<>();
    private ViewTypes mViewTypes = new ViewTypes();
    private VMObserver mObserver;

    void setObserver(VMObserver observer) {
        mObserver = observer;
    }

    public int getViewType(int position) {
        return mViewTypes.getViewType(mViewModels.get(position));
    }

    public BaseViewModel getViewModelFromType(int viewType) {
        Class c = mViewTypes.keyAt(viewType);
        for (int i = 0; i < size(); i++) {
            if (get(i).getClass().equals(c)) {
                return get(i);
            }
        }
        return null;
    }

    @Override
    public int size() {
        return mViewModels.size();
    }

    @Override
    public boolean isEmpty() {
        return mViewModels.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return mViewModels.contains(o);
    }

    @NonNull
    @Override
    public Iterator<BaseViewModel> iterator() {
        return mViewModels.iterator();
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return mViewModels.toArray();
    }

    @NonNull
    @Override
    public <T> T[] toArray(@NonNull T[] ts) {
        //noinspection SuspiciousToArrayCall
        return mViewModels.toArray(ts);
    }

    @Override
    public boolean add(BaseViewModel baseViewModel) {
        boolean added = mViewModels.add(baseViewModel);
        if (added) {
            mViewTypes.add(baseViewModel);
            if (mObserver != null) {
                mObserver.onItemInserted(mViewModels.size() - 1);
            }
        }
        return added;
    }

    @Override
    public boolean remove(Object o) {
        if (!(o instanceof BaseViewModel)) {
            return false;
        }
        int index = mViewModels.indexOf(o);
        boolean removed = mViewModels.remove(o);
        if (removed) {
            mViewTypes.remove((BaseViewModel) o);
            if (mObserver != null) {
                mObserver.onItemRemoved(index);
            }
        }
        return removed;
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> collection) {
        return mViewModels.containsAll(collection);
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends BaseViewModel> collection) {
        boolean added = false;
        boolean add;
        for (Object o : collection) {
            add = add((BaseViewModel) o);
            added = add || added;
        }
        return added;
    }

    @Override
    public boolean addAll(int i, @NonNull Collection<? extends BaseViewModel> collection) {
        try {
            for (Object o : collection) {
                add(i++, (BaseViewModel) o);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> collection) {
        boolean removed = false;
        for (Object o : collection) {
            if (!(o instanceof BaseViewModel)) {
                continue;
            }
            if (contains(o)) {
                boolean remove = remove(o);
                removed = remove || removed;
            }
        }
        return removed;
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> collection) {
        return mViewModels.retainAll(collection);
    }

    @Override
    public void clear() {
        mViewModels.clear();
        if (mObserver != null) {
            mObserver.onDataSetChange();
        }
    }

    @Override
    public BaseViewModel get(int i) {
        return mViewModels.get(i);
    }

    @Override
    public BaseViewModel set(int i, BaseViewModel baseViewModel) {
        if (mObserver != null) {
            mObserver.onItemChanged(i);
        }
        return mViewModels.set(i, baseViewModel);
    }

    @Override
    public void add(int i, BaseViewModel baseViewModel) {
        mViewModels.add(i, baseViewModel);
        mViewTypes.add(baseViewModel);
        if (mObserver != null) {
            mObserver.onItemInserted(i);
        }
    }

    @Override
    public BaseViewModel remove(int i) {
        BaseViewModel viewModel = mViewModels.remove(i);
        mViewTypes.remove(viewModel);
        if (mObserver != null) {
            mObserver.onItemRemoved(i);
        }
        return viewModel;
    }

    @Override
    public int indexOf(Object o) {
        return mViewModels.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return mViewModels.lastIndexOf(o);
    }

    @Override
    public ListIterator<BaseViewModel> listIterator() {
        return mViewModels.listIterator();
    }

    @NonNull
    @Override
    public ListIterator<BaseViewModel> listIterator(int i) {
        return mViewModels.listIterator(i);
    }

    @NonNull
    @Override
    public List<BaseViewModel> subList(int i, int i1) {
        return mViewModels.subList(i, i1);
    }
}
