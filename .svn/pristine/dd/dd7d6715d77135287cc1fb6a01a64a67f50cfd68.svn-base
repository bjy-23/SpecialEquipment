package com.wondersgroup.special.employment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.common.RecyclerBaseAdapter;
import com.wondersgroup.special.employment.bean.EPermissionInfo;

import java.util.ArrayList;

/**
 * Created by bjy on 2016/12/21.
 */

public class ExpandPermissionAdapter extends RecyclerBaseAdapter {
    private ArrayList<EPermissionInfo> datas;

    public ExpandPermissionAdapter(Context context, ArrayList<EPermissionInfo> datas) {
        super.context = context;
        this.datas = datas;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PermissionViewHolder permissionViewHolder = (PermissionViewHolder) holder;
        EPermissionInfo EPermissionInfo = datas.get(position);
        permissionViewHolder.tv1.setText(EPermissionInfo.getCertName());
        permissionViewHolder.tv2.setText("许可类型："+ (EPermissionInfo.getCertType()==null?"": EPermissionInfo.getCertType()));
        permissionViewHolder.tv3.setText("发证日期：" + (EPermissionInfo.getCertDate()==null?"": EPermissionInfo.getCertDate()));
        permissionViewHolder.tv4.setText("证书有效期：" + (EPermissionInfo.getValidDate()==null?"": EPermissionInfo.getValidDate()));
        permissionViewHolder.tv5.setVisibility(View.GONE);
        permissionViewHolder.position = position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.unit_license_item,parent,false);
        return new PermissionViewHolder(view);
    }

    class PermissionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;
        private TextView tv4;
        private TextView tv5;
        private int position;

        public PermissionViewHolder(View itemView) {
            super(itemView);

            tv1 = (TextView)itemView.findViewById(R.id.tv1);
            tv2 = (TextView)itemView.findViewById(R.id.tv2);
            tv3 = (TextView)itemView.findViewById(R.id.tv3);
            tv4 = (TextView)itemView.findViewById(R.id.tv4);
            tv5 = (TextView)itemView.findViewById(R.id.tv5);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(position);
        }
    }
}
