package com.wondersgroup.special.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.ResponseCallBack;
import com.wondersgroup.special.constant.UrlConstant;
import com.wondersgroup.special.entity.PersonLicenseResult;
import com.wondersgroup.special.entity.UnitLicenseResult;
import com.wondersgroup.special.entity.UnitUnitResult;
import com.wondersgroup.special.widget.ClearEditText;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;

/**
 * Created by bjy on 2016/12/5.
 */

public class QueryRecordActivity extends BaseActivity {
    private Button mBtnSearch;
    private ClearEditText mCreditCode, mEntName, nameEditText, idCardEditText, unitNameEditText, unitAddressEditText, deviceNumberEditText, deviceAddressEditText;
    private TextView mEntType, permissionTypeTextView;
    private RadioButton btnMan, btnGirl;
    private int type;
    private String pageNo = "1", pageSize = "10";
    private HashMap<String, String> params;
    private String URL;
    private String[] titles = new String[]{"", "单位许可", "人员许可", "管理单元"};

    @Override
    protected void initView() {
        type = getIntent().getIntExtra("type", 0);
        mTitle.setText(titles[type]);
        switch (type) {
            case RecordActivity.TYPE_1:
                setContentView(R.layout.activity_query_unit_license);
                mBtnSearch = (Button) findViewById(R.id.btn_search);
                mBtnSearch.setOnClickListener(this);
                mCreditCode = (ClearEditText) findViewById(R.id.credit_code);
                mEntName = (ClearEditText) findViewById(R.id.ent_name);
                mEntType = (TextView) findViewById(R.id.ent_type);
                break;
            case RecordActivity.TYPE_2:
                setContentView(R.layout.activity_query_person_license);
                mBtnSearch = (Button) findViewById(R.id.btn_search);
                mBtnSearch.setOnClickListener(this);
                nameEditText = (ClearEditText) findViewById(R.id.name);
                idCardEditText = (ClearEditText) findViewById(R.id.id_card);
                permissionTypeTextView = (TextView) findViewById(R.id.permission_type);
                btnMan = (RadioButton) findViewById(R.id.btn_man);
                btnGirl = (RadioButton) findViewById(R.id.btn_girl);
                break;
            case RecordActivity.TYPE_3:
                setContentView(R.layout.activity_query_unit_unit);
                mBtnSearch = (Button) findViewById(R.id.btn_search);
                mBtnSearch.setOnClickListener(this);
                unitNameEditText = (ClearEditText) findViewById(R.id.unit_name);
                unitAddressEditText = (ClearEditText) findViewById(R.id.unit_address);
                deviceNumberEditText = (ClearEditText) findViewById(R.id.device_number);
                deviceAddressEditText = (ClearEditText) findViewById(R.id.device_address);
                break;
        }

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent = new Intent(QueryRecordActivity.this, RecordActivity.class);
        intent.putExtra("type", type);
        params = new HashMap<>();
        switch (v.getId()) {
            case R.id.btn_search:
                params = new HashMap<>();
                switch (type) {
                    case 1:
                        params.put("unitName", mCreditCode.getText().toString());
                        params.put("socialCreditCode", mEntName.getText().toString());
                        params.put("unitType", mEntType.getText().toString());
                        params.put("pageNo", pageNo);
                        params.put("pageSize", pageSize);
                        URL = UrlConstant.QUERY_UNIT_LICENSE;

//                        OkHttpUtils.get()
//                                .url(URL)
//                                .params(params)
//                                .build()
//                                .execute(new ResponseCallBack<UnitLicenseResult>() {
//                                    @Override
//                                    public void onResponse(UnitLicenseResult unitLicenseResult) {
//                                        super.onResponse(unitLicenseResult);
//                                        Intent intent = new Intent(QueryRecordActivity.this, RecordActivity.class);
//                                        intent.putExtra("datas", unitLicenseResult);
//                                        intent.putExtra("type", type);
//                                        intent.putExtra("params", params);
//                                        startActivity(intent);
//                                    }
//                                });
                        break;
                    case 2:
                        params.put("name", nameEditText.getText().toString());
                        params.put("idCard", idCardEditText.getText().toString());
                        params.put("sex", (btnMan.isChecked() || btnGirl.isChecked()) ? (btnMan.isChecked() ? "1" : "0") : (""));
                        params.put("certType", permissionTypeTextView.getText().toString());
                        params.put("pageNo", pageNo);
                        params.put("pageSize", pageSize);
                        URL = UrlConstant.QUERY_PERSON_LICENSE;
//                        OkHttpUtils.get()
//                                .url(URL)
//                                .params(params)
//                                .build()
//                                .execute(new ResponseCallBack<PersonLicenseResult>() {
//                                    @Override
//                                    public void onResponse(PersonLicenseResult personLicenseResult) {
//                                        super.onResponse(personLicenseResult);
//                                        Intent intent = new Intent(QueryRecordActivity.this, RecordActivity.class);
//                                        intent.putExtra("datas", personLicenseResult);
//                                        intent.putExtra("type", type);
//                                        intent.putExtra("params", params);
//                                        startActivity(intent);
//                                    }
//                                });
                        break;
                    case 3:
                        params.put("uUnitName", unitNameEditText.getText().toString());
                        params.put("uUnitAddress", unitAddressEditText.getText().toString());
                        params.put("deviceNum", deviceNumberEditText.getText().toString());
                        params.put("deviceAddress", deviceAddressEditText.getText().toString());
                        params.put("pageNo", pageNo);
                        params.put("pageSize", pageSize);
                        URL = UrlConstant.QUERY_UNIT_UNIT;

//                        OkHttpUtils.get()
//                                .url(URL)
//                                .params(params)
//                                .build()
//                                .execute(new ResponseCallBack<UnitUnitResult>() {
//                                    @Override
//                                    public void onResponse(UnitUnitResult unitUnitResult) {
//                                        super.onResponse(unitUnitResult);
//                                        Intent intent = new Intent(QueryRecordActivity.this, RecordActivity.class);
//                                        intent.putExtra("datas", unitUnitResult);
//                                        intent.putExtra("type", type);
//                                        intent.putExtra("params", params);
//                                        startActivity(intent);
//                                    }
//                                });

                }
                intent.putExtra("params", params);
                startActivity(intent);
        }
    }
}
