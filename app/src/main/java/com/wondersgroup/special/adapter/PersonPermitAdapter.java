package com.wondersgroup.special.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.entity.BaseResult;

import java.util.List;

/**
 * Created by chan on 11/25/16.
 */

public class PersonPermitAdapter extends BaseAdapter<BaseResult, PersonPermitAdapter.ViewHolder> {

    public PersonPermitAdapter(Context context, List<BaseResult> items) {
        super(context, items);
    }

    @Override
    public PersonPermitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PersonPermitAdapter.ViewHolder(inflater.inflate(R.layout.item_person_permit, parent, false));
    }


    @Override
    public void onBindViewHolder(PersonPermitAdapter.ViewHolder holder, int position) {
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mPermitName, mPermitType, mName, mIssueDate, mValidityDate;
        private ImageView avatar;

        public ViewHolder(View itemView) {
            super(itemView);
            mPermitName = (TextView) itemView.findViewById(R.id.permit_name);
            mPermitType = (TextView) itemView.findViewById(R.id.permit_type);
            mName = (TextView) itemView.findViewById(R.id.name);
            mIssueDate = (TextView) itemView.findViewById(R.id.issue_date);
            mValidityDate = (TextView) itemView.findViewById(R.id.validity_date);
            avatar = (ImageView) itemView.findViewById(R.id.avatar);
        }
    }
}