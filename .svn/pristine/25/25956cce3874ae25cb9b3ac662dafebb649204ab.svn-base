package com.wondersgroup.special.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.widget.ClearEditText;

public class EntPermitActivity extends BaseActivity {
    private Button mBtnSearch;
    private ClearEditText mCreditCode, mEntName;
    private TextView mEntType;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_ent_permit);
        mBtnSearch = (Button) findViewById(R.id.btn_search);
        mBtnSearch.setOnClickListener(this);
        mCreditCode = (ClearEditText) findViewById(R.id.credit_code);
        mEntName = (ClearEditText) findViewById(R.id.ent_name);
        mEntType = (TextView) findViewById(R.id.ent_type);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_search:
                startActivity(new Intent(EntPermitActivity.this, EntPermitListActivity.class));
                break;
        }
    }
}
