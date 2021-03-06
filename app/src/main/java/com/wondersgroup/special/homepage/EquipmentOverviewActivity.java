package com.wondersgroup.special.homepage;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.wondersgroup.special.R;
import com.wondersgroup.special.ResponseCallBack;
import com.wondersgroup.special.activity.BaseActivity;
import com.wondersgroup.special.activity.EquipmentListActivity;
import com.wondersgroup.special.adapter.AnalysisAdapter;
import com.wondersgroup.special.constant.Constant;
import com.wondersgroup.special.constant.Params;
import com.wondersgroup.special.constant.UrlConstant;
import com.wondersgroup.special.datepicker.SectionDatePickerDialog;
import com.wondersgroup.special.entity.AnalysisResult;
import com.wondersgroup.special.utils.DateUtils;
import com.wondersgroup.special.widget.SVListView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

public class EquipmentOverviewActivity extends BaseActivity {
    private SVListView mList;
    private TextView mTextChartName;
    private PieChart mChart;
    private List<AnalysisResult> data;
    private LinearLayout mLinearSection;
    private SectionDatePickerDialog mSectionDialog;
    private TextView mIntervalStartDate, mIntervalEndDate;
    private AnalysisAdapter analysisAdapter;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_equipment_overview);
        mTitle.setText("设备概览");
        mChart = (PieChart) findViewById(R.id.pie_chart);
        mList = (SVListView) findViewById(R.id.list);
        mTextChartName = (TextView) findViewById(R.id.chart_name);
        mTextChartName.setText("设备概览和数量");
        mLinearSection = (LinearLayout) findViewById(R.id.linear_section);
        mLinearSection.setOnClickListener(this);
        mIntervalStartDate = (TextView) findViewById(R.id.interval_start_date);
        mIntervalStartDate.setText(DateUtils.getFirstDay());
        mIntervalEndDate = (TextView) findViewById(R.id.interval_end_date);
        mIntervalEndDate.setText(DateUtils.getToday());
        mList.setOnItemClickListener(new SVListView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                HashMap<String, String> params = new HashMap<>();
                params.put("type", Constant.Overview.HOME_PAGE);
                params.put("orderNum", Constant.HomeDateType.TYPE_DEVICE_OVERVIEW);
                params.put("param", data.get(position).getUuid());
                params.put("startDate", mIntervalStartDate.getText().toString());
                params.put("endDate", mIntervalEndDate.getText().toString());
                bundle.putSerializable("PARAMS", params);
                bundle.putString(Params.PARENT_TYPE, Constant.HomeDateType.TYPE_ANALYSIS);
                intent.setClass(EquipmentOverviewActivity.this, EquipmentListActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        String mStartDate = bundle.getString("START_YEAR");
        String mEndDate = bundle.getString("END_YEAR");
        mIntervalStartDate.setText(mStartDate);
        mIntervalEndDate.setText(mEndDate);
        data = new ArrayList<>();
        showEquipmentOverview();
    }

    /**
     * 显示设备预览图
     */
    private void showEquipmentOverview() {
        mChart.setNoDataText("没有设备概览数据");
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);
        mChart.setDrawHoleEnabled(false);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(false);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener

        setEquipmentOverviewData();

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        Legend l = mChart.getLegend();
        l.setWordWrapEnabled(true);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setTextSize(10f);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(1f);
        l.setYOffset(1f);

        // entry label styling
        mChart.setEntryLabelColor(Color.BLACK);
        mChart.setEntryLabelTextSize(10f);
    }

    private void setEquipmentOverviewData() {
        OkHttpUtils.get()
                .url(UrlConstant.GET_ANALYSIS_DATA)
                .addParams("type", Constant.Overview.HOME_PAGE)
                .addParams("orderNum", Constant.Boiler.CATEGORY)
                .addParams("startDate", mIntervalStartDate.getText().toString())
                .addParams("endDate", mIntervalEndDate.getText().toString())
                .build()
                .execute(new ResponseCallBack<AnalysisResult>() {
                    @Override
                    public void onResponse(List<AnalysisResult> result) {
                        super.onResponse(result);
                        if (result != null) {
                            setPieChartData(result);
                            data.clear();
                            data.addAll(result);
                            analysisAdapter = new AnalysisAdapter(EquipmentOverviewActivity.this, data);
                            mList.setAdapter(analysisAdapter);
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        super.onError(call, e);
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
                    }

                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                    }
                });
    }

    public void setPieChartData(List<AnalysisResult> data) {
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < data.size(); i++) {
            float value = TextUtils.isEmpty(data.get(i).getValue()) ? 0 : Float.valueOf(data.get(i).getValue());
            entries.add(new PieEntry(value, data.get(i).getName()));
        }
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData datas = new PieData(dataSet);
        datas.setValueFormatter(new PercentFormatter());
        datas.setValueTextSize(10f);
        datas.setValueTextColor(Color.BLACK);
        mChart.setData(datas);
        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.linear_section:
                String[] arrStart = DateUtils.getStrSplit(mIntervalStartDate.getText().toString(), "-");
                String[] arrEnd = DateUtils.getStrSplit(mIntervalEndDate.getText().toString(), "-");
                if (null == mSectionDialog || (mSectionDialog != null && !mSectionDialog.isShowing())) {
                    mSectionDialog = new SectionDatePickerDialog.Builder(EquipmentOverviewActivity.this)
                            .setStartDate(Integer.valueOf(arrStart[0]), Integer.valueOf(arrStart[1]), Integer.valueOf(arrStart[2]))
                            .setEndDate(Integer.valueOf(arrEnd[0]), Integer.valueOf(arrEnd[1]), Integer.valueOf(arrEnd[2]))
                            .setNegativeButton("取消", null).setPositiveButton("确定", new SectionDatePickerDialog.Builder.OnDatePickListener() {
                                @Override
                                public void onDatePick(DialogInterface dialog, int witch, int... args) {
                                    mIntervalStartDate.setText(args[0] + "-" + args[1] + "-" + args[2]);
                                    mIntervalEndDate.setText(args[3] + "-" + args[4] + "-" + args[5]);
                                    showEquipmentOverview();
                                }
                            }).create();
                    mSectionDialog.setCanceledOnTouchOutside(false);
                    mSectionDialog.show();
                    break;
                }
                break;
        }
    }
}
