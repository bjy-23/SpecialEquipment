package com.wondersgroup.special.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wondersgroup.special.R;
import com.wondersgroup.special.datepicker.DatePickerDialog;
import com.wondersgroup.special.entity.AreaDicEntity;
import com.wondersgroup.special.utils.DateUtils;
import com.wondersgroup.special.utils.DicUtil;
import com.wondersgroup.special.widget.ClearEditText;

import java.util.HashMap;

public class EquipmentActivity extends BaseActivity implements DicUtil.OnChoiceListener {
    private Button mBtnSearch;
    private TextView mEquipmentType, mEquipmentCategory, mUsePlace, mStartDate, mEndDate;
    private ClearEditText mEquipmentCode, mRegistrationNo, mUseUnitName, mWeiBao, mManage;
    private DatePickerDialog mDatePickerDialog;
    private String[] arr;
    private HashMap<String, String> params;
    private String equipmentType, equipmentCategory, usePlace;
    private int dicType;

    @Override
    protected void initView() {
        context = this;
        setContentView(R.layout.activity_equipment);
        mTitle.setText("设备查询");
        mBtnSearch = (Button) findViewById(R.id.btn_search);
        mBtnSearch.setOnClickListener(this);
        mEquipmentType = (TextView) findViewById(R.id.equipment_type);
        mEquipmentType.setOnClickListener(this);
        mEquipmentCategory = (TextView) findViewById(R.id.equipment_category);
        mEquipmentCategory.setOnClickListener(this);
        mUsePlace = (TextView) findViewById(R.id.use_place);
        mUsePlace.setOnClickListener(this);
        mStartDate = (TextView) findViewById(R.id.start_date);
//        mStartDate.setText(DateUtils.getToday());
        mEndDate = (TextView) findViewById(R.id.end_date);
//        mEndDate.setText(DateUtils.getToday());
        mEquipmentCode = (ClearEditText) findViewById(R.id.equipment_code);
        mRegistrationNo = (ClearEditText) findViewById(R.id.registration_no);
        mUseUnitName = (ClearEditText) findViewById(R.id.use_unit_name);
        mWeiBao = (ClearEditText) findViewById(R.id.wei_bao);
        mManage = (ClearEditText) findViewById(R.id.manage);
        mEquipmentType.setOnClickListener(this);
        mEquipmentCategory.setOnClickListener(this);
        mUsePlace.setOnClickListener(this);
        mStartDate.setOnClickListener(this);
        mEndDate.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        params = new HashMap<>();
    }

    private boolean validate() {
        String code = mEquipmentCode.getText().toString();
//        if (TextUtils.isEmpty(code)) {
//            showMsg(getResources().getString(R.string.equipment_code) + "不能为空");
//            return false;
//        }

//        if (TextUtils.isEmpty(mRegistrationNo.getText().toString())) {
//            showMsg(getResources().getString(R.string.registration_no) + "不能为空");
//            return false;
//        }
//        if (TextUtils.isEmpty(mEquipmentType.getText().toString())) {
//            showMsg(getResources().getString(R.string.equipment_type) + "不能为空");
//            return false;
//        }
//        if (TextUtils.isEmpty(mWeiBao.getText().toString())) {
//            showMsg("维保单位名称不能为空");
//            return false;
//        }
//        if (TextUtils.isEmpty(mUseUnitName.getText().toString())) {
//            showMsg(getResources().getString(R.string.use_unit_name) + "不能为空");
//            return false;
//        }
//        if (TextUtils.isEmpty(mManage.getText().toString())) {
//            showMsg("管理单元不能为空");
//            return false;
//        }
        return true;
    }

