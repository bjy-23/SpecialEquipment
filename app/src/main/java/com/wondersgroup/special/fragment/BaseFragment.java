package com.wondersgroup.special.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wondersgroup.special.R;
import com.wondersgroup.special.dialog.LoadDialog;

/**
 * Created by root on 11/1/16.
 */

public class BaseFragment extends Fragment {
    protected View mToolBar;
    protected LoadDialog mLoadingDialog;

    public static BaseFragment newInstance(Bundle args) {
        BaseFragment fragment = new BaseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            onPause();
        } else {
            onVisible();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            onVisible();
        }
    }

    protected void onVisible() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mToolBar = getActivity().findViewById(R.id.tool_bar);
        mToolBar.setVisibility(View.GONE);
        if (null == mLoadingDialog)
            mLoadingDialog = new LoadDialog.Builder(getActivity()).create();
    }

    /**
     * 显示进度条
     */
    protected void showDialog() {
        if (null != mLoadingDialog && !mLoadingDialog.isShowing()) {
            mLoadingDialog.setCanceledOnTouchOutside(false);
            mLoadingDialog.show();
        }
    }

    /**
     * 隐藏进度条
     */
    protected void dismissDialog() {
        mLoadingDialog.dismiss();
    }

}
