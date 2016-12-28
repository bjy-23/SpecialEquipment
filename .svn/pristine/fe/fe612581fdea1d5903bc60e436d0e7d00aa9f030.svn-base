package com.wondersgroup.special.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.constant.Constant;
import com.wondersgroup.special.constant.Params;
import com.wondersgroup.special.datepicker.DatePickerDialog;
import com.wondersgroup.special.datepicker.NumberPickerDialog;
import com.wondersgroup.special.datepicker.YearPickerDialog;
import com.wondersgroup.special.widget.ClearEditText;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 事故档案
 */
public class AccidentActivity extends BaseActivity {
    private Button mBtnSearch;
    private TextView mStartDeath, mEndDeath;
    private TextView mStartInjuries, mEndInjuries;
    private ClearEditText mStartLoss, mEndLoss;
    private TextView mStartDate, mEndDate;
    private TextView mAccidentType;
    private ClearEditText mAccidentUnit, mUnitCode;
    private NumberPickerDialog mNumberPickerDialog;
    private DatePickerDialog mDatePickerDialog;
    private HashMap<String, String> params;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_accident);
        mTitle.setText("事故档案");
        mBtnSearch = (Button) findViewById(R.id.btn_search);
        mBtnSearch.setOnClickListener(this);
        mStartDeath = (TextView) findViewById(R.id.start_death);
        mEndDeath = (TextView) findViewById(R.id.end_death);
        mStartInjuries = (TextView) findViewById(R.id.start_injuries);
        mEndInjuries = (TextView) findViewById(R.id.end_injuries);
        mStartLoss = (ClearEditText) findViewById(R.id.start_loss);
        mEndLoss = (ClearEditText) findViewById(R.id.end_loss);
        mStartDate = (TextView) findViewById(R.id.start_date);
        mEndDate = (TextView) findViewById(R.id.end_date);
        mAccidentUnit = (ClearEditText) findViewById(R.id.accident_unit);
        mUnitCode = (ClearEditText) findViewById(R.id.unit_code);
        mAccidentType = (TextView) findViewById(R.id.accident_type);
        mStartDeath.setOnClickListener(this);
        mEndDeath.setOnClickListener(this);
        mStartInjuries.setOnClickListener(this);
        mEndInjuries.setOnClickListener(this);
        mStartDate.setOnClickListener(this);
        mEndDate.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        params = new HashMap<>();
    }

    private boolean validate() {
        String startLoss = mStartLoss.getText().toString();
        if (!TextUtils.isEmpty(startLoss) && !Pattern.matches(Constant.DIGIT_REGEX, startLoss)) {
            showMsg("请输入正确的起始金额");
            return false;
        }
        String endLoss = mEndLoss.getText().toString();
        if (!TextUtils.isEmpty(endLoss) && !Pattern.matches(Constant.DIGIT_REGEX, endLoss)) {
            showMsg("请输入正确的结束金额");
            return false;
        }
        return true;
    }

    private void getParams() {
        String unitName = mAccidentUnit.getText().toString();
        if (!TextUtils.isEmpty(unitName)) {
            params.put("unitName", unitName);
        }
        String unitCode = mUnitCode.getText().toString();
        if (!TextUtils.isEmpty(unitCode)) {
            params.put("unitCode", unitCode);
        }
        String startDate = mStartDate.getText().toString();
        if (!TextUtils.isEmpty(startDate)) {
            params.put("startDate", startDate);
        }
        String endDate = mEndDate.getText().toString();
        if (!TextUtils.isEmpty(endDate)) {
            params.put("endDate", endDate);
        }
        String deadNumS = mStartDeath.getText().toString();
        if (!TextUtils.isEmpty(deadNumS)) {
            params.put("deadNumS", deadNumS);
        }
        String deadNumE = mEndDeath.getText().toString();
        if (!TextUtils.isEmpty(deadNumE)) {
            params.put("deadNumE", deadNumE);
        }
        String hurtNumS = mStartInjuries.getText().toString();
        if (!TextUtils.isEmpty(hurtNumS)) {
            params.put("hurtNumS", hurtNumS);
        }
        String hurtNumE = mEndInjuries.getText().toString();
        if (!TextUtils.isEmpty(hurtNumE)) {
            params.put("hurtNumE", hurtNumE);
        }
        String moneyNumS = mStartLoss.getText().toString();
        if (!TextUtils.isEmpty(moneyNumS)) {
            params.put("moneyNumS", moneyNumS);
        }
        String moneyNumE = mEndLoss.getText().toString();
        if (!TextUtils.isEmpty(moneyNumE)) {
            params.put("moneyNumE", moneyNumE);
        }
        String type = mAccidentType.getText().toString();
        if (!TextUtils.isEmpty(type)) {
            params.put("type", type);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.start_date:
                if (null == mDatePickerDialog || (null != mDatePickerDialog && !mDatePickerDialog.isShowing())) {
                    mDatePickerDialog = new DatePickerDialog.Builder(AccidentActivity.this)
                            .setNegativeButton("清空", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mStartDate.setText("");
                                }
                            }).setPositiveButton("确定", new DatePickerDialog.Builder.OnDatePickListener() {
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
                if (null == mDatePickerDialog || (null != mDatePickerDialog && !mDatePickerDialog.isShowing())) {
                    mDatePickerDialog = new DatePickerDialog.Builder(AccidentActivity.this)
                            .setNegativeButton("清空", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mEndDate.setText("");
                                }
                            }).setPositiveButton("确定", new DatePickerDialog.Builder.OnDatePickListener() {
                                @Override
                                public void onDatePick(DialogInterface dialog, int witch, int... args) {
                                    mEndDate.setText(args[0] + "-" + args[1] + "-" + args[2]);
                                }
                            }).create();
                    mDatePickerDialog.setCanceledOnTouchOutside(false);
                    mDatePickerDialog.show();
                }
                break;
            case R.id.start_death:
                if (null == mNumberPickerDialog || (null != mNumberPickerDialog && !mNumberPickerDialog.isShowing())) {
                    mNumberPickerDialog = new NumberPickerDialog.Builder(AccidentActivity.this)
                            .setMinValue(0)
                            .setMaxValue(36)
                            .setUnit("人")
                            .setNegativeButton("清空", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mStartDeath.setText("");
                                }
                            }).setPositiveButton("确定", new NumberPickerDialog.Builder.OnNumberPickListener() {
                                @Override
                                public void onNumberPick(DialogInterface dialog, int witch, int... args) {
                                    mStartDeath.setText(String.valueOf(args[0]));
                                }
                            }).create();
                    mNumberPickerDialog.setCanceledOnTouchOutside(false);
                    mNumberPickerDialog.show();
                }
                break;
            case R.id.end_death:
                if (null == mNumberPickerDialog || (null != mNumberPickerDialog && !mNumberPickerDialog.isShowing())) {
                    mNumberPickerDialog = new NumberPickerDialog.Builder(AccidentActivity.this)
                            .setMinValue(0)
                            .setMaxValue(36)
                            .setUnit("人")
                            .setNegativeButton("清空", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mEndDeath.setText("");
                                }
                            }).setPositiveButton("确定", new NumberPickerDialog.Builder.OnNumberPickListener() {
                                @Override
                                public void onNumberPick(DialogInterface dialog, int witch, int... args) {
                                    mEndDeath.setText(String.valueOf(args[0]));
                                }
                            }).create();
                    mNumberPickerDialog.setCanceledOnTouchOutside(false);
                    mNumberPickerDialog.show();
                }
                break;
            case R.id.start_injuries:
                if (null == mNumberPickerDialog || (null != mNumberPickerDialog && !mNumberPickerDialog.isShowing())) {
                    mNumberPickerDialog = new NumberPickerDialog.Builder(AccidentActivity.this)
                            .setMinValue(0)
                            .setMaxValue(36)
                            .setUnit("人")
                            .setNegativeButton("清空", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mStartInjuries.setText("");
                                }
                            }).setPositiveButton("确定", new NumberPickerDialog.Builder.OnNumberPickListener() {
                                @Override
                                public void onNumberPick(DialogInterface dialog, int witch, int... args) {
                                    mStartInjuries.setText(String.valueOf(args[0]));
                                }
                            }).create();
                    mNumberPickerDialog.setCanceledOnTouchOutside(false);
                    mNumberPickerDialog.show();
                }
                break;
            case R.id.end_injuries:
                if (null == mNumberPickerDialog || (null != mNumberPickerDialog && !mNumberPickerDialog.isShowing())) {
                    mNumberPickerDialog = new NumberPickerDialog.Builder(AccidentActivity.this)
                            .setMinValue(0)
                            .setMaxValue(36)
                            .setUnit("人")
                            .setNegativeButton("清空", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mEndInjuries.setText("");
                                }
                            }).setPositiveButton("确定", new NumberPickerDialog.Builder.OnNumberPickListener() {
                                @Override
                                public void onNumberPick(DialogInterface dialog, int witch, int... args) {
                                    mEndInjuries.setText(String.valueOf(args[0]));
                                }
                            }).create();
                    mNumberPickerDialog.setCanceledOnTouchOutside(false);
                    mNumberPickerDialog.show();
                }
                break;
            case R.id.btn_search:
                if (validate()) {
                    getParams();
                    Intent intent = new Intent(AccidentActivity.this, AccidentListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Params.DATA, params);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
        }
    }
}
