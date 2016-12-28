package com.wondersgroup.special.employment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.common.RecyclerBaseAdapter;
import com.wondersgroup.special.employment.bean.ECheckInfo;

import java.util.ArrayList;

/**
 * Created by bjy on 2016/12/21.
 */

public class ExpandCheckAdapter extends RecyclerBaseAdapter {
    private ArrayList<ECheckInfo> datas;

    public ExpandCheckAdapter(Context context, ArrayList<ECheckInfo> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CheckViewHolder checkViewHolder = (CheckViewHolder) holder;
        ECheckInfo eCheckInfo = datas.get(position);
        checkViewHolder.tv1.setText(eCheckInfo.getCertPro());
        checkViewHolder.tv2.setText("考核内容："+(eCheckInfo.getAssessContent()==null?"": eCheckInfo.getAssessContent()));
        checkViewHolder.tv3.setText("考核日期："+(eCheckInfo.getAssessDate()==null?"": eCheckInfo.getAssessDate()));
        checkViewHolder.tv4.setText("考核成绩："+(eCheckInfo.getAssessScore()==null?"": eCheckInfo.getAssessScore()));
        checkViewHolder.tv5.setVisibility(View.GONE);
        checkViewHolder.position = position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.unit_license_item,parent,false);
        return new CheckViewHolder(view);
    }

    class CheckViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;
        private TextView tv4;
        private TextView tv5;
        private int position;
        public CheckViewHolder(View itemView) {
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
