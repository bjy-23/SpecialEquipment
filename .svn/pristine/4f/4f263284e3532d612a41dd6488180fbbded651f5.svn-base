package com.wondersgroup.special.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.entity.CaseListResult;

import java.util.List;

/**
 * Created by root on 12/19/16.
 */

public class CaseAdapter extends BaseAdapter<CaseListResult.CaseList, CaseAdapter.ViewHolder> {
    public CaseAdapter(Context context, List<CaseListResult.CaseList> items) {
        super(context, items);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_case, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mEntName.setText(getItem(position).getCaseUnit());
        holder.mCaseTitle.setText("案件标题：" + getItem(position).getCaseTitle());
        holder.mCaseNo.setText("立案编号：" + getItem(position).getCaseNo());
        holder.mCaseMoney.setText("涉案金额：" + getItem(position).getCaseMoney());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mEntName, mCaseTitle, mCaseNo, mCaseMoney;

        public ViewHolder(View itemView) {
            super(itemView);
            mEntName = (TextView) itemView.findViewById(R.id.ent_name);
            mCaseTitle = (TextView) itemView.findViewById(R.id.case_title);
            mCaseNo = (TextView) itemView.findViewById(R.id.case_no);
            mCaseMoney = (TextView) itemView.findViewById(R.id.case_money);
        }
    }
}
