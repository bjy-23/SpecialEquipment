package com.wondersgroup.special.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.wondersgroup.special.R;
import com.wondersgroup.special.activity.CategoryActivity;
import com.wondersgroup.special.activity.CategoryUnitActivity;

/**
 * Created by chan on 11/2/16.
 */

public class CategoryFragment extends BaseFragment implements View.OnClickListener {
    private View mParentView;
    private RelativeLayout mEmploymentUnit, mEmploymentNumber, mSpecialEquipment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mParentView = getView();
        mEmploymentUnit = (RelativeLayout) mParentView.findViewById(R.id.employment_unit);
        mEmploymentUnit.setOnClickListener(this);
        mEmploymentNumber = (RelativeLayout) mParentView.findViewById(R.id.employment_number);
        mEmploymentNumber.setOnClickListener(this);
        mSpecialEquipment = (RelativeLayout) mParentView.findViewById(R.id.special_equipment);
        mSpecialEquipment.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.employment_unit:
                startActivity(new Intent(getActivity(), CategoryUnitActivity.class));
                break;
            case R.id.employment_number:
                break;
            case R.id.special_equipment:
                startActivity(new Intent(getActivity(), CategoryActivity.class));
                break;
            default:
                break;
        }
    }
}