package com.wondersgroup.special.employment.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.wondersgroup.special.employment.constant.Details;
import com.wondersgroup.special.R;
import com.wondersgroup.special.ResponseCallBack;
import com.wondersgroup.special.activity.BaseActivity;
import com.wondersgroup.special.constant.UrlConstant;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Iterator;
import java.util.LinkedHashMap;

import okhttp3.Call;
import okhttp3.Request;

public class DetailActivity extends BaseActivity{
    private Context context;
    private String url;
    private String uuid;
    private LinearLayout mLinearDetail,layoutIndex,layoutPermission;     ;
    private int maxLine;
    private static String mType;
    private String certType;
    private LinkedHashMap<String,String> dataNet,dataLocal,dataShow,dataHint;
    private boolean isShow;
    private ImageView imgIndex;

    @Override
    protected void initView() {
        context = this;
        setContentView(R.layout.activity_detail);
        mLinearDetail = (LinearLayout) findViewById(R.id.activity_detail);
        layoutIndex = (LinearLayout) findViewById(R.id.layout_index);
        layoutIndex.setOnClickListener(this);
        imgIndex = (ImageView) findViewById(R.id.img_index);
        layoutPermission = (LinearLayout) findViewById(R.id.layout_permission);
        layoutPermission.setOnClickListener(this);
        mLinearDetail.removeAllViews();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mType = getIntent().getStringExtra("type");
        uuid = getIntent().getStringExtra("uuid");
        certType = getIntent().getStringExtra("certType");
        if ("02".equals(certType))
            layoutPermission.setVisibility(View.VISIBLE);

        maxLine = 3;

        switch (mType) {
            case "0":
                url = UrlConstant.QUERY_PERSON_DETAIL_INFO_BY_ID;
                mTitle.setText("从业人员基本信息");
                break;
            case "1":
                url = UrlConstant.QUERY_PERSON_EXPAND_INFO_DETAIL;
                mTitle.setText("工作履历详情");
                break;
            case "21":
            case "22":
                url = UrlConstant.QUERY_PERSON_EXPAND_INFO_DETAIL;
                mTitle.setText("许可信息详情");
                break;
            case "3":
                url = UrlConstant.QUERY_PERSON_EXPAND_INFO_DETAIL;
                mTitle.setText("奖惩信息详情");
                break;
            case "4":
                url = UrlConstant.QUERY_PERSON_EXPAND_INFO_DETAIL;
                mTitle.setText("培训信息详情");
                break;
            case "5":
                url = UrlConstant.QUERY_PERSON_EXPAND_INFO_DETAIL;
                mTitle.setText("考核信息详情");
                break;
            case "6":
                url = UrlConstant.QUERY_PERSON_EXPAND_INFO_DETAIL;
                mTitle.setText("变更信息详情");
                break;

            default:
                break;
        }
        getData();
    }


    private void getData() {
        OkHttpUtils.get().url(url)
                .addParams("uuid", uuid)
                .addParams("type",mType)
                .build().
                execute(new ResponseCallBack<JsonElement>() {
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
                    dataNet = new Gson().fromJson(result, type);
                    dataLocal = getDataLocal(DetailActivity.mType);
                    dataHint = getDataLocal(DetailActivity.mType);
                    dataShow = new LinkedHashMap<String, String>();
                    Iterator ite = dataLocal.keySet().iterator();
                    while (ite.hasNext()) {
                        String key = (String) ite.next();
                        String keyReal = dataLocal.get(key);
                        String value = dataNet.get(key);
                        mLinearDetail.addView(getItemView(keyReal, value));

                        if ("02".equals(certType)) {
                            dataHint.remove(key);
                            dataShow.put(key, value);
                            --maxLine;
                            if (maxLine==0){
                                layoutIndex.setVisibility(View.VISIBLE);
                                break;
                            }
                        }
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


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_index:
                addView();
                break;
            case R.id.layout_permission:
                startActivity(new Intent(context,PermissonProActivity.class).putExtra("uuid",uuid));
                break;
            case R.id.image_back:
                finish();
                break;
        }
    }

    public void addView(){
        Iterator ite = null;
        if (!isShow){
            ite = dataHint.keySet().iterator();
            isShow = true;
            imgIndex.setImageResource(R.mipmap.icon_arrow_up);
        }else {
            mLinearDetail.removeAllViews();
            ite = dataShow.keySet().iterator();
            isShow = false;
            imgIndex.setImageResource(R.mipmap.icon_arrow_down);
        }
        while (ite.hasNext()) {
            String key = (String) ite.next();
            String keyReal = dataLocal.get(key);
            String value = dataNet.get(key);
            mLinearDetail.addView(getItemView(keyReal, value));

        }
    }

    public LinkedHashMap<String,String> getDataLocal(String type){
        LinkedHashMap<String,String> linkedHashMap = null;
        switch (type){
            case "0":
                linkedHashMap = Details.getEBasicInfo();
                break;
            case "1":
                linkedHashMap = Details.getEWorkInfo();
                break;
            case "21":
                linkedHashMap = Details.getEPermissionInfo();
                break;
            case "22":
                linkedHashMap = Details.getEPermissionProInfo();
                break;
            case "3":
                linkedHashMap = Details.getERewardInfo();
                break;
            case "4":
                linkedHashMap = Details.getETrainInfo();
                break;
            case "5":
                linkedHashMap = Details.getECheckInfo();
                break;
            case "6":
                linkedHashMap = Details.getEChangefo();
                break;
        }
        return linkedHashMap;
    }
}
