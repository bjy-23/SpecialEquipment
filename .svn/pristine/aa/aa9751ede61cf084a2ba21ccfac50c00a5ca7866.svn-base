package com.wondersgroup.special.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.entity.UseListResult;

import java.util.List;

/**
 * Created by root on 11/3/16.
 */

public class UseUnitAdapter extends BaseAdapter<UseListResult.UseList, UseUnitAdapter.ViewHolder> {

    public UseUnitAdapter(Context context, List<UseListResult.UseList> items) {
        super(context, items);
    }

    @Override
    public UseUnitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_use_unit, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.mUnitName.setText(getString(getItem(position).getUnitName()));
//        holder.mUnitCode.setText("组织机构代码：" + getString(getItem(position).getUnitCode()));
//        holder.mUnitProperty.setText("单位性质：" + getString(getItem(position).getUnitProperty()));
//        holder.mUnitType.setText("单位类型：" + getString(getItem(position).getUnitType()));
//        holder.mUnitAddress.setText("单位地址：" + getString(getItem(position).getUnitAddress()));
//        holder.mUnitPhone.setText("联系电话：" + getString(getItem(position).getUnitPhone()));
        holder.mEntName.setText(getString(getItem(position).getUnitName()));
        if (!TextUtils.isEmpty(getItem(position).getMonitorFlag()) && "true".equals(getItem(position).getMonitorFlag())) {
            holder.mWarningType.setVisibility(View.VISIBLE);
        }
        holder.mEntType.setText("(" + getString(getItem(position).getUnitType()) + ")");
        holder.mBoiler.setText("锅炉：" + getString(getItem(position).getType1()));
        holder.mVessel.setText("压力容器：" + getString(getItem(position).getType2()));
        holder.mCylindex.setText("客运索道：" + getString(getItem(position).getType6()));
        holder.mPipe.setText("压力管道：" + getString(getItem(position).getType3()));
        holder.mElevator.setText("电梯：" + getString(getItem(position).getType4()));
        holder.mLifting.setText("起重器械：" + getString(getItem(position).getType5()));
        holder.mVehicle.setText("场内车辆：" + getString(getItem(position).getType8()));
        holder.mFacility.setText("游乐设施：" + getString(getItem(position).getType7()));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mEntName, mWarningType, mEquipmentRegistrationNumber, mCreditCode, mEntType, mEntAddress;
        TextView mBoiler, mVessel, mCylindex, mPipe, mElevator, mLifting, mVehicle, mFacility;
//        TextView mUnitName, mUnitCode, mUnitProperty, mUnitType, mUnitAddress, mUnitPhone;

        public ViewHolder(View itemView) {
            super(itemView);
            mEntName = (TextView) itemView.findViewById(R.id.ent_name);
            mWarningType = (TextView) itemView.findViewById(R.id.warning_type);
            mWarningType.setVisibility(View.GONE);
            mCreditCode = (TextView) itemView.findViewById(R.id.credit_code);
            mEquipmentRegistrationNumber = (TextView) itemView.findViewById(R.id.equipment_registration_number);
            mEntType = (TextView) itemView.findViewById(R.id.ent_type);
            mEntAddress = (TextView) itemView.findViewById(R.id.ent_address);
            mBoiler = (TextView) itemView.findViewById(R.id.boiler);
            mVessel = (TextView) itemView.findViewById(R.id.vessel);
            mCylindex = (TextView) itemView.findViewById(R.id.cylindex);
            mPipe = (TextView) itemView.findViewById(R.id.pipe);
            mElevator = (TextView) itemView.findViewById(R.id.elevator);
            mLifting = (TextView) itemView.findViewById(R.id.lifting);
            mVehicle = (TextView) itemView.findViewById(R.id.vehicle);
            mFacility = (TextView) itemView.findViewById(R.id.facility);
//            mUnitName = (TextView) itemView.findViewById(R.id.unit_name);
//            mUnitCode = (TextView) itemView.findViewById(R.id.unit_code);
//            mUnitProperty = (TextView) itemView.findViewById(R.id.unit_property);
//            mUnitType = (TextView) itemView.findViewById(R.id.unit_type);
//            mUnitAddress = (TextView) itemView.findViewById(R.id.unit_address);
//            mUnitPhone = (TextView) itemView.findViewById(R.id.unit_phone);
        }
    }
}
