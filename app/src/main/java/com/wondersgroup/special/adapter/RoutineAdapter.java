package com.wondersgroup.special.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.entity.SupervisionListResult;
import com.wondersgroup.special.utils.DateUtils;

import java.util.List;

/**
 * Created by root on 12/19/16.
 */

public class RoutineAdapter extends BaseAdapter<SupervisionListResult.SupervisionList, RoutineAdapter.ViewHolder> {

    public RoutineAdapter(Context context, List<SupervisionListResult.SupervisionList> items) {
        super(context, items);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_routine, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mEntName.setText(items.get(position).getUnitName());
        holder.mRegCode.setText("设备注册代码：" + items.get(position).getCreditCode());
        holder.mDeviceType.setText("设备类型：" + items.get(position).getDeviceType());
        holder.mTaskType.setText("任务类型：" + items.get(position).getTaskType());
        holder.mStartDate.setText("检查开始日期：" + DateUtils.formatDate(items.get(position).getStartDate()));
        holder.mEndDate.setText("检查结束日期：" + DateUtils.formatDate(items.get(position).getEndDate()));
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mEntName, mRegCode, mDeviceType, mTaskType, mStartDate, mEndDate;

        public ViewHolder(View itemView) {
            super(itemView);
            mEntName = (TextView) itemView.findViewById(R.id.ent_name);
            mRegCode = (TextView) itemView.findViewById(R.id.reg_code);
            mDeviceType = (TextView) itemView.findViewById(R.id.device_type);
            mTaskType = (TextView) itemView.findViewById(R.id.task_type);
            mStartDate = (TextView) itemView.findViewById(R.id.start_date);
            mEndDate = (TextView) itemView.findViewById(R.id.end_date);
        }
    }
}