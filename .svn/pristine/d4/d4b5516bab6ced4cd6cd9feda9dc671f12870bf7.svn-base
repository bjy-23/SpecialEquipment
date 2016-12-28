package com.wondersgroup.special.homepage;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.wondersgroup.special.R;
import com.wondersgroup.special.ResponseCallBack;
import com.wondersgroup.special.activity.BaseActivity;
import com.wondersgroup.special.activity.EquipmentListActivity;
import com.wondersgroup.special.adapter.AnalysisAdapter;
import com.wondersgroup.special.constant.Constant;
import com.wondersgroup.special.constant.Params;
import com.wondersgroup.special.constant.UrlConstant;
import com.wondersgroup.special.datepicker.YearPickerDialog;
import com.wondersgroup.special.entity.AnalysisResult;
import com.wondersgroup.special.utils.DateUtils;
import com.wondersgroup.special.widget.SVListView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Request;

public class EmergentActivity extends BaseActivity {
    private String title, mChartName;
    private SVListView mList;
    private TextView mTextChartName;
    private LineChart mLineChart;
    private String mParentType;//大类别，表示锅炉、压力容器等
    private String mChildType;//大类别里的小类别,比如锅炉里的类别分析、使用场所分析等
    private List<AnalysisResult> data;
    private AnalysisAdapter analysisAdapter;
    private TextView mTextDate;
    private YearPickerDialog mYearDialog;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_emergent);
        mTitle.setText("突发事件分析");
        mLineChart = (LineChart) findViewById(R.id.line_chart);
        mList = (SVListView) findViewById(R.id.list);
        mTextChartName = (TextView) findViewById(R.id.chart_name);
        mTextChartName.setText("突发事件和数量");
        mTextDate = (TextView) findViewById(R.id.date);
        mTextDate.setOnClickListener(this);
        mTextDate.setText(DateUtils.getThisYear());
        mList.setOnItemClickListener(new SVListView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                HashMap<String, String> params = new HashMap<>();
                params.put("type", data.get(position).getUuid());
                params.put("orderNum", Constant.DataInfo.EQUIPMENT_NO);
                params.put("param", data.get(position).getUuid());
                params.put("endYear", mTextDate.getText().toString());
                bundle.putSerializable("PARAMS", params);
                bundle.putString(Params.PARENT_TYPE, Constant.HomeDateType.TYPE_EMERGENCY);
                intent.setClass(EmergentActivity.this, EquipmentListActivity.class);
//                bundle.putString("UUID", data.get(position).getUuid());
//                bundle.putString("HOME_DATA_TYPE", Constant.HomeDateType.TYPE_EMERGENCY);
//                bundle.putString("END_YEAR", mTextDate.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        data = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        String mEndYear = bundle.getString("END_YEAR");
        mTextDate.setText(mEndYear);
        showEmergentEvents();
    }

    /**
     * 显示突发事件分析
     */
    private void showEmergentEvents() {
        // no description text
        mLineChart.getDescription().setEnabled(false);

        // enable touch gestures
        mLineChart.setTouchEnabled(true);

        mLineChart.setDragDecelerationFrictionCoef(0.9f);

        // enable scaling and dragging
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(true);
        mLineChart.setDrawGridBackground(false);
        mLineChart.setHighlightPerDragEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mLineChart.setPinchZoom(true);

        // set an alternative background color
        mLineChart.setBackgroundColor(Color.WHITE);

        getEmergentEvents();

        // add data


        mLineChart.animateX(2500);

        // get the legend (only possible after setting data)

    }

    public void getEmergentEvents() {
        OkHttpUtils.get()
                .url(UrlConstant.GET_EMERGENCIES)
                .addParams(getResources().getString(R.string.endYear), mTextDate.getText().toString())
                .build()
                .execute(new ResponseCallBack<AnalysisResult>() {
                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                    }

                    @Override
                    public void onResponse(AnalysisResult analysisResult) {
                        super.onResponse(analysisResult);
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
                    }

                    @Override
                    public void onResponse(List<AnalysisResult> result) {
                        super.onResponse(result);
                        if (null != result) {
                            setEmergentEventsData(result);
                            data.clear();
                            data.addAll(result);
                            analysisAdapter = new AnalysisAdapter(EmergentActivity.this, data);
                            mList.setAdapter(analysisAdapter);
                        }

                    }
                });
    }

    private void setEmergentEventsData(final List<AnalysisResult> datas) {

        Legend l = mLineChart.getLegend();
        l.setEnabled(false);
        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setTextSize(11f);
        l.setTextColor(Color.WHITE);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
//        l.setYOffset(11f);

        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setLabelCount(11);
        xAxis.setTextSize(11f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return datas.get((int) value).getName();
            }
        });
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);

        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setAxisMaximum(getMaxHeight(datas) + 20f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularityEnabled(true);

        YAxis rightAxis = mLineChart.getAxisRight();
        rightAxis.setEnabled(false);
        rightAxis.setTextColor(Color.RED);
        rightAxis.setAxisMaximum(900);
        rightAxis.setAxisMinimum(-200);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setGranularityEnabled(false);

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        LineDataSet set1;
        for (int i = 0; i < datas.size(); i++) {
            yVals1.add(new Entry(i, Float.parseFloat(datas.get(i).getValue())));
        }
        if (mLineChart.getData() != null &&
                mLineChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mLineChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mLineChart.getData().notifyDataChanged();
            mLineChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(yVals1, "");
            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.setColor(ColorTemplate.getHoloBlue());
            set1.setCircleColor(ColorTemplate.getHoloBlue());
            set1.setLineWidth(2f);
            set1.setCircleRadius(3f);
            set1.setFillAlpha(65);
            set1.setFillColor(ColorTemplate.getHoloBlue());
            set1.setHighLightColor(ColorTemplate.getHoloBlue());
            set1.setDrawCircleHole(false);
            set1.setDrawValues(true);
            //set1.setFillFormatter(new MyFillFormatter(0f));
            //set1.setDrawHorizontalHighlightIndicator(false);
            //set1.setVisible(false);
            //set1.setCircleHoleColor(Color.WHITE);


            // create a data object with the datasets
            LineData data = new LineData(set1);
            data.setValueTextColor(Color.BLACK);
            data.setValueTextSize(9f);
            data.setValueFormatter(new IValueFormatter() {

                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return String.valueOf((int) value);
                }
            });
            // set data
            mLineChart.setData(data);
            mLineChart.invalidate();
        }
    }

    public float getMaxHeight(List<AnalysisResult> datas) {
        int max = 0;
        for (AnalysisResult analysisResult : datas) {
            if (max < Integer.parseInt(analysisResult.getValue()))
                max = Integer.parseInt(analysisResult.getValue());
        }
        return (float) max;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.date:
                String endDate = mTextDate.getText().toString();
                if (null == mYearDialog || (mYearDialog != null && !mYearDialog.isShowing())) {
                    mYearDialog = new YearPickerDialog.Builder(EmergentActivity.this)
                            .setYear(Integer.parseInt(endDate))
                            .setNegativeButton("取消", null)
                            .setPositiveButton("确定", new YearPickerDialog.Builder.OnYearPickListener() {
                                @Override
                                public void onYearPick(DialogInterface dialog, int witch, int... args) {
                                    dialog.dismiss();
                                    mTextDate.setText(args[0] + "");
                                    showEmergentEvents();
                                }
                            })
                            .create();
                    mYearDialog.setCanceledOnTouchOutside(false);
                    mYearDialog.show();
                }
                break;
        }
    }
}
