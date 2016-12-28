package com.wondersgroup.special.activity.unit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.wondersgroup.special.R;
import com.wondersgroup.special.activity.BaseActivity;
import com.wondersgroup.special.entity.AreaDicEntity;
import com.wondersgroup.special.utils.DicUtil;
import com.wondersgroup.special.widget.ClearEditText;
import java.util.HashMap;

/**
 * 从业单位
 */
public class UseUnitActivity extends BaseActivity implements DicUtil.OnChoiceListener {
    private Button mBtnSearch;
    private TextView mEquipmentType, mDivisions;
    private ClearEditText mCreditCode, mPermitsProjects, mEntName, mManage,
            mUnitAddress;
    private HashMap<String, String> params;
    private String areaCode;


    @Override
    protected void initView() {
        context = this;
        setContentView(R.layout.activity_use_unit);
        mTitle.setText("从业单位查询");
        mBtnSearch = (Button) findViewById(R.id.btn_search);
        mBtnSearch.setOnClickListener(this);
        mCreditCode = (ClearEditText) findViewById(R.id.credit_code);
        mEntName = (ClearEditText) findViewById(R.id.ent_name);
        mPermitsProjects = (ClearEditText) findViewById(R.id.permits_projects);
        mEquipmentType = (TextView) findViewById(R.id.equipment_type);
        mDivisions = (TextView) findViewById(R.id.divisions);//行政区划
        mDivisions.setOnClickListener(this);
        mEntName = (ClearEditText) findViewById(R.id.ent_name);
        mManage = (ClearEditText) findViewById(R.id.manage);
        mUnitAddress = (ClearEditText) findViewById(R.id.unit_address);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        params = new HashMap<>();
    }

    private boolean validate() {
//        String code = mCreditCode.getText().toString();
//        if (TextUtils.isEmpty(code)) {
//            showMsg(getResources().getString(R.string.credit_code) + "不能为空");
//            return false;
//        }
//        if (!code.matches(Constant.CREDIT_CODE_REGEX) && code.length() != 7 && code.length() != 18) {
//            showMsg(Tips.CREDIT_CODE_TIP);
//            return false;
//        }
        return true;
    }

    private void getParams() {
        String code = mCreditCode.getText().toString();
        if (!TextUtils.isEmpty(code)) {
            params.put("creditCode", code);
        }
        String entName = mEntName.getText().toString();
        if (!TextUtils.isEmpty(entName)) {
            params.put("unitName", entName);
        }
        String equType = mEquipmentType.getText().toString();
        if (!TextUtils.isEmpty(equType)) {
            params.put("deviceType", equType);
        }

        if (!TextUtils.isEmpty(areaCode)) {
            params.put("areaCode", areaCode);
        }
        String permitsProjects = mPermitsProjects.getText().toString();
        if (!TextUtils.isEmpty(permitsProjects)) {
            params.put("certPro", permitsProjects);
        }
        String manage = mManage.getText().toString();
        if (!TextUtils.isEmpty(manage)) {
            params.put("unitUnit", manage);
        }
        String unitAddress = mUnitAddress.getText().toString();
        if (!TextUtils.isEmpty(unitAddress)) {
            params.put("address", unitAddress);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_search:
                if (validate()) {
                    getParams();
                    Intent intent = new Intent(UseUnitActivity.this, UseUnitListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("PARAMS", params);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
            case R.id.divisions:
                DicUtil dicUtil = new DicUtil(context);
                dicUtil.setOnChoiceListener(this);
                dicUtil.makeChoice(1,"");
                break;
        }
    }

    @Override
    public void choice(AreaDicEntity entity) {
        mDivisions.setText(entity.getValue());
        areaCode = entity.getKey();
    }
}
