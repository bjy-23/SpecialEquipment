package com.wondersgroup.special.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by bjy on 2016/12/2.
 */

public abstract class RecyclerBaseHolder extends RecyclerView.ViewHolder {
    private Context context;
    private RecyclerBaseAdapter adapter;


    public RecyclerBaseHolder(Context context,View itemView) {
        super(itemView);
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setAdapter(RecyclerBaseAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * 必须继承
     */
    public abstract void createView(View v);
}
