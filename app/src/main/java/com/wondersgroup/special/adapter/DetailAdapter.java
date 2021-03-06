package com.wondersgroup.special.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.archive.ArchiveDetailActivity;
import com.wondersgroup.special.entity.BaseResult;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by root on 11/22/16.
 */

public class DetailAdapter extends BaseAdapter<LinkedHashMap<String, String>, DetailAdapter.ViewHolder> {

    public DetailAdapter(Context context, List<LinkedHashMap<String, String>> items) {
        super(context, items);
    }

    @Override
    public DetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.linear_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(DetailAdapter.ViewHolder holder, int position) {
        holder.mLinearLayout.removeAllViews();
        int length = items.get(position).size();
        Iterator ite = items.get(position).keySet().iterator();
        if (length <= 4) {
            while (ite.hasNext()) {
                String key = (String) ite.next();
                holder.mLinearLayout.addView(getItemView(key,  getString(items.get(position).get(key))));
            }
        } else {
            while (ite.hasNext()) {
                String leftKey = (String) ite.next();
                String rightKey = "", rightValue = "";
                if (ite.hasNext()) {
                    rightKey = (String) ite.next();
                    rightValue = items.get(position).get(rightKey);
                }
                holder.mLinearLayout.addView(getItemViewTwo(leftKey,
                        getString(items.get(position).get(leftKey)) , rightKey, getString(rightValue)));
            }
        }
    }

    private View getItemView(String strText, String strValue) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler, null);
        TextView text = (TextView) view.findViewById(R.id.text);
        TextView value = (TextView) view.findViewById(R.id.value);
        value.setVisibility(View.GONE);
        View divider = view.findViewById(R.id.divider);
        divider.setVisibility(View.GONE);
        text.setText(strText + ":" + strValue);
        return view;
    }

    private View getItemViewTwo(String leftText, String leftValue, String rightText, String rightValue) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler, null);
        TextView text = (TextView) view.findViewById(R.id.text);
        TextView value = (TextView) view.findViewById(R.id.value);
        View divider = view.findViewById(R.id.divider);
        divider.setVisibility(View.GONE);
        text.setText(leftText + ":" + leftValue);
        if (!TextUtils.isEmpty(rightText)) {
            value.setText(rightText + ":" + rightValue);
        } else {
            value.setVisibility(View.GONE);
        }
        return view;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mLinearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.linear_layout);
        }
    }
}
