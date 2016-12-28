package com.wondersgroup.special.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chan on 12/14/16.
 */

public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected Context context;
    protected List<T> items;
    protected LayoutInflater inflater;

    public BaseAdapter(Context context, List<T> items) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        if (null != items) {
            this.items = items;
        } else {
            this.items = new ArrayList<>();
        }
    }

    public String getString(String src) {
        return TextUtils.isEmpty(src) ? "" : src;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public T getItem(int position) {
        return items.get(position);
    }

    public void addItems(List<T> data) {
        if (null != items && data.size() != 0) {
            items.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void addItem(T data) {
        if (null != data) {
            items.add(data);
            notifyDataSetChanged();
        }
    }

    public void removeItem(int position) {
        items.remove(position);
    }
}