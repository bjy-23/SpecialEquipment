package com.wondersgroup.special.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.entity.AnalysisResult;

import java.util.List;

/**
 * Created by root on 11/17/16.
 */

public class AnalysisAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<AnalysisResult> mItems;

    public AnalysisAdapter(Context context, List<AnalysisResult> mItems) {
        inflater = LayoutInflater.from(context);
        this.mItems = mItems;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_analysis_list, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.text);
            holder.count = (TextView) convertView.findViewById(R.id.count);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.text.setText(((AnalysisResult) getItem(position)).getName());
        holder.count.setText(((AnalysisResult) getItem(position)).getValue());
        return convertView;
    }

    public class ViewHolder {
        private TextView text, count;
    }
}
