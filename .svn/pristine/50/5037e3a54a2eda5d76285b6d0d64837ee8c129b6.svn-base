package com.wondersgroup.special.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wondersgroup.special.R;
import com.wondersgroup.special.ResponseCallBack;
import com.wondersgroup.special.constant.UrlConstant;
import com.wondersgroup.special.entity.GetAccidentEntity;
import com.wondersgroup.special.entity.QueryAccidentResult;
import com.wondersgroup.special.entity.QueryCheckoutEntity;
import com.wondersgroup.special.entity.QueryCheckoutResult;
import com.wondersgroup.special.entity.UnitApprovalEntity;
import com.wondersgroup.special.entity.UnitPermissionEnitity;
import com.wondersgroup.special.entity.UnitPermissionResult;
import com.wondersgroup.special.fragment.ArchiveFragment;
import com.wondersgroup.special.fragment.BaseFragment;
import com.wondersgroup.special.fragment.CategoryFragment;
import com.wondersgroup.special.fragment.HomeFragment;
import com.wondersgroup.special.fragment.SettingFragment;
import com.wondersgroup.special.fragment.WarningFragment;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Response;


public class MainActivity extends BaseActivity {
    private long exitTime = 0;
    private LinearLayout mLinearHome, mLinearCategory, mLinearArchive, mLinearWarning, mLinearSetting;
    private ImageView mImageHome, mImageCategory, mImageArchive, mImageWarning, mImageSetting;
    private TextView mTextHome, mTextCategory, mTextArchive, mTextWarning, mTextSetting;
    private int currentIndex = -1;
    private FragmentManager manager = getSupportFragmentManager();
    private List<BaseFragment> arrFragments;

    @Override
    protected void initView() {
//        testInterface();
        setContentView(R.layout.activity_main);
        setNavigationVisible(false);
        setCustomTitle(R.string.home);
        mLinearHome = (LinearLayout) findViewById(R.id.linear_home);
        mLinearCategory = (LinearLayout) findViewById(R.id.linear_category);
        mLinearArchive = (LinearLayout) findViewById(R.id.linear_archive);
        mLinearWarning = (LinearLayout) findViewById(R.id.linear_warning);
        mLinearSetting = (LinearLayout) findViewById(R.id.linear_setting);
        mImageHome = (ImageView) findViewById(R.id.image_home);
        mImageCategory = (ImageView) findViewById(R.id.image_category);
        mImageArchive = (ImageView) findViewById(R.id.image_archive);
        mImageWarning = (ImageView) findViewById(R.id.image_warning);
        mImageSetting = (ImageView) findViewById(R.id.image_setting);
        mTextHome = (TextView) findViewById(R.id.text_home);
        mTextCategory = (TextView) findViewById(R.id.text_category);
        mTextArchive = (TextView) findViewById(R.id.text_archive);
        mTextWarning = (TextView) findViewById(R.id.text_warning);
        mTextSetting = (TextView) findViewById(R.id.text_setting);
        mLinearHome.setOnClickListener(this);
        mLinearCategory.setOnClickListener(this);
        mLinearArchive.setOnClickListener(this);
        mLinearWarning.setOnClickListener(this);
        mLinearSetting.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        arrFragments = new ArrayList<>();
        arrFragments.add(new HomeFragment());
        arrFragments.add(new CategoryFragment());
        arrFragments.add(new ArchiveFragment());
        arrFragments.add(new WarningFragment());
        arrFragments.add(new SettingFragment());
        setCurrentTab(0);
    }

