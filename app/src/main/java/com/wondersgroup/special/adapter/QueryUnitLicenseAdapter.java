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
import com.wondersgroup.special.utils.DateUtils;

import java.util.ArrayList;

/**
 * Created by bjy on 2016/12/1.
 */

public class QueryUnitLicenseAdapter extends RecyclerBaseAdapter {
    private ArrayList<UnitPermissionEnitity> datas;
    private Context mContext;

    public QueryUnitLicenseAdapter(ArrayList<UnitPermissionEnitity> datas, Context mContext) {
        this.datas = datas;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.unit_license_item, parent, false);
        return new UintLicenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        UintLicenseViewHolder uintLicenseViewHolder = (UintLicenseViewHolder) holder;
        UnitPermissionEnitity data = datas.get(position);
        uintLicenseViewHolder.tv1.setText("单位名称：" + data.getUnitName());
        uintLicenseViewHolder.tv2.setText("许可证编号：" + data.getCertCode());
        uintLicenseViewHolder.tv3.setText("许可类型：" + data.getCertType());
        uintLicenseViewHolder.tv4.setText("发证日期：" + DateUtils.formatDate(data.getCertGrantDate()));
        uintLicenseViewHolder.tv5.setText("证书有效期：" + DateUtils.formatDate(data.getCertAvailableDate()));
        uintLicenseViewHolder.position = position;
    }

    public UnitPermissionEnitity getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class UintLicenseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;
        private TextView tv4;
        private TextView tv5;
        private int position;

        public UintLicenseViewHolder(View itemView) {
            super(itemView);

            tv1 = (TextView) itemView.findViewById(R.id.tv1);
            tv2 = (TextView) itemView.findViewById(R.id.tv2);
            tv3 = (TextView) itemView.findViewById(R.id.tv3);
            tv4 = (TextView) itemView.findViewById(R.id.tv4);
            tv5 = (TextView) itemView.findViewById(R.id.tv5);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (null != onItemClickListener)
                onItemClickListener.onItemClick(position);
        }
    }
}