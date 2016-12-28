package com.wondersgroup.special.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.entity.BaseResult;

import java.util.List;

/**
 * Created by chan on 11/25/16.
 */

public class ManageAdapter extends BaseAdapter<BaseResult, ManageAdapter.ViewHolder> {

    public ManageAdapter(Context context, List<BaseResult> items) {
        super(context, items);
    }

    @Override
    public ManageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ManageAdapter.ViewHolder(inflater.inflate(R.layout.item_manage, parent, false));
    }


    @Override
    public void onBindViewHolder(ManageAdapter.ViewHolder holder, int position) {
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mUnitName, mBoiler, mVessel, mCylindex, mPipe, mElevator, mLifting, mVehicle, mFacility, mUnitAddress;

        public ViewHolder(View itemView) {
            super(itemView);
            mUnitName = (TextView) itemView.findViewById(R.id.unit_name);
            mBoiler = (TextView) itemView.findViewById(R.id.boiler);
            mVessel = (TextView) itemView.findViewById(R.id.vessel);
            mCylindex = (TextView) itemView.findViewById(R.id.cylindex);
            mPipe = (TextView) itemView.findViewById(R.id.pipe);
            mElevator = (TextView) itemView.findViewById(R.id.elevator);
            mLifting = (TextView) itemView.findViewById(R.id.lifting);
            mVehicle = (TextView) itemView.findViewById(R.id.vehicle);
            mFacility = (TextView) itemView.findViewById(R.id.facility);
            mUnitAddress = (TextView) itemView.findViewById(R.id.unit_address);
        }
    }
}
