package com.wondersgroup.special.employment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.common.RecyclerBaseAdapter;
import com.wondersgroup.special.employment.bean.EChangeInfo;

import java.util.ArrayList;

/**
 * Created by bjy on 2016/12/21.
 */

public class ExpanChangeAdapter extends RecyclerBaseAdapter {
    private ArrayList<EChangeInfo> datas;

    public ExpanChangeAdapter(Context context, ArrayList<EChangeInfo> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChangeViewHolder changeViewHolder = (ChangeViewHolder) holder;
        EChangeInfo eChangeInfo = datas.get(position);
        changeViewHolder.tv1.setText(eChangeInfo.getChangePro());
        changeViewHolder.tv2.setText("变更日期："+(eChangeInfo.getChangeDate()==null?"": eChangeInfo.getChangeDate()));
        changeViewHolder.tv3.setText("变更人："+(eChangeInfo.getCahngePerson()==null?"": eChangeInfo.getCahngePerson()));
        changeViewHolder.tv4.setText("变更内容："+(eChangeInfo.getChangeContent()==null?"": eChangeInfo.getChangeContent()));
        changeViewHolder.tv5.setVisibility(View.GONE);
        changeViewHolder.position =position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.unit_license_item,parent,false);
        return new ChangeViewHolder(view);
    }

    class ChangeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;
        private TextView tv4;
        private TextView tv5;
        private int position;
        public ChangeViewHolder(View itemView) {
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
