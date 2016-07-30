package io.agileninja.vmrecycler;


import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public abstract class ViewModelCollection implements List<BaseViewModel> {

    private List<BaseViewModel> mViewModels = new ArrayList<>();
    private VMObserver mObserver;

    void setObserver(VMObserver observer) {
        mObserver = observer;
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
        if (added && mObserver != null) {
            mObserver.onInserted(mViewModels.size() - 1);
        }
        return added;
    }

    @Override
    public boolean remove(Object o) {
        //noinspection SuspiciousMethodCalls
        int index = mViewModels.indexOf(o);
        boolean removed = mViewModels.remove(o);
        if (removed && mObserver != null) {
            mObserver.onRemoved(index);
        }
        return removed;
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> collection) {
        return mViewModels.containsAll(collection);
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends BaseViewModel> collection) {
        int index = mViewModels.size();
        boolean added = mViewModels.addAll(collection);
        if (added && mObserver != null) {
            mObserver.onInsertedRange(index, collection.size());
        }
        return added;
    }

    @Override
    public boolean addAll(int i, @NonNull Collection<? extends BaseViewModel> collection) {
        boolean added = mViewModels.addAll(i, collection);
        if (added && mObserver != null) {
            mObserver.onInsertedRange(i, collection.size());
        }
        return added;
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> collection) {
        boolean removed = mViewModels.removeAll(collection);
        if (removed && mObserver != null) {
            mObserver.onDataSetChange();
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
            mObserver.onChange(i);
        }
        return mViewModels.set(i, baseViewModel);
    }

    @Override
    public void add(int i, BaseViewModel baseViewModel) {
        mViewModels.add(i, baseViewModel);
        if (mObserver != null) {
            mObserver.onInserted(i);
        }
    }

    @Override
    public BaseViewModel remove(int i) {
        if (mObserver != null) {
            mObserver.onRemoved(i);
        }
        return mViewModels.remove(i);
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
