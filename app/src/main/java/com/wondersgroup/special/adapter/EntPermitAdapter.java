package com.wondersgroup.special.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.entity.BaseResult;

import java.util.List;

/**
 * Created by chan on 11/25/16.
 */

public class EntPermitAdapter extends BaseAdapter<BaseResult, EntPermitAdapter.ViewHolder> {

    public EntPermitAdapter(Context context, List<BaseResult> items) {
        super(context, items);
    }

    @Override
    public EntPermitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EntPermitAdapter.ViewHolder(inflater.inflate(R.layout.item_ent_permit, parent, false));
    }

    @Override
    public void onBindViewHolder(EntPermitAdapter.ViewHolder holder, int position) {
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mPermitName, mPermitType, mEntName, mIssueDate,
                mValidityDate;

        public ViewHolder(View itemView) {
            super(itemView);
            mPermitName = (TextView) itemView.findViewById(R.id.permit_name);
            mPermitType = (TextView) itemView.findViewById(R.id.permit_type);
            mEntName = (TextView) itemView.findViewById(R.id.ent_name);
            mIssueDate = (TextView) itemView.findViewById(R.id.issue_date);
            mValidityDate = (TextView) itemView.findViewById(R.id.validity_date);
        }
    }
}