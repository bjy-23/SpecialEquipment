package com.wondersgroup.special.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.entity.BaseResult;
import com.wondersgroup.special.entity.QueryAccidentEntity;
import com.wondersgroup.special.utils.DateUtils;

import java.util.List;

/**
 * Created by chan on 12/14/16.
 */

public class AccidentAdapter extends BaseAdapter<QueryAccidentEntity, AccidentAdapter.ViewHolder> {
    public AccidentAdapter(Context context, List<QueryAccidentEntity> items) {
        super(context, items);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_accident, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mEntName.setText(items.get(position).getAccidentUnit());
        holder.mHappenTime.setText("发生时间：" + DateUtils.formatDate(items.get(position).getHappenTime()));
        holder.mReportTime.setText("上报时间：" + DateUtils.formatDate(items.get(position).getReportTime()));
        holder.mArea.setText("所属区域：" + items.get(position).getArea());
        holder.mAccidentPlace.setText("事故地点：" + items.get(position).getAccidentPlace());
        holder.mAccidentType.setText("事故类别：" + items.get(position).getAccidentType());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mEntName, mHappenTime, mReportTime, mArea, mAccidentPlace, mAccidentType;

        public ViewHolder(View itemView) {
            super(itemView);
            mEntName = (TextView) itemView.findViewById(R.id.ent_name);
            mHappenTime = (TextView) itemView.findViewById(R.id.happen_time);
            mReportTime = (TextView) itemView.findViewById(R.id.report_time);
            mArea = (TextView) itemView.findViewById(R.id.area);
            mAccidentPlace = (TextView) itemView.findViewById(R.id.accident_place);
            mAccidentType = (TextView) itemView.findViewById(R.id.accident_type);
        }
    }
}