    /**
     * 设置当前tab
     *
     * @param position
     */
    private void setCurrentTab(int position) {
        Drawable drawable;
        int unSelectedTextColor = getResources().getColor(R.color.text_grey);
        int selectedTextColor = getResources().getColor(R.color.theme);
        switch (currentIndex) {
            case -1:
                break;
            case 0:
                drawable = getResources().getDrawable(R.mipmap.icon_home_unselected);
                mImageHome.setImageDrawable(drawable);
                mTextHome.setTextColor(unSelectedTextColor);
                break;
            case 1:
                drawable = getResources().getDrawable(R.mipmap.icon_fenlei_unselected);
                mImageCategory.setImageDrawable(drawable);
                mTextCategory.setTextColor(unSelectedTextColor);
                break;
            case 2:
                drawable = getResources().getDrawable(R.mipmap.icon_dangan_unselected);
                mImageArchive.setImageDrawable(drawable);
                mTextArchive.setTextColor(unSelectedTextColor);
                break;
            case 3:
                drawable = getResources().getDrawable(R.mipmap.icon_yujing_unselected);
                mImageWarning.setImageDrawable(drawable);
                mTextWarning.setTextColor(unSelectedTextColor);
                break;
            case 4:
                drawable = getResources().getDrawable(R.mipmap.icon_shezhi_unselected);
                mImageSetting.setImageDrawable(drawable);
                mTextSetting.setTextColor(unSelectedTextColor);
                break;
        }
        switch (position) {
            case 0:
                drawable = getResources().getDrawable(R.mipmap.icon_home_selected);
                mImageHome.setImageDrawable(drawable);
                mTextHome.setTextColor(selectedTextColor);
                break;
            case 1:
                drawable = getResources().getDrawable(R.mipmap.icon_fenlei_selected);
                mImageCategory.setImageDrawable(drawable);
                mTextCategory.setTextColor(selectedTextColor);
                break;
            case 2:
                drawable = getResources().getDrawable(R.mipmap.icon_dangan_selected);
                mImageArchive.setImageDrawable(drawable);
                mTextArchive.setTextColor(selectedTextColor);
                break;
            case 3:
                drawable = getResources().getDrawable(R.mipmap.icon_yujing_selected);
                mImageWarning.setImageDrawable(drawable);
                mTextWarning.setTextColor(selectedTextColor);
                break;
            case 4:
                drawable = getResources().getDrawable(R.mipmap.icon_shezhi_selected);
                mImageSetting.setImageDrawable(drawable);
                mTextSetting.setTextColor(selectedTextColor);
                break;
        }
        replace(position);
        currentIndex = position;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.linear_home:
                setCurrentTab(0);
                break;
            case R.id.linear_category:
                setCurrentTab(1);
                break;
            case R.id.linear_archive:
                setCurrentTab(2);
                break;
            case R.id.linear_warning:
                setCurrentTab(3);
                break;
            case R.id.linear_setting:
                setCurrentTab(4);
                break;
            default:
                break;
        }
    }

    private void replace(int position) {
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = null;
        Fragment current = null;
//            Bundle bundle = new Bundle();
//            bundle.putString("title", "aaa" + position);
        fragment = arrFragments.get(position);
//            fragment.setArguments(bundle);
        current = arrFragments.get(currentIndex == -1 ? 0 : currentIndex);
        if (current != null && current.isVisible()) {
            transaction.hide(current);
        }
        if (!fragment.isAdded()) {
            transaction.add(R.id.frame_content, fragment).commit();
        } else {
            transaction.show(fragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), getString(R.string.logout_app_one_time), Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }

    public void testInterface(){
        HashMap<String,String> params = new HashMap<>();
        params.clear();
        params.put("pageNo","1");
        params.put("pageSize","10");
        params.put("unitName","");
        params.put("socialCreditCode","");
        params.put("unitType","");

        OkHttpUtils.get().
                url(UrlConstant.QUERY_UNIT_LICENSE)
                .params(params)
                .build()
                .execute(new ResponseCallBack<UnitPermissionResult>() {
                    @Override
                    public void onResponse(UnitPermissionResult unitPermissionResult) {
                        super.onResponse(unitPermissionResult);
                    }
                });

        params.clear();
        params.put("uuid","123456789");
        OkHttpUtils.get().
                url(UrlConstant.GET_UNIT_LICENSE)
                .params(params)
                .build()
                .execute(new ResponseCallBack<UnitPermissionEnitity>() {
                    @Override
                    public void onResponse(UnitPermissionEnitity unitPermissionEnitity) {
                        super.onResponse(unitPermissionEnitity);
                    }
                });

//        params.clear();
//        params.put("uuid","123456789");
//        OkHttpUtils.get().
//                url(UrlConstant.GET_LICENSE_PRO)
//                .params(params)
//                .build()
//                .execute(new ResponseCallBack<UnitApprovalEntity>() {
//                    @Override
//                    public void onResponse(List<UnitApprovalEntity> t) {
//                        super.onResponse(t);
//                    }
//                });

//        params.clear();
//        params.put("pageNo","1");
//        params.put("pageSize","10");
//        params.put("unitName","");
//        params.put("unitId","");
//        params.put("unitCode","");
//        params.put("areaCode","");
//        params.put("type","");
//        params.put("startDate","");
//        params.put("endDate","");
//        params.put("deadNumS","");
//        params.put("deadNumE","");
//        params.put("hurtNumS","");
//        params.put("hurtNumE","");
//        params.put("moneyNumS","");
//        params.put("moneyNumE","");
//        params.put("deviceId","");
//
//        OkHttpUtils.get()
//                .url(UrlConstant.QUERY_ACCIDENT_INFO)
//                .params(params)
//                .build()
//                .execute(new ResponseCallBack<QueryAccidentResult>() {
//                    @Override
//                    public void onResponse(QueryAccidentResult queryAccidentResult) {
//                        super.onResponse(queryAccidentResult);
//                    }
//                });
//
//        params.clear();
//        params.put("uuid","982782387");
//        OkHttpUtils.get()
//                .url(UrlConstant.GET_ACCIDENT_INFO)
//                .params(params)
//                .build()
//                .execute(new ResponseCallBack<GetAccidentEntity>() {
//                    @Override
//                    public void onResponse(GetAccidentEntity getAccidentEntity) {
//                        super.onResponse(getAccidentEntity);
//                    }
//                });

//        params.clear();
//        params.put("pageNo","1");
//        params.put("pageSize","10");
//        params.put("unitName","");
//        params.put("unitCode","");
//        params.put("deviceNumber","");
//        params.put("deviceType","");
//        params.put("checkResult","");
//        params.put("deviceId","");
//        params.put("visionId","");
//
//        OkHttpUtils.get()
//                .url(UrlConstant.QUERY_CHECKOUT_INFO)
//                .params(params)
//                .build().execute(new ResponseCallBack<QueryCheckoutResult>() {
//            @Override
//            public void onResponse(QueryCheckoutResult queryCheckoutResult) {
//                super.onResponse(queryCheckoutResult);
//            }
//        });

        params.clear();
        params.put("uuid","123123");
        OkHttpUtils.get()
                .url(UrlConstant.GET_CHECKOUT_INFO)
                .params(params)
                .build()
                .execute(new ResponseCallBack<QueryCheckoutEntity>() {
                    @Override
                    public void onResponse(QueryCheckoutEntity queryCheckoutEntity) {
                        super.onResponse(queryCheckoutEntity);
                    }
                });
    }
}
