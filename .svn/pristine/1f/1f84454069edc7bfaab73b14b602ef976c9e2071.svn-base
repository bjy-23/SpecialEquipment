package com.wondersgroup.special.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.common.RecyclerBaseAdapter;
import com.wondersgroup.special.entity.PersonLicenseModel;

import java.util.ArrayList;

/**
 * Created by bjy on 2016/12/5.
 */

public class QueryPersonLicenseAdapter extends RecyclerBaseAdapter{
    private ArrayList<PersonLicenseModel> datas;
    private Context context;

    public QueryPersonLicenseAdapter(Context context, ArrayList<PersonLicenseModel> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.unit_license_item,null);
        return new PersonLicenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PersonLicenseViewHolder viewHolder = (PersonLicenseViewHolder)holder;
        PersonLicenseModel data = datas.get(position);
        viewHolder.tv1.setText("人员姓名：" +data.getName());
        viewHolder.tv2.setText("许可名称：" +data.getCertName());
        viewHolder.tv3.setText("许可类型：" + data.getCertType());
        viewHolder.tv4.setText("发证日期：" + data.getCertGrantDate());
        viewHolder.tv5.setText("证书有效期："+data.getCertAvailableDate());
        viewHolder.position = position;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class PersonLicenseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;
        private TextView tv4;
        private TextView tv5;
        private int position;

        public PersonLicenseViewHolder(View itemView) {
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
