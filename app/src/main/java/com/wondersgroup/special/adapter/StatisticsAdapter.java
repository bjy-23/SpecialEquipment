package com.wondersgroup.special.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.entity.StatisticsListResult;

import java.util.List;

/**
 * Created by root on 12/21/16.
 */

public class StatisticsAdapter extends BaseAdapter<StatisticsListResult, StatisticsAdapter.ViewHolder> {

    public StatisticsAdapter(Context context, List<StatisticsListResult> items) {
        super(context, items);
    }

    @Override
    public StatisticsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StatisticsAdapter.ViewHolder(inflater.inflate(R.layout.item_statistics, parent, false));
    }

    @Override
    public void onBindViewHolder(StatisticsAdapter.ViewHolder holder, int position) {
        holder.text1.setText(getItem(position).getAreaName());
        holder.text2.setText(getItem(position).getInspUnitNum());
        holder.text3.setText(getItem(position).getProUnitNum());
        if (0 != position) {
            holder.text4.setText(getItem(position).getTotalUnitNum()
                    + "(" + getItem(position).getUseNum()
                    + "/" + getItem(position).getOutUseNum() + ")");
        } else {
            holder.text4.setText(getItem(position).getTotalUnitNum());
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView text1, text2, text3, text4;

        public ViewHolder(View itemView) {
            super(itemView);
            text1 = (TextView) itemView.findViewById(R.id.text1);
            text2 = (TextView) itemView.findViewById(R.id.text2);
            text3 = (TextView) itemView.findViewById(R.id.text3);
            text4 = (TextView) itemView.findViewById(R.id.text4);
        }
    }
}