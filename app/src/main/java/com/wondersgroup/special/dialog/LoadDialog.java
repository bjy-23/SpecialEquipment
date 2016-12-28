package com.wondersgroup.special.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.wondersgroup.special.R;

/**
 * Created by root on 11/21/16.
 */

public class LoadDialog extends Dialog {

    public LoadDialog(Context context) {
        super(context);
    }

    public LoadDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected LoadDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setLayout(double width, double height) {
        getWindow().setLayout((int) width, (int) height);
    }

    public void setAnimStyle(int styleId) {
        if (getWindow() != null) {
            getWindow().setWindowAnimations(styleId);
        }
    }

    public static class Builder {
        private static final int DEFAULT_STYLE = R.style.DialogStyle;
        private LoadDialog dialog;
        private Context mContext;
        private TextView mLoadingText;
        private String textLoading;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setLoadingText(String text) {
            this.textLoading = text;
            return this;
        }

        public LoadDialog create() {
            dialog = new LoadDialog(mContext, DEFAULT_STYLE);

            LayoutInflater inflater = LayoutInflater.from(mContext);
            View contentView = inflater.inflate(R.layout.loading_dialog_layout, null);
            mLoadingText = (TextView) contentView.findViewById(R.id.progress_dialog_content);
            if (!TextUtils.isEmpty(textLoading)) {
                mLoadingText.setText(textLoading);
            }
            dialog.setContentView(contentView);
            setupWindowParams();
            return dialog;
        }

        private void setupWindowParams() {
            dialog.setAnimStyle(DEFAULT_STYLE);

            WindowManager wManager = dialog.getWindow().getWindowManager();
            double width = wManager.getDefaultDisplay().getWidth() * 0.8;
            dialog.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);
        }

        public LoadDialog show() {
            dialog = create();
            dialog.show();
            return dialog;
        }
    }
}
