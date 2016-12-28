package com.wondersgroup.special.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.wondersgroup.special.R;
import com.wondersgroup.special.ResponseCallBack;
import com.wondersgroup.special.constant.Params;
import com.wondersgroup.special.constant.UrlConstant;
import com.wondersgroup.special.entity.DocumentResult;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.Call;
import okhttp3.Request;

public class BookActivity extends BaseActivity {
    private TextView book;
    private String uuid;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_book);
        book = (TextView) findViewById(R.id.book);
        mTitle.setText("安全监察指令书");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        uuid = getIntent().getStringExtra(Params.UUID);
        getData();
    }

    /**
     * 单位许可
     */
    private void getData() {
        OkHttpUtils.get().
                url(UrlConstant.GET_SUPERVISION_DOCUMENT)
                .addParams("uuid", uuid)
                .build()
                .execute(new ResponseCallBack<DocumentResult>() {
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
                    public void onResponse(DocumentResult result) {
                        super.onResponse(result);
                        if (null != result) {
                            book.setText(result.getContent());
                        }
                    }
                });
    }
}
