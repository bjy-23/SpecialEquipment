package com.wondersgroup.special.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.wondersgroup.special.ResponseCallBack;
import com.wondersgroup.special.constant.UrlConstant;
import com.wondersgroup.special.entity.AreaDicEntity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjy on 2016/12/23.
 */

public class DicUtil {
    private Context context;
    private OnChoiceListener onChoiceListener;
    private RequestCall call;

    public DicUtil(Context context) {
        this.context = context;
    }

    public void makeChoice(int type,String deviceType){
        switch (type){
            case 1:
                call = OkHttpUtils.get()
                        .url(UrlConstant.GET_AREA_DIC)
                        .build();
                break;
            case 2:
                call = OkHttpUtils.get()
                        .url(UrlConstant.GET_UNIT_NATURE_DIC)
                        .build();
                break;
            case 3:
                call = OkHttpUtils.get()
                        .url(UrlConstant.GET_DEVICE_TYPE_DIC)
                        .build();
                break;
            case 4:
                call = OkHttpUtils.get()
                        .url(UrlConstant.GET_DEVICE_KIND_DIC)
                        .addParams("deviceType",deviceType)
                        .build();
                break;
            case 5:
                call = OkHttpUtils.get()
                        .url(UrlConstant.GET_USE_PLACE)
                        .build();
                break;
        }

        call.execute(new ResponseCallBack<AreaDicEntity>() {
            @Override
            public void onResponse(List<AreaDicEntity> t) {
                super.onResponse(t);
                final List<AreaDicEntity> dicList = new ArrayList<AreaDicEntity>();
                dicList.addAll(t);
                final String[] choices = new String[t.size()];
                for (int i = 0; i < t.size(); i++) {
                    choices[i] = t.get(i).getValue();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setSingleChoiceItems(choices, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onChoiceListener.choice(dicList.get(which));
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
    }

    public interface OnChoiceListener{
        void choice(AreaDicEntity entity);
    }

    public void setOnChoiceListener(OnChoiceListener onChoiceListener) {
        this.onChoiceListener = onChoiceListener;
    }
}
