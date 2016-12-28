package com.wondersgroup.special.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wondersgroup.special.R;

public class CategoryUnitActivity extends BaseActivity {
    private RelativeLayout mRelativeUnit;
    private TextView mStatistics, mPermitStatistics, mGrowth;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_category_unit);
        mTitle.setText("从业单位");
        mRelativeUnit = (RelativeLayout) findViewById(R.id.relative_unit);
        mStatistics = (TextView) findViewById(R.id.statistics);
        mPermitStatistics = (TextView) findViewById(R.id.permit_statistics);
        mGrowth = (TextView) findViewById(R.id.growth);
        mRelativeUnit.setOnClickListener(this);
        mStatistics.setOnClickListener(this);
        mPermitStatistics.setOnClickListener(this);
        mGrowth.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.relative_unit:
                break;
            case R.id.statistics:
                startActivity(new Intent(CategoryUnitActivity.this, StatisticsUnitActivity.class));
                break;
            case R.id.permit_statistics:
                startActivity(new Intent(CategoryUnitActivity.this,StatisticsProUnitActivity.class));
                break;
            case R.id.growth:
                startActivity(new Intent(CategoryUnitActivity.this,StatisticsGrowthActivity.class));
                break;
            default:
                break;
        }
    }
}
