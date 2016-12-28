package com.wondersgroup.special.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.activity.AccidentActivity;
import com.wondersgroup.special.activity.CaseActivity;
import com.wondersgroup.special.activity.CheckActivity;
import com.wondersgroup.special.activity.RoutineActivity;
import com.wondersgroup.special.activity.EmploymentActivity;
import com.wondersgroup.special.activity.EquipmentActivity;
import com.wondersgroup.special.activity.QueryRecordActivity;
import com.wondersgroup.special.activity.unit.CheckUnitActivity;
import com.wondersgroup.special.activity.unit.ProductUnitActivity;
import com.wondersgroup.special.activity.VoteActivity;
import com.wondersgroup.special.activity.unit.UseUnitActivity;
import com.wondersgroup.special.archive.ArchiveDetailActivity;
import com.wondersgroup.special.archive.ArchiveDetailListActivity;
import com.wondersgroup.special.constant.Constant;
import com.wondersgroup.special.constant.Params;

/**
 * Created by chan on 11/2/16.
 */

public class ArchiveFragment extends BaseFragment implements View.OnClickListener {
    private RelativeLayout mRelativeInspectionUnit, mRelativeUseUnit, mRelativeProduct;
    private RelativeLayout mRelativeEmployment, mRelativeInspectionPerson, mRelativeInspectors, mRelativeExpert;
    private RelativeLayout mRelativeSpecial;
    private RelativeLayout mRelativeUnitLicense, mRelativePersonLicense, mRelativeUnitArchive,
            mRelativeAccidentArchive, mRelativeCheckArchive, mRelativeEquipmentLicense,
            mRelativeRoutineInspection, mRelativeCaseArchive, mRelativeVoteArchive;
    private View mParentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_archive, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        mParentView = getView();
        ((TextView) mParentView.findViewById(R.id.title)).setText("档案导航");
        initView();
        initListener();
    }

    private void initView() {
        mRelativeInspectionUnit = (RelativeLayout) mParentView.findViewById(R.id.relative_inspection_unit);
        mRelativeUseUnit = (RelativeLayout) mParentView.findViewById(R.id.relative_use_unit);
        mRelativeProduct = (RelativeLayout) mParentView.findViewById(R.id.relative_product);
        mRelativeEmployment = (RelativeLayout) mParentView.findViewById(R.id.relative_employment);
        mRelativeInspectionPerson = (RelativeLayout) mParentView.findViewById(R.id.relative_inspection_person);
        mRelativeInspectors = (RelativeLayout) mParentView.findViewById(R.id.relative_inspectors);
        mRelativeExpert = (RelativeLayout) mParentView.findViewById(R.id.relative_expert);
        mRelativeSpecial = (RelativeLayout) mParentView.findViewById(R.id.relative_special);
        mRelativeUnitLicense = (RelativeLayout) mParentView.findViewById(R.id.relative_unit_license);
        mRelativePersonLicense = (RelativeLayout) mParentView.findViewById(R.id.relative_person_license);
        mRelativeUnitArchive = (RelativeLayout) mParentView.findViewById(R.id.relative_unit_archive);
        mRelativeAccidentArchive = (RelativeLayout) mParentView.findViewById(R.id.relative_accident_archive);
        mRelativeCheckArchive = (RelativeLayout) mParentView.findViewById(R.id.relative_check_archive);
        mRelativeEquipmentLicense = (RelativeLayout) mParentView.findViewById(R.id.relative_equipment_license);
        mRelativeRoutineInspection = (RelativeLayout) mParentView.findViewById(R.id.relative_routine_inspection);
        mRelativeCaseArchive = (RelativeLayout) mParentView.findViewById(R.id.relative_case_archive);
        mRelativeVoteArchive = (RelativeLayout) mParentView.findViewById(R.id.relative_vote_archive);
    }

    private void initListener() {
        mRelativeInspectionUnit.setOnClickListener(this);
        mRelativeUseUnit.setOnClickListener(this);
        mRelativeProduct.setOnClickListener(this);
        mRelativeEmployment.setOnClickListener(this);
        mRelativeInspectionPerson.setOnClickListener(this);
        mRelativeInspectors.setOnClickListener(this);
        mRelativeExpert.setOnClickListener(this);
        mRelativeSpecial.setOnClickListener(this);
        mRelativeUnitLicense.setOnClickListener(this);
        mRelativePersonLicense.setOnClickListener(this);
        mRelativeUnitArchive.setOnClickListener(this);
        mRelativeAccidentArchive.setOnClickListener(this);
        mRelativeCheckArchive.setOnClickListener(this);
        mRelativeEquipmentLicense.setOnClickListener(this);
        mRelativeRoutineInspection.setOnClickListener(this);
        mRelativeCaseArchive.setOnClickListener(this);
        mRelativeVoteArchive.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), QueryRecordActivity.class);
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.relative_inspection_unit:
                startActivity(new Intent(getActivity(), CheckUnitActivity.class));
                break;
            case R.id.relative_use_unit:
                startActivity(new Intent(getActivity(), UseUnitActivity.class));
                break;
            case R.id.relative_product:
                startActivity(new Intent(getActivity(), ProductUnitActivity.class));
                break;
            case R.id.relative_employment:
                intent.setClass(getActivity(), EmploymentActivity.class);
                bundle.putString(Params.EMPLOY_TYPE, Constant.EmployType.EMPLOYMENT);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.relative_inspection_person:
                intent.setClass(getActivity(), EmploymentActivity.class);
                bundle.putString(Params.EMPLOY_TYPE, Constant.EmployType.TEST);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.relative_inspectors:
                intent.setClass(getActivity(), EmploymentActivity.class);
                bundle.putString(Params.EMPLOY_TYPE, Constant.EmployType.INSPECTOR);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.relative_expert:
                intent.setClass(getActivity(), EmploymentActivity.class);
                bundle.putString(Params.EMPLOY_TYPE, Constant.EmployType.NONDESTRUCTIVE);
                intent.putExtras(bundle);
//                startActivity(intent);
                break;
            case R.id.relative_special:
                startActivity(new Intent(getActivity(), EquipmentActivity.class));
                break;
            case R.id.relative_unit_license:
                intent.putExtra("type", 1);
                startActivity(intent);
                break;
            case R.id.relative_person_license:
                intent.putExtra("type", 2);
//                startActivity(intent);
                break;
            case R.id.relative_unit_archive:
                intent.putExtra("type", 3);
                startActivity(intent);
                break;
            case R.id.relative_vote_archive:
                startActivity(new Intent(getActivity(), VoteActivity.class));
                break;
            case R.id.relative_accident_archive:
                startActivity(new Intent(getActivity(), AccidentActivity.class));
                break;
            case R.id.relative_check_archive://检验档案
                startActivity(new Intent(getActivity(), CheckActivity.class));
                break;
            case R.id.relative_equipment_license:
                break;
            case R.id.relative_routine_inspection://监察档案
                startActivity(new Intent(getActivity(), RoutineActivity.class));
                break;
            case R.id.relative_case_archive:
                startActivity(new Intent(getActivity(), CaseActivity.class));
                break;
            default:
                break;
        }
    }
}
