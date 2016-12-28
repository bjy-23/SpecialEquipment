package com.wondersgroup.special.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.activity.BDMapActivity;
import com.wondersgroup.special.entity.EquipmentListResult;

import java.util.List;

/**
 * Created by root on 11/3/16.
 */

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<EquipmentListResult.ResultList> items;
    private Context context;

    public EquipmentAdapter(Context context, List<EquipmentListResult.ResultList> items) {
        inflater = LayoutInflater.from(context);
        this.items = items;
        this.context = context;
    }

    @Override
    public EquipmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_equipment, parent, false));
    }

    public EquipmentListResult.ResultList getItem(int position) {
        return items.get(position);
    }

    public void addItems(List<EquipmentListResult.ResultList> data) {
        if (null != items && data.size() != 0) {
            items.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public void onBindViewHolder(EquipmentAdapter.ViewHolder holder, int position) {
        holder.mEquipmentName.setText(getItem(position).getDeviceName());
        holder.mType.setText("设备类型：" + (getItem(position).getDeviceType1() == null ? "" : getItem(position).getDeviceType1()));
        holder.mPingType.setText("设备品类：" + (getItem(position).getDeviceType2() == null ? "" : getItem(position).getDeviceType2()));
        holder.mChild.setText("设备子类：" + (getItem(position).getDeviceType3() == null ? "" : getItem(position).getDeviceType3()));
        holder.mCode.setText("设备代码：" + (getItem(position).getDeviceNumber() == null ? "" : getItem(position).getDeviceNumber()));
        holder.mRegNo.setText("设备登记号：" + (getItem(position).getUseCerNum() == null ? "" : getItem(position).getUseCerNum()));
        holder.mAddress.setText("设备地址：" + (getItem(position).getDeviceAddress() == null ? "" : getItem(position).getDeviceAddress()));
        if ("1".equals(getItem(position).getIfDanger()))
            holder.mWarningType.setVisibility(View.VISIBLE);
        if ("1".equals(getItem(position).getIfCheck()))
            holder.mDateType.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mEquipmentName, mWarningType, mDateType,
                mType, mPingType, mChild, mCode, mRegNo, mAddress;

        public ViewHolder(View itemView) {
            super(itemView);
            mEquipmentName = (TextView) itemView.findViewById(R.id.equipment_name);
            mWarningType = (TextView) itemView.findViewById(R.id.warning_type);
            mDateType = (TextView) itemView.findViewById(R.id.date_type);
            mType = (TextView) itemView.findViewById(R.id.type);
            mPingType = (TextView) itemView.findViewById(R.id.ping_type);
            mChild = (TextView) itemView.findViewById(R.id.child);
            mCode = (TextView) itemView.findViewById(R.id.code);
            mRegNo = (TextView) itemView.findViewById(R.id.reg_no);
            mAddress = (TextView) itemView.findViewById(R.id.address);
        }
    }
}
