package com.wondersgroup.special.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import com.wondersgroup.special.R;
import com.wondersgroup.special.widget.ClearEditText;

import java.util.HashMap;

public class ManageActivity extends BaseActivity {
    private Button mBtnSearch;
    private ClearEditText mUnitName, mUnitAddress, mEquCode, mEquAddress;
    private HashMap<String, String> params;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_manage);
        mTitle.setText("管理单元");
        mBtnSearch = (Button) findViewById(R.id.btn_search);
        mBtnSearch.setOnClickListener(this);
        mUnitName = (ClearEditText) findViewById(R.id.unit_name);
        mUnitAddress = (ClearEditText) findViewById(R.id.unit_address);
        mEquCode = (ClearEditText) findViewById(R.id.equ_code);
        mEquAddress = (ClearEditText) findViewById(R.id.equ_address);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        params = new HashMap<>();
    }

    private void getParams() {
        String name = mUnitName.getText().toString();
        if (!TextUtils.isEmpty(name)) {
            params.put("uUnitName", name);
        }
        String uAddress = mUnitAddress.getText().toString();
        if (!TextUtils.isEmpty(uAddress)) {
            params.put("uUnitAddress", uAddress);
        }
        String code = mEquCode.getText().toString();
        if (!TextUtils.isEmpty(code)) {
            params.put("deviceNum", code);
        }
        String eAddress = mEquAddress.getText().toString();
        if (!TextUtils.isEmpty(eAddress)) {
            params.put("deviceAddress", eAddress);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_search:
                getParams();
                Intent intent = new Intent(ManageActivity.this, ManageListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("PARAMS", params);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
}
