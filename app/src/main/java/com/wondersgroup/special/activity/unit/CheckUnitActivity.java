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
 * 检验检测单位
 */
public class CheckUnitActivity extends BaseActivity implements DicUtil.OnChoiceListener {
    private Button mBtnSearch;
    private ClearEditText mCreditCode, mEntAddress, mCheckPro;
    private TextView mOrganNature, mAreaCode;
    private HashMap<String, String> params;
    private String areaCode,organCode;
    private int dicType;

    @Override
    protected void initView() {
        context = this;
        setContentView(R.layout.activity_check_unit);
        mTitle.setText("检验检测单位");
        mBtnSearch = (Button) findViewById(R.id.btn_search);
        mBtnSearch.setOnClickListener(this);
        mCreditCode = (ClearEditText) findViewById(R.id.credit_code);
        mEntAddress = (ClearEditText) findViewById(R.id.ent_address);
        mOrganNature = (TextView) findViewById(R.id.organ_nature);
        mOrganNature.setOnClickListener(this);
        mCheckPro = (ClearEditText) findViewById(R.id.check_pro);
        mAreaCode = (TextView) findViewById(R.id.area_code);
        mAreaCode.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        params = new HashMap<>();
    }

    private boolean validate() {
        String code = mCreditCode.getText().toString();
//        if (TextUtils.isEmpty(code)) {
//            showMsg(getResources().getString(R.string.credit_code) + "不能为空");
//            return false;
//        }
//        if (!TextUtils.isEmpty(code) && !code.matches(Constant.CREDIT_CODE_REGEX)) {
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
            params.put("creditCode", code);
        }

        if (!TextUtils.isEmpty(organCode)) {
            params.put("organNature", organCode);
        }
        String pro = mCheckPro.getText().toString();
        if (!TextUtils.isEmpty(pro)) {
            params.put("certPro", pro);
        }
        String address = mEntAddress.getText().toString();
        if (!TextUtils.isEmpty(address)) {
            params.put("address", address);
        }

        if (!TextUtils.isEmpty(areaCode)) {
            params.put("areaCode", areaCode);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_search:
                if (validate()) {
                    getParams();
                    Intent intent = new Intent(CheckUnitActivity.this, CheckUnitListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("PARAMS", params);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
            case R.id.area_code:
                dicType = 1;
                DicUtil dicUtil = new DicUtil(context);
                dicUtil.setOnChoiceListener(this);
                dicUtil.makeChoice(dicType,"");
                break;

            case R.id.organ_nature:
                dicType = 2;
                DicUtil dicUtil2 = new DicUtil(context);
                dicUtil2.setOnChoiceListener(this);
                dicUtil2.makeChoice(dicType,"");
                break;
        }
    }

    @Override
    public void choice(AreaDicEntity entity) {
        switch (dicType){
            case 1:
                mAreaCode.setText(entity.getValue());
                areaCode = entity.getKey();
                break;
            case 2:
                mOrganNature.setText(entity.getValue());
                organCode = entity.getKey();
                break;
        }

    }
}
