package com.wondersgroup.special.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.widget.ClearEditText;

public class PersonPermitActivity extends BaseActivity {
    private ClearEditText mName, mEntName;
    private TextView mSex, mPermitType;
    private Button mBtnSearch;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_person_permit);
        mBtnSearch = (Button) findViewById(R.id.btn_search);
        mBtnSearch.setOnClickListener(this);
        mName = (ClearEditText) findViewById(R.id.name);
        mEntName = (ClearEditText) findViewById(R.id.ent_name);
        mSex = (TextView) findViewById(R.id.sex);
        mSex.setOnClickListener(this);
        mPermitType = (TextView) findViewById(R.id.permit_type);
        mPermitType.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_search:
                break;
            case R.id.sex:
                break;
            case R.id.permit_type:
                break;
        }
    }
}
