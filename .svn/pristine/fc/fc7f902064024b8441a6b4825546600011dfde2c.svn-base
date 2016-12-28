package com.wondersgroup.special.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.entity.EmployListResult;

import java.util.List;

/**
 * Created by root on 11/3/16.
 */

public class EmploymentAdapter extends BaseAdapter<EmployListResult.EmployList, EmploymentAdapter.ViewHolder> {

    public EmploymentAdapter(Context context, List<EmployListResult.EmployList> items) {
        super(context, items);
    }

    @Override
    public EmploymentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_employment_list, parent, false));
    }

    @Override
    public void onBindViewHolder(EmploymentAdapter.ViewHolder holder, int position) {
        holder.mEmploymentName.setText(items.get(position).getName());
        holder.mIdCode.setText(String.format(context.getString(R.string.item_id_code), items.get(position).getCardId()));
        holder.mTheNo.setText("许可类型：" + items.get(position).getCertType());
        holder.mEmploymentUnit.setText(String.format(context.getString(R.string.employment_unit), items.get(position).getUnitName()));
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mEmploymentName, mIdCode, mTheNo, mEmploymentUnit, mEntAddress;

        public ViewHolder(View itemView) {
            super(itemView);
            mEmploymentName = (TextView) itemView.findViewById(R.id.employment_name);
            mIdCode = (TextView) itemView.findViewById(R.id.id_code);
            mTheNo = (TextView) itemView.findViewById(R.id.the_no);
            mEmploymentUnit = (TextView) itemView.findViewById(R.id.employment_unit);
            mEntAddress = (TextView) itemView.findViewById(R.id.ent_address);
        }
    }
}
