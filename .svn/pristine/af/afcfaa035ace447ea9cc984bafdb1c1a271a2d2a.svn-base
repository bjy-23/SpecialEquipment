package com.wondersgroup.special.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.entity.CompListResult;
import com.wondersgroup.special.utils.DateUtils;

import java.util.List;

/**
 * Created by chan on 11/25/16.
 */

public class VoteAdapter extends BaseAdapter<CompListResult.CompList, VoteAdapter.ViewHolder> {

    public VoteAdapter(Context context, List<CompListResult.CompList> items) {
        super(context, items);
    }

    @Override
    public VoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VoteAdapter.ViewHolder(inflater.inflate(R.layout.item_vote, parent, false));
    }

    @Override
    public void onBindViewHolder(VoteAdapter.ViewHolder holder, int position) {
        holder.mCreditCode.setText("企业信用代码：" + items.get(position).getCompCreditCode());
        holder.mObjectName.setText("被投诉对象名称：" + items.get(position).getCompObjectName());
        holder.mClientName.setText("客户姓名：" + items.get(position).getClientName());
        holder.mDealDate.setText("受理日期：" + DateUtils.formatDate(items.get(position).getDealDate()));
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mCreditCode, mObjectName, mClientName, mDealDate;

        public ViewHolder(View itemView) {
            super(itemView);
            mCreditCode = (TextView) itemView.findViewById(R.id.credit_code);
            mObjectName = (TextView) itemView.findViewById(R.id.object_name);
            mClientName = (TextView) itemView.findViewById(R.id.client_name);
            mDealDate = (TextView) itemView.findViewById(R.id.deal_date);
        }
    }

}
