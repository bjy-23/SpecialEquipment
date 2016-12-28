package com.wondersgroup.special.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.constant.Constant;
import com.wondersgroup.special.entity.DataInfo;

import java.util.List;

/**
 * Created by chan on 11/26/16.
 */

public class DataAdapter extends BaseAdapter<DataInfo.ResultList, DataAdapter.ViewHolder> {
    private String type;

    public DataAdapter(Context context, List<DataInfo.ResultList> items, String mChildType) {
        super(context, items);
        type = mChildType;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataAdapter.ViewHolder(inflater.inflate(R.layout.item_data, parent, false));
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (type.equals(Constant.DataInfo.MANAGE_NO)) {
            holder.mLinearBottom.setVisibility(View.GONE);
            holder.mName.setText(items.get(position).getUnitUnitName());
            holder.mType.setText("单元代码：" + items.get(position).getUnitUnitCode());
            holder.mPingType.setText("单元地址：" + items.get(position).getUnitUnitAddress());
        } else if (type.equals(Constant.DataInfo.EQUIPMENT_NO) || type.equals(Constant.DataInfo.CHECK_EQU_NO)) {
            holder.mName.setText(items.get(position).getDeviceName());
            holder.mType.setText("设备类型：" + items.get(position).getDeviceType());
            holder.mPingType.setText("设备品类：" + items.get(position).getDeviceClass());
            holder.mCode.setText("设备代码：" + items.get(position).getDeviceNumber());
            holder.mAddress.setText("设备所属业务类型：" + items.get(position).getBusinesStype());
        } else {
            holder.mName.setText(items.get(position).getUnitName());
            holder.mType.setText("单位性质：" + items.get(position).getUnitProperty());
            holder.mPingType.setText("单位地址：" + items.get(position).getUnitAddress());
            holder.mCode.setText("联系电话：" + items.get(position).getUnitPhone());
            holder.mAddress.setText("组织机构代码：" + items.get(position).getUnitCode());
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mName, mType, mPingType, mCode, mAddress;
        public LinearLayout mLinearBottom;

        public ViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.name);
            mType = (TextView) itemView.findViewById(R.id.type);
            mPingType = (TextView) itemView.findViewById(R.id.ping_type);
            mCode = (TextView) itemView.findViewById(R.id.code);
            mAddress = (TextView) itemView.findViewById(R.id.address);
            mLinearBottom = (LinearLayout) itemView.findViewById(R.id.linear_bottom);
        }
    }

}
