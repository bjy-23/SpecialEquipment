package com.wondersgroup.special.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.wondersgroup.special.R;
import com.wondersgroup.special.ResponseCallBack;
import com.wondersgroup.special.constant.ArchiveConstant;
import com.wondersgroup.special.constant.Params;
import com.wondersgroup.special.constant.UrlConstant;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

public class UnitDetailActivity extends BaseActivity {
    private LinearLayout mLinearDetail;
    private LinkedHashMap<String, String> data;
    private String url, type, uuid, mUnitType;
    private HashMap<String, String> params;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_detail);
        mLinearDetail = (LinearLayout) findViewById(R.id.activity_detail);
        mLinearDetail.removeAllViews();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        type = getIntent().getStringExtra(Params.TYPE);
        uuid = getIntent().getStringExtra(Params.UUID);
        mUnitType = getIntent().getStringExtra(Params.UNIT_TYPE);
        params = new HashMap<>();
        params.clear();
        if (!TextUtils.isEmpty(type)) {
            switch (type) {
                case ArchiveConstant.Unit.CHECK_UNIT:
                    url = UrlConstant.GET_CHECK_UNIT;
                    mTitle.setText("检验检测单位-基本信息");
                    break;
                case ArchiveConstant.Unit.UNIT:
                    url = UrlConstant.GET_UNIT_INFO;
                    mTitle.setText("从业单位-基本信息");
                    break;
                case ArchiveConstant.Unit.PRO_UNIT:
                    url = UrlConstant.GET_PRP_UNIT_INFO;
                    mTitle.setText("生产单位-基本信息");
                    break;
                default:
                    break;
            }
        }
        if (!TextUtils.isEmpty(uuid) && !TextUtils.isEmpty(mUnitType)) {
            params.put("uuid", uuid);
            params.put("unitType", mUnitType);
            getUnitPermit();
        }
    }

    /**
     * 单位许可
     */
    private void getUnitPermit() {
        OkHttpUtils.get().
                url(url)
                .params(params)
                .build()
                .execute(new ResponseCallBack<JsonElement>() {
                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                        showLoadingDialog();
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
                        dismissLoadingDialog();
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        super.onError(call, e);
                        showMsg(e.getMessage());
                    }

                    @Override
                    public void onResponse(List<JsonElement> result) {
                        super.onResponse(result);
                        if (null != result) {
                            showView(result);
                        }
                    }
                });
    }


    /**
     * 显示界面
     */
    private void showView(List<JsonElement> result) {
//        Type listType = new ResponseCallBack.ParameterizedTypeImpl(List.class, new Class[]{JsonElement.class});
//        List<JsonElement> listResult = new Gson().fromJson(result, listType);
        for (int i = 0; i < result.size(); i++) {
            java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<LinkedHashMap>() {
            }.getType();
            LinkedHashMap ret = new Gson().fromJson(result.get(i), type);
            Iterator ite = ret.keySet().iterator();
            while (ite.hasNext()) {
                String key = ite.next().toString();
                String value = (ret.get(key) == null || TextUtils.isEmpty(ret.get(key).toString())) ? "" : ret.get(key).toString();
                mLinearDetail.addView(getItemView(key, value));
            }
        }
    }

    private View getItemView(String strText, String strValue) {
        View view = LayoutInflater.from(UnitDetailActivity.this).inflate(R.layout.item_recycler, null);
        TextView text = (TextView) view.findViewById(R.id.text);
        TextView value = (TextView) view.findViewById(R.id.value);
        text.setText(strText);
        value.setText(strValue);
        return view;
    }
}
