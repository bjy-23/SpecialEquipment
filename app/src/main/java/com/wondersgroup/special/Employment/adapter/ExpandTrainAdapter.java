package com.wondersgroup.special.employment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.common.RecyclerBaseAdapter;
import com.wondersgroup.special.employment.bean.ETrainInfo;

import java.util.ArrayList;

/**
 * Created by bjy on 2016/12/21.
 */

public class ExpandTrainAdapter extends RecyclerBaseAdapter {
    private ArrayList<ETrainInfo> datas;

    public ExpandTrainAdapter(Context context, ArrayList<ETrainInfo> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TrainViewHolder trainViewHolder = (TrainViewHolder) holder;
        ETrainInfo eTrainInfo = datas.get(position);
        trainViewHolder.tv1.setText(eTrainInfo.getTrainContent());
        trainViewHolder.tv2.setText("培训日期：");
        trainViewHolder.tv3.setText("培训单位："+(eTrainInfo.getTrainUnit()==null?"": eTrainInfo.getTrainUnit()));
        trainViewHolder.tv4.setText("培训成绩："+(eTrainInfo.getTrainScore()==null?"": eTrainInfo.getTrainScore()));
        trainViewHolder.tv5.setVisibility(View.GONE);
        trainViewHolder.position = position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.unit_license_item,parent,false);
        return new TrainViewHolder(view);
    }

    class TrainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;
        private TextView tv4;
        private TextView tv5;
        private int position;
        public TrainViewHolder(View itemView) {
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
