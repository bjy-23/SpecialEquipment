package com.wondersgroup.special.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wondersgroup.special.R;
import com.wondersgroup.special.ResponseCallBack;
import com.wondersgroup.special.activity.BDMapActivity;
import com.wondersgroup.special.activity.LoginActivity;
import com.wondersgroup.special.constant.UrlConstant;
import com.wondersgroup.special.entity.BaseResult;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chan on 11/2/16.
 */

public class SettingFragment extends BaseFragment implements View.OnClickListener {
    private View mParentView;
    private TextView loginOutTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mParentView = getView();
        ((TextView) mParentView.findViewById(R.id.title)).setText("设置");
        loginOutTextView = (TextView) mParentView.findViewById(R.id.loginOut);
        loginOutTextView.setOnClickListener(this);
//        mParentView.findViewById(R.id.show_map).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), BDMapActivity.class));
//            }
//        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginOut:
                loginOut();
                break;
        }
    }

    public void loginOut() {
        OkHttpUtils.post()
                .url(UrlConstant.LOGOUT)
                .build().
                execute(new ResponseCallBack() {
                    @Override
                    public BaseResult parseNetworkResponse(Response response) throws Exception {
                        String s = response.body().string();
                        Gson gson = new Gson();
                        BaseResult baseResult = gson.fromJson(s, BaseResult.class);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                Toast.makeText(getActivity(),"退出成功",Toast.LENGTH_SHORT);
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                startActivity(intent);
                                getActivity().finish();
                            }
                        });

                        return baseResult;
                    }

                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        super.onError(call, e);
                    }

                    @Override
                    public void onResponse(Object o) {
                        super.onResponse(o);

                    }
                });
    }
}
