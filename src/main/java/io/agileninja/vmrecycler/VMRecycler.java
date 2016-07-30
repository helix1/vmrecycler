package io.agileninja.vmrecycler;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;


public class VMRecycler extends RecyclerView {

    private VMAdapter mAdapter;

    public VMRecycler(Context context) {
        super(context);
        init();
    }

    public VMRecycler(Context context,
                      @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VMRecycler(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mAdapter = new VMAdapter();
        setAdapter(mAdapter);
        setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void setViewModelCollection(ViewModelCollection collection) {
        mAdapter.setViewModelCollection(collection);
    }
}
