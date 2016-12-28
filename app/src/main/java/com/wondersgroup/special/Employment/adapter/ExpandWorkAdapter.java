package com.wondersgroup.special.employment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.common.RecyclerBaseAdapter;
import com.wondersgroup.special.employment.bean.EWorkInfo;

import java.util.ArrayList;

/**
 * Created by bjy on 2016/12/21.
 */

public class ExpandWorkAdapter extends RecyclerBaseAdapter {
    private ArrayList<EWorkInfo> datas;

    public ExpandWorkAdapter(Context context, ArrayList<EWorkInfo> datas) {
        super.context = context;
        this.datas = datas;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        WorkViewHolder workViewHolder = (WorkViewHolder) holder;
        EWorkInfo EWorkInfo = datas.get(position);
        workViewHolder.tv1.setText(EWorkInfo.getUnitName());
        workViewHolder.tv2.setText("组织机构代码："+ EWorkInfo.getUnitCode());
        workViewHolder.tv3.setText("受聘日期："+ EWorkInfo.getStratDate());
        workViewHolder.tv4.setText("离开日期："+ EWorkInfo.getEndDate());
        workViewHolder.tv5.setText("职务："+ EWorkInfo.getPost());
        workViewHolder.position = position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.unit_license_item,parent,false);
        return new WorkViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class WorkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;
        private TextView tv4;
        private TextView tv5;
        private int position;

        public WorkViewHolder(View itemView) {
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
