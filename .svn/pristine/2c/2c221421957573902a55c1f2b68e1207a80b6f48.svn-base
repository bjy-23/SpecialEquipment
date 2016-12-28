package com.wondersgroup.special.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.entity.CheckoutListResult;
import com.wondersgroup.special.utils.DateUtils;

import java.util.List;

/**
 * Created by root on 12/19/16.
 */

public class CheckoutAdapter extends BaseAdapter<CheckoutListResult.CheckoutList, CheckoutAdapter.ViewHolder> {
    public CheckoutAdapter(Context context, List<CheckoutListResult.CheckoutList> items) {
        super(context, items);
    }

    @Override
    public CheckoutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_checkout, parent, false));
    }

    @Override
    public void onBindViewHolder(CheckoutAdapter.ViewHolder holder, int position) {
        holder.mEntName.setText(items.get(position).getUnitName());
        holder.mRegCode.setText("设备注册代码：" + items.get(position).getCreditCode());
        holder.mDeviceType.setText("设备类型：" + items.get(position).getDeviceType());
        holder.mCheckDate.setText("检验日期：" + DateUtils.formatDate(items.get(position).getCheckDaye()));
        holder.mCheckUnit.setText("检验单位：" + items.get(position).getCheckUnit());
        holder.mCheckResult.setText("检验结论：" + items.get(position).getCheckResult());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mEntName, mRegCode, mDeviceType, mCheckDate, mCheckUnit, mCheckResult;

        public ViewHolder(View itemView) {
            super(itemView);
            mEntName = (TextView) itemView.findViewById(R.id.ent_name);
            mRegCode = (TextView) itemView.findViewById(R.id.reg_code);
            mDeviceType = (TextView) itemView.findViewById(R.id.device_type);
            mCheckDate = (TextView) itemView.findViewById(R.id.check_date);
            mCheckUnit = (TextView) itemView.findViewById(R.id.check_unit);
            mCheckResult = (TextView) itemView.findViewById(R.id.check_result);
        }
    }
}
