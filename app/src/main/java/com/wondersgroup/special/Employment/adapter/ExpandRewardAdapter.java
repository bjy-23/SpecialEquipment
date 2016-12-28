package com.wondersgroup.special.employment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.common.RecyclerBaseAdapter;
import com.wondersgroup.special.employment.bean.ERewardInfo;

import java.util.ArrayList;

/**
 * Created by bjy on 2016/12/21.
 */

public class ExpandRewardAdapter extends RecyclerBaseAdapter {
    private ArrayList<ERewardInfo> datas;

    public ExpandRewardAdapter(Context context, ArrayList<ERewardInfo> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RewardViewHolder rewardViewHolder = (RewardViewHolder) holder;
        ERewardInfo ERewardInfo = datas.get(position);
        rewardViewHolder.tv1.setText(ERewardInfo.getAwardPro());
        rewardViewHolder.tv2.setText("奖惩日期："+(ERewardInfo.getAwardDate()==null?"": ERewardInfo.getAwardDate()));
        rewardViewHolder.tv3.setText("奖惩机构："+(ERewardInfo.getAwardOrgan()==null?"": ERewardInfo.getAwardOrgan()));
        rewardViewHolder.tv4.setVisibility(View.GONE);
        rewardViewHolder.tv5.setVisibility(View.GONE);
        rewardViewHolder.position = position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.unit_license_item,parent,false);
        return new RewardViewHolder(view);
    }

    class RewardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;
        private TextView tv4;
        private TextView tv5;
        private int position;
        public RewardViewHolder(View itemView) {
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