package com.wondersgroup.special.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

/**
 * Created by root on 11/17/16.
 */

public class SVListView extends LinearLayout {
    private OnItemClickListener listener;

    public SVListView(Context context) {
        super(context);
        setOrientation(VERTICAL);
    }

    public SVListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    @SuppressLint("InflateParams")
    public void setAdapter(BaseAdapter adapter) {
        removeAllViews();
        int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            final View item = adapter.getView(i, null, null);
            final int position = i;
            item.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(item, position);
                    }
                }
            });
            addView(item);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
}
