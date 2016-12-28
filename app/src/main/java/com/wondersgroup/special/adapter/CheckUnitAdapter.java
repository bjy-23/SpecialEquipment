package com.wondersgroup.special.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.activity.BDMapActivity;
import com.wondersgroup.special.entity.CheckUnitListResult;

import java.util.List;

/**
 * Created by chan on 11/25/16.
 * 检验检测
 */

public class CheckUnitAdapter extends BaseAdapter<CheckUnitListResult.CheckList, CheckUnitAdapter.ViewHolder> {

    public CheckUnitAdapter(Context context, List<CheckUnitListResult.CheckList> items) {
        super(context, items);
    }

    @Override
    public CheckUnitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CheckUnitAdapter.ViewHolder(inflater.inflate(R.layout.item_check, parent, false));
    }

    @Override
    public void onBindViewHolder(CheckUnitAdapter.ViewHolder holder, int position) {
        holder.mEntName.setText(getString(items.get(position).getUnitName()));
        holder.mCreditCode.setText("企业信用代码：" + getString(items.get(position).getSocialCreditCode()));
        holder.mOrganCode.setText("行政区划：" + getString(items.get(position).getUnitAdminAreaCode()));
        holder.mPersonScale.setText("机构级别：" + getString(items.get(position).getOrganLevel()));
        holder.mOrganProperty.setText("机构性质：" + getString(items.get(position).getOrganProperty()));
        String address = getString(items.get(position).getUnitAddress());
        holder.mAddress.setText("单位地址：" + address);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mEntName, mCreditCode, mOrganCode, mPersonScale, mOrganProperty, mAddress;

        public ViewHolder(View itemView) {
            super(itemView);
            mCreditCode = (TextView) itemView.findViewById(R.id.credit_code);
            mEntName = (TextView) itemView.findViewById(R.id.ent_name);
            mOrganCode = (TextView) itemView.findViewById(R.id.organ_code);
            mPersonScale = (TextView) itemView.findViewById(R.id.person_scale);
            mOrganProperty = (TextView) itemView.findViewById(R.id.organ_property);
            mAddress = (TextView) itemView.findViewById(R.id.address);
        }
    }

}
