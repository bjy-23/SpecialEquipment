package com.wondersgroup.special.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.constant.Params;
import com.wondersgroup.special.datepicker.DatePickerDialog;
import com.wondersgroup.special.widget.ClearEditText;

import java.util.HashMap;

/**
 * 投诉举报档案
 */
public class VoteActivity extends BaseActivity {
    private ClearEditText mCreditCode, mEntName, mDeclarant, mMainProblem;
    private TextView mStartDate, mEndDate;
    private Button mBtnSearch;
    private HashMap<String, String> params;
    private DatePickerDialog mDatePickerDialog;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_vote);
        mTitle.setText("投举档案");
        mCreditCode = (ClearEditText) findViewById(R.id.credit_code);
        mEntName = (ClearEditText) findViewById(R.id.ent_name);
        mDeclarant = (ClearEditText) findViewById(R.id.declarant);
        mMainProblem = (ClearEditText) findViewById(R.id.main_problem);
        mStartDate = (TextView) findViewById(R.id.start_date);
        mStartDate.setOnClickListener(this);
        mEndDate = (TextView) findViewById(R.id.end_date);
        mEndDate.setOnClickListener(this);
        mBtnSearch = (Button) findViewById(R.id.btn_search);
        mBtnSearch.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        params = new HashMap<>();
    }

    /**
     * 验证非空
     *
     * @return
     */
    private boolean validate() {
//        String code = mCreditCode.getText().toString();
//        if (TextUtils.isEmpty(code)) {
//            showMsg(getResources().getString(R.string.credit_code) + "不能为空");
//            return false;
//        }
//        if (!code.matches(Constant.CREDIT_CODE_REGEX) || code.length() != 7 || code.length() != 18) {
//            showMsg(Tips.CREDIT_CODE_TIP);
//            return false;
//        }
        return true;
    }

    /**
     * 获取服务器提交参数
     */
    private void getParams() {
        String code = mCreditCode.getText().toString();
        if (!TextUtils.isEmpty(code)) {
            params.put("unitCode", code);
        }
        String name = mEntName.getText().toString();
        if (!TextUtils.isEmpty(name)) {
            params.put("compObjectName", name);
        }
        String declarant = mDeclarant.getText().toString();
        if (!TextUtils.isEmpty(declarant)) {
            params.put("clientName", declarant);
        }
        String problem = mMainProblem.getText().toString();
        if (!TextUtils.isEmpty(problem)) {
            params.put("busiContent", problem);
        }
        String date = mStartDate.getText().toString();
        if (!TextUtils.isEmpty(date)) {
            params.put("startDate", date);
        }
        String src = mEndDate.getText().toString();
        if (!TextUtils.isEmpty(src)) {
            params.put("endDate", src);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_search:
                if (validate()) {
                    getParams();
                    Intent intent = new Intent(VoteActivity.this, VoteListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Params.DATA, params);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
            case R.id.start_date:
                if (null == mDatePickerDialog || (null != mDatePickerDialog && !mDatePickerDialog.isShowing())) {
                    mDatePickerDialog = new DatePickerDialog.Builder(VoteActivity.this)
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
                    mDatePickerDialog = new DatePickerDialog.Builder(VoteActivity.this)
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
        }
    }
}
