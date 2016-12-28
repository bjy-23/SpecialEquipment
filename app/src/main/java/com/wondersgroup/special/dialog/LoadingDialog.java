package com.wondersgroup.special.dialog;

import android.support.v4.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.special.R;

/**
 * Created by chan on 6/16/16.
 */
public class LoadingDialog extends DialogFragment {
    public static String TAG = "LoadingDialog";
    private TextView mLoadingText;

    public LoadingDialog() {
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.loading_dialog_layout, container, false);
        mLoadingText = (TextView) view.findViewById(R.id.progress_dialog_content);
        return view;
    }

    public LoadingDialog setLoadingText(String text) {
        mLoadingText.setText(text);
        return this;
    }

    public void show(FragmentManager manager) {
        synchronized (LoadingDialog.this){
            if (!isVisible()) {
                super.show(manager, TAG);
            }
        }
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }
}
