package com.wondersgroup.special.archive;

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
import com.wondersgroup.special.activity.BaseActivity;
import com.wondersgroup.special.constant.ArchiveConstant;
import com.wondersgroup.special.constant.Constant;
import com.wondersgroup.special.constant.Params;
import com.wondersgroup.special.constant.UrlConstant;
import com.zhy.http.okhttp.OkHttpUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

public class ArchiveDetailActivity extends BaseActivity {
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
                case ArchiveConstant.UNIT_PERMIT:
                    url = UrlConstant.GET_UNIT_LICENSE;
                    mTitle.setText("单位许可详情");
                    if (!TextUtils.isEmpty(uuid) && !TextUtils.isEmpty(mUnitType)) {
                        params.put("uuid", uuid);
                        params.put("certUnitType", mUnitType);
                        getUnitPermit();
                    }
                    break;
                case ArchiveConstant.ACCIDENT:
                    url = UrlConstant.GET_ACCIDENT_INFO;
                    mTitle.setText("事故档案详情");
                    if (!TextUtils.isEmpty(uuid)) {
                        params.put("uuid", uuid);
                        getUnitPermit();
                    }
                    break;
                case ArchiveConstant.Checkout.BASE_INFO:
                    url = UrlConstant.GET_CHECKOUT_INFO;
                    mTitle.setText("检验档案详情");
                    if (!TextUtils.isEmpty(uuid)) {
                        params.put("uuid", uuid);
                        params.put("type", Constant.CheckoutRoutineType.CHECKOUT);
                        getUnitPermit();
                    }
                    break;
                case ArchiveConstant.CASE:
                    url = UrlConstant.GET_CASE_INFO;
                    mTitle.setText("行政处罚详情");
                    if (!TextUtils.isEmpty(uuid)) {
                        params.put("uuid", uuid);
                        getUnitPermit();
                    }
                    break;
                case ArchiveConstant.COMP:
                    url = UrlConstant.GET_COMP_INFO;
                    mTitle.setText("投举档案详情");
                    if (!TextUtils.isEmpty(uuid)) {
                        params.put("uuid", uuid);
                        getUnitPermit();
                    }
                    break;
                case ArchiveConstant.MANAGE:
                    url = UrlConstant.GET_UNIT_UNIT;
                    mTitle.setText("管理单元详情");
                    if (!TextUtils.isEmpty(uuid)) {
                        params.put("uuid", uuid);
                        getUnitPermit();
                    }
                    break;
                case ArchiveConstant.Routine.BASE_INFO:
                    url = UrlConstant.GET_SUPERVISION_INFO;
                    mTitle.setText("监察档案-基本信息");
                    if (!TextUtils.isEmpty(uuid)) {
                        params.put("uuid", uuid);
                        params.put("type", Constant.CheckoutRoutineType.ROUTINE);
                        getUnitPermit();
                    }
                    break;
                case ArchiveConstant.Routine.OVERVIEW:
                    url = UrlConstant.GET_SUPERVISION_VIEW;
                    mTitle.setText("监察档案-检查概况");
                    if (!TextUtils.isEmpty(uuid)) {
                        params.put("uuid", uuid);
                        getUnitPermit();
                    }
                    break;
                case ArchiveConstant.Routine.SITUATION:
                    url = UrlConstant.GET_SUPERVISION_SITUATION;
                    mTitle.setText("监察档案-检查情况");
                    if (!TextUtils.isEmpty(uuid)) {
                        params.put("uuid", uuid);
                        getUnitPermit();
                    }
                    break;
                case ArchiveConstant.DeviceBaseInfo.DEVICE_MARK:
                    url = UrlConstant.GET_EQUIP_BASIC_INFO;
                    mTitle.setText("标识信息");
                    if (!TextUtils.isEmpty(uuid)) {
                        params.put("uuid", uuid);
                        params.put("deviceType1",mUnitType);
                        getUnitPermit();
                    }
                    break;
                case ArchiveConstant.DeviceBaseInfo.DEVICE_PRODUCT:
                    url = UrlConstant.GET_EQUIP_MAKE_INFO;
                    mTitle.setText("制造信息");
                    if (!TextUtils.isEmpty(uuid)) {
                        params.put("uuid", uuid);
                        params.put("deviceType1",mUnitType);
                        getUnitPermit();
                    }
                    break;
                case ArchiveConstant.DeviceBaseInfo.DEVICE_DESIGN:
                    url = UrlConstant.GET_EQUIP_DESIGN_INFO;
                    mTitle.setText("设计信息");
                    if (!TextUtils.isEmpty(uuid)) {
                        params.put("uuid", uuid);
                        params.put("deviceType1",mUnitType);
                        getUnitPermit();
                    }
                    break;
                case ArchiveConstant.DeviceBaseInfo.DEVICE_PROPERTY:
                    url = UrlConstant.GET_EQUIP_PROPERTY_RIGHT;
                    mTitle.setText("产权信息");
                    if (!TextUtils.isEmpty(uuid)) {
                        params.put("uuid", uuid);
                        params.put("deviceType1",mUnitType);
                        getUnitPermit();
                    }
                    break;
                case ArchiveConstant.UNIT_AWARD:
                    url = UrlConstant.GET_UNIT_AWARD;
                    mTitle.setText("获奖记录详情");
                    if (!TextUtils.isEmpty(uuid)) {
                        params.put("uuid", uuid);
                        getUnitPermit();
                    }
                    break;
            }
        }


        // 保持服务器json顺序
//        java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<LinkedHashMap>() {
//        }.getType();
//        LinkedHashMap ret = new Gson().fromJson(t.toString(), type);
//        Iterator ite = ret.keySet().iterator();
//        while (ite.hasNext()) {
//            String key = ite.next().toString();
//            String value = ret.get(key).toString();
//            TextView tx = getTextView();
//            // 去掉框架自带的key值
//            if (key.equals("attributeNames") || key.equals("primaryKey") || key.equals("selected")
//                    || key.equals("tableName")) {
//                continue;
//            }
//            tx.setText(key + "：" + value);
//            mLinearMeat.addView(tx);
//        }
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
        View view = LayoutInflater.from(ArchiveDetailActivity.this).inflate(R.layout.item_recycler, null);
        TextView text = (TextView) view.findViewById(R.id.text);
        TextView value = (TextView) view.findViewById(R.id.value);
        text.setText(strText);
        value.setText(strValue);
        return view;
    }
}
