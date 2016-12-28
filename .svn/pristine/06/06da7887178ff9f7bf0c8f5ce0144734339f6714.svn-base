package com.wondersgroup.special.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wondersgroup.special.R;

/**
 * Created by chan on 11/2/16.
 */

public class WarningFragment extends BaseFragment {
    private View mParentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_warning, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        mParentView=getView();
        ((TextView) mParentView.findViewById(R.id.title)).setText("预警中心");
    }
}
