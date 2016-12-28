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
import com.wondersgroup.special.constant.Constant;
import com.wondersgroup.special.constant.UrlConstant;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Iterator;
import java.util.LinkedHashMap;

import okhttp3.Call;
import okhttp3.Request;

public class DetailActivity extends BaseActivity {
    private String url;
    private String uuid;
    private LinearLayout mLinearDetail;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_detail);
        mLinearDetail = (LinearLayout) findViewById(R.id.activity_detail);
        mLinearDetail.removeAllViews();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        String info = TextUtils.isEmpty(bundle.getString("INFO")) ? "" : bundle.getString("INFO");
        uuid = TextUtils.isEmpty(bundle.getString("UUID")) ? "" : bundle.getString("UUID");
        if (!TextUtils.isEmpty(info) && !TextUtils.isEmpty(uuid)) {
            switch (info) {
                case Constant.Info.EQUIP_BASIC_INFO:
                    url = UrlConstant.GET_EQUIP_BASIC_INFO;
                    break;
                case Constant.Info.EQUIP_REG_INFO:
                    url = UrlConstant.GET_EQUIP_REGISTER_INFO;
                    break;
                case Constant.Info.EQUIP_MAKE_INFO:
                    url = UrlConstant.GET_EQUIP_MAKE_INFO;
                    break;
                case Constant.Info.EQUIP_DESIGN_INFO:
                    url = UrlConstant.GET_EQUIP_DESIGN_INFO;
                    break;
                case Constant.Info.PERSON_DETAIL_INFO:
                    url = UrlConstant.QUERY_PERSON_DETAIL_INFO_BY_ID;
                    mTitle.setText("从业人员基本信息");
                    break;
                case Constant.Info.PERSON_LICENSE_INFO:
                    url = UrlConstant.GET_PERSON_LICENSE;
                    mTitle.setText("从业人员许可信息");
                    break;
                case Constant.Info.PERSON_AWARD_INFO:
                    url = UrlConstant.QUERY_PERSON_AWARD_DETAIL;
                    mTitle.setText("从业人员奖惩信息");
                    break;
                case Constant.Info.CHECK_UNIT_INFO:
                    url = UrlConstant.GET_CHECK_UNIT;
                    mTitle.setText("检验检测单位基本信息");
                    break;
                default:
                    break;
            }
            getData();
        }
    }

    private void getData() {
        OkHttpUtils.get().url(url).addParams("uuid", uuid).build().execute(new ResponseCallBack<JsonElement>() {
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
            public void onResponse(JsonElement result) {
                super.onResponse(result);
                if (null != result) {
                    // 保持服务器json顺序
                    java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<LinkedHashMap>() {
                    }.getType();
                    LinkedHashMap ret = new Gson().fromJson(result, type);
                    Iterator ite = ret.keySet().iterator();
                    while (ite.hasNext()) {
                        String key = (String) ite.next();
                        if (key.equals("attributeNames")
                                || key.equals("uuid")
                                || key.equals("primaryKey")
                                || key.equals("selected")
                                || key.equals("tableName")) {
                            continue;
                        }
                        mLinearDetail.addView(getItemView(key, (String) ret.get(key)));
                    }
                }
            }

            @Override
            public void onError(Call call, Exception e) {
                super.onError(call, e);
                showMsg(e.getMessage());
            }
        });
    }

    private View getItemView(String strText, String strValue) {
        View view = LayoutInflater.from(DetailActivity.this).inflate(R.layout.item_recycler, null);
        TextView text = (TextView) view.findViewById(R.id.text);
        TextView value = (TextView) view.findViewById(R.id.value);
        text.setText(strText);
        value.setText(strValue);
        return view;
    }
}
