package com.wondersgroup.special.employment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.special.employment.bean.EPermissionProInfo;
import com.wondersgroup.special.R;
import com.wondersgroup.special.common.RecyclerBaseAdapter;

import java.util.ArrayList;

/**
 * Created by bjy on 2016/12/22.
 */

public class PermissionProAdapter extends RecyclerBaseAdapter {
    private Context context;
    private ArrayList<EPermissionProInfo> datas;

    public PermissionProAdapter(Context context, ArrayList<EPermissionProInfo> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PermissionProViewHolder viewHolder = (PermissionProViewHolder) holder;
        EPermissionProInfo data = datas.get(position);
        viewHolder.tv1.setText(data.getProName());
        viewHolder.tv2.setText("考核单位："+(data.getAssessOrgan()==null?"":data.getAssessOrgan()));
        viewHolder.tv3.setText("批准单位："+(data.getApproveOrgan()==null?"":data.getApproveOrgan()));
        viewHolder.tv4.setText("考核日期："+(data.getAssessDate()==null?"":data.getAssessDate()));
        viewHolder.tv5.setText("有效日期："+(data.getValidDate()==null?"":data.getValidDate()));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.unit_license_item,parent,false);
        return new PermissionProViewHolder(view);
    }

    class PermissionProViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private int position;
        private TextView tv1,tv2,tv3,tv4,tv5;
        public PermissionProViewHolder(View itemView) {
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
            onItemClickListener.onItemClick(position);
        }
    }
}
