package com.wondersgroup.special.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.common.RecyclerBaseAdapter;
import com.wondersgroup.special.entity.UnitPermissionEnitity;
import com.wondersgroup.special.entity.UnitUnitModel;

import java.util.ArrayList;

/**
 * Created by bjy on 2016/12/5.
 */

public class QueryUnitUnitAdapter extends RecyclerBaseAdapter {
    private ArrayList<UnitUnitModel> datas;
    private Context context;

    public QueryUnitUnitAdapter(Context context, ArrayList<UnitUnitModel> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_manage, parent, false);
        return new UintUnitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        UintUnitViewHolder viewHolder = (UintUnitViewHolder) holder;
        UnitUnitModel data = datas.get(position);
        viewHolder.mUnitName.setText(data.getuUnitName());
        viewHolder.mBoiler.setText("锅炉：" + data.getType1());
        viewHolder.mVessel.setText("压力容器：" + data.getType2());
        viewHolder.mCylindex.setText("客运索道：" + data.getType6());
        viewHolder.mPipe.setText("压力管道：" + data.getType3());
        viewHolder.mElevator.setText("电梯：" + data.getType4());
        viewHolder.mLifting.setText("起重器械：" + data.getType5());
        viewHolder.mVehicle.setText("场内车辆：" + data.getType8());
        viewHolder.mFacility.setText("游乐设施：" + data.getType7());
        viewHolder.mUnitAddress.setText("单元地址：" + data.getuUnitAddress());
        viewHolder.position = position;
    }

    public UnitUnitModel getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class UintUnitViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mUnitName;
        private TextView mUnitAddress;
        private TextView mBoiler, mVessel, mCylindex, mPipe, mElevator, mLifting, mVehicle, mFacility;
        private int position;

        public UintUnitViewHolder(View itemView) {
            super(itemView);
            mUnitName = (TextView) itemView.findViewById(R.id.unit_name);
            mUnitAddress = (TextView) itemView.findViewById(R.id.unit_address);
            mBoiler = (TextView) itemView.findViewById(R.id.boiler);
            mVessel = (TextView) itemView.findViewById(R.id.vessel);
            mCylindex = (TextView) itemView.findViewById(R.id.cylindex);
            mPipe = (TextView) itemView.findViewById(R.id.pipe);
            mElevator = (TextView) itemView.findViewById(R.id.elevator);
            mLifting = (TextView) itemView.findViewById(R.id.lifting);
            mVehicle = (TextView) itemView.findViewById(R.id.vehicle);
            mFacility = (TextView) itemView.findViewById(R.id.facility);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (null != onItemClickListener)
                onItemClickListener.onItemClick(position);
        }
    }
}
