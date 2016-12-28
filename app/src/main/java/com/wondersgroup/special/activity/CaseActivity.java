package com.wondersgroup.special.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baidu.platform.comapi.map.C;
import com.wondersgroup.special.R;
import com.wondersgroup.special.constant.Params;
import com.wondersgroup.special.widget.ClearEditText;

import java.util.HashMap;

public class CaseActivity extends BaseActivity {
    private Button mBtnSearch;
    private ClearEditText mFilingNumber, mCaseTitle, mCaseUnit, mStartLoss, mEndLoss;
    private HashMap<String, String> params;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_case);
        mTitle.setText("案件档案");
        mBtnSearch = (Button) findViewById(R.id.btn_search);
        mBtnSearch.setOnClickListener(this);
        mFilingNumber = (ClearEditText) findViewById(R.id.filing_number);
        mCaseTitle = (ClearEditText) findViewById(R.id.case_title);
        mCaseUnit = (ClearEditText) findViewById(R.id.case_unit);
        mStartLoss = (ClearEditText) findViewById(R.id.start_loss);
        mEndLoss = (ClearEditText) findViewById(R.id.end_loss);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        params = new HashMap<>();
    }

    private void getParams() {
        params.put("unitName", mCaseUnit.getText().toString());
        params.put("caseNo", mFilingNumber.getText().toString());
        params.put("caseTitle", mCaseTitle.getText().toString());
        params.put("numS", mStartLoss.getText().toString());
        params.put("numE", mEndLoss.getText().toString());
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_search:
                getParams();
                Intent intent = new Intent(CaseActivity.this, CaseListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Params.DATA, params);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
}