    /**
     * 获取服务器提交参数
     */
    private void setParams() {
        String manage = mManage.getText().toString();
        if (!TextUtils.isEmpty(manage)) {
            params.put("unitUnit", manage);
        }
        String code = mEquipmentCode.getText().toString();
        if (!TextUtils.isEmpty(code)) {
            params.put("deviceNumber", code);
        }
        String regNo = mRegistrationNo.getText().toString();
        if (!TextUtils.isEmpty(regNo)) {
            params.put("useCerNum", regNo);
        }
        if (!TextUtils.isEmpty(equipmentType)) {
            params.put("deviceType1", equipmentType);
        }
        if (!TextUtils.isEmpty(equipmentCategory)) {
            params.put("deviceType2", equipmentCategory);
        }
        String useUnit = mUseUnitName.getText().toString();
        if (!TextUtils.isEmpty(useUnit)) {
            params.put("companyName", useUnit);
        }
        if (!TextUtils.isEmpty(usePlace)) {
            params.put("siteType", usePlace);
        }
        String weiBao = mWeiBao.getText().toString();
        if (!TextUtils.isEmpty(weiBao)) {
            params.put("maintainComName", weiBao);
        }
        String startDate = mStartDate.getText().toString();
        if (!TextUtils.isEmpty(startDate)) {
            params.put("startCerDate", startDate);
        }
        String endDate = mEndDate.getText().toString();
        if (!TextUtils.isEmpty(endDate)) {
            params.put("endCerDate", endDate);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.equipment_type:
                dicType = 1;
                DicUtil dicUtil = new DicUtil(context);
                dicUtil.setOnChoiceListener(this);
                dicUtil.makeChoice(3, "");
                break;
            case R.id.equipment_category:
                dicType = 2;
                if (TextUtils.isEmpty(equipmentType)) {
                    showMsg("请先选择设备种类");
                } else {
                    DicUtil dicUtil2 = new DicUtil(context);
                    dicUtil2.setOnChoiceListener(this);
                    dicUtil2.makeChoice(4, equipmentType);
                }

                break;
            case R.id.use_place:
                dicType = 3;
                DicUtil dicUtil3 = new DicUtil(context);
                dicUtil3.setOnChoiceListener(this);
                dicUtil3.makeChoice(5, "");
                break;
            case R.id.start_date:
//                arr = DateUtils.getStrSplit(mStartDate.getText().toString(), "-");
                if (null == mDatePickerDialog || (mDatePickerDialog != null && !mDatePickerDialog.isShowing())) {
                    mDatePickerDialog = new DatePickerDialog.Builder(EquipmentActivity.this).setDayOff(false)
//                            .setDate(Integer.valueOf(arr[0]), Integer.valueOf(arr[1]), Integer.valueOf(arr[2]))
                            .setNegativeButton("取消", null)
                            .setPositiveButton("确定", new DatePickerDialog.Builder.OnDatePickListener() {
                                @Override
                                public void onDatePick(DialogInterface dialog, int witch, int... args) {
                                    mStartDate.setText(args[0] + "-" + args[1] + "-" + args[2]);
                                }
                            }).create();
                    mDatePickerDialog.setCanceledOnTouchOutside(false);
                    mDatePickerDialog.show();
                }
                break;
            case R.id.end_date:
//                arr = DateUtils.getStrSplit(mEndDate.getText().toString(), "-");
                if (null == mDatePickerDialog || (mDatePickerDialog != null && !mDatePickerDialog.isShowing())) {
                    mDatePickerDialog = new DatePickerDialog.Builder(EquipmentActivity.this).setDayOff(false)
//                            .setDate(Integer.valueOf(arr[0]), Integer.valueOf(arr[1]), Integer.valueOf(arr[2]))
                            .setNegativeButton("取消", null)
                            .setPositiveButton("确定", new DatePickerDialog.Builder.OnDatePickListener() {
                                @Override
                                public void onDatePick(DialogInterface dialog, int witch, int... args) {
                                    mEndDate.setText(args[0] + "-" + args[1] + "-" + args[2]);
                                }
                            }).create();
                    mDatePickerDialog.setCanceledOnTouchOutside(false);
                    mDatePickerDialog.show();
                }
                break;
            case R.id.btn_search:
                if (validate()) {
                    setParams();
                    Intent intent = new Intent(EquipmentActivity.this, EquipmentListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("PARAMS", params);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void choice(AreaDicEntity entity) {
        switch (dicType) {
            case 1:
                mEquipmentType.setText(entity.getValue());
                equipmentType = entity.getKey();
                break;
            case 2:
                mEquipmentCategory.setText(entity.getValue());
                equipmentCategory = entity.getKey();
                break;
            case 3:
                mUsePlace.setText(entity.getValue());
                usePlace = entity.getKey();
                break;
        }
    }
}
