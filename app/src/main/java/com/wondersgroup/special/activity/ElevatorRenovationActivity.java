package com.wondersgroup.special.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
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
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.wondersgroup.special.R;
import com.wondersgroup.special.ResponseCallBack;
import com.wondersgroup.special.adapter.AnalysisAdapter;
import com.wondersgroup.special.constant.Constant;
import com.wondersgroup.special.constant.Params;
import com.wondersgroup.special.constant.UrlConstant;
import com.wondersgroup.special.datepicker.SectionYearPickerDialog;
import com.wondersgroup.special.entity.AnalysisElevatorRenovationResult;
import com.wondersgroup.special.entity.AnalysisResult;
import com.wondersgroup.special.utils.DateUtils;
import com.wondersgroup.special.widget.SVListView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

/**
 * 住宅电梯改造情况分析
 */
public class ElevatorRenovationActivity extends BaseActivity {
    private SVListView mList, mList1, mList2;
    private TextView mTextChartName, mTextChartName1, mTextChartName2;
    private LineChart mLineChart;
    private List<AnalysisResult> data, data1, data2;
    private SectionYearPickerDialog mDialog;
    private HashMap<String, String> params;
    private LinearLayout mLinearSection;
    private TextView mIntervalStartDate, mIntervalEndDate;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_elevator_renovation);
        mTitle.setText("住宅电梯改造情况分析");
        mLineChart = (LineChart) findViewById(R.id.line_chart);
        mTextChartName = (TextView) findViewById(R.id.chart_name);
        mTextChartName.setText("安装");
        mTextChartName1 = (TextView) findViewById(R.id.chart_name1);
        mTextChartName1.setText("改造");
        mTextChartName2 = (TextView) findViewById(R.id.chart_name2);
        mTextChartName2.setText("维修");
        mList = (SVListView) findViewById(R.id.list);
        mList.setOnItemClickListener(new SVListView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ElevatorRenovationActivity.this, EquipmentListActivity.class);
                Bundle bundle = new Bundle();
                params = new HashMap<>();
                params.put("type", Constant.Overview.ELEVATOR);
                params.put("orderNum", Constant.Elevator.RENOVATION);
                params.put("param", "0");
                params.put("endYear", data.get(position).getUuid());
                bundle.putSerializable("PARAMS", params);
                bundle.putString(Params.PARENT_TYPE, Constant.HomeDateType.TYPE_ANALYSIS);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        mList1 = (SVListView) findViewById(R.id.list1);
        mList1.setOnItemClickListener(new SVListView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ElevatorRenovationActivity.this, EquipmentListActivity.class);
                Bundle bundle = new Bundle();
                params = new HashMap<>();
                params.put("type", Constant.Overview.ELEVATOR);
                params.put("orderNum", Constant.Elevator.RENOVATION);
                params.put("param", "1");
                params.put("endYear", data1.get(position).getUuid());
                bundle.putSerializable("PARAMS", params);
                bundle.putString(Params.PARENT_TYPE, Constant.HomeDateType.TYPE_ANALYSIS);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        mList2 = (SVListView) findViewById(R.id.list2);
        mList2.setOnItemClickListener(new SVListView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ElevatorRenovationActivity.this, EquipmentListActivity.class);
                Bundle bundle = new Bundle();
                params = new HashMap<>();
                params.put("type", Constant.Overview.ELEVATOR);
                params.put("orderNum", Constant.Elevator.RENOVATION);
                params.put("param", "2");
                params.put("endYear", data2.get(position).getUuid());
                bundle.putSerializable("PARAMS", params);
                bundle.putString(Params.PARENT_TYPE, Constant.HomeDateType.TYPE_ANALYSIS);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        mLinearSection = (LinearLayout) findViewById(R.id.linear_section);
        mLinearSection.setOnClickListener(this);
        mIntervalStartDate = (TextView) findViewById(R.id.interval_start_date);
        mIntervalEndDate = (TextView) findViewById(R.id.interval_end_date);
        mIntervalStartDate.setText(DateUtils.getSixAgeYear());
        mIntervalEndDate.setText(DateUtils.getThisYear());
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getAnalysisData();
    }

    private void getAnalysisData() {
        OkHttpUtils.get().url(UrlConstant.GET_ANALYSIS_DATA)
                .addParams("type", Constant.Overview.ELEVATOR)
                .addParams("orderNum", Constant.Elevator.RENOVATION)
                .addParams("startYear", mIntervalStartDate.getText().toString())
                .addParams("endYear", mIntervalEndDate.getText().toString())
                .build().execute(new ResponseCallBack<AnalysisElevatorRenovationResult>() {
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
            public void onResponse(AnalysisElevatorRenovationResult result) {
                super.onResponse(result);
                if (null != result) {
                    data = result.getFixData();
                    data1 = result.getReformData();
                    data2 = result.getRepairData();
                    mList.setAdapter(new AnalysisAdapter(ElevatorRenovationActivity.this, data));
                    mList1.setAdapter(new AnalysisAdapter(ElevatorRenovationActivity.this, data1));
                    mList2.setAdapter(new AnalysisAdapter(ElevatorRenovationActivity.this, data2));
                    showChart();
                }
            }

            @Override
            public void onError(Call call, Exception e) {
                super.onError(call, e);
                showMsg(e.getMessage());
            }
        });
    }

    private void showChart() {
        showBarChartData();
        setBarChartData();
//        setData();
    }

    /**
     * 显示柱状图
     */
    private void showBarChartData() {
        mLineChart.setDrawGridBackground(false);
        mLineChart.getDescription().setEnabled(false);
        mLineChart.setDrawBorders(false);
        mLineChart.getAxisLeft().setEnabled(true);
        mLineChart.getAxisRight().setDrawAxisLine(false);
        mLineChart.getAxisRight().setDrawGridLines(false);
        mLineChart.getXAxis().setDrawAxisLine(false);
        mLineChart.getXAxis().setDrawGridLines(false);
        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelRotationAngle(-30);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int index = (int) value;
                if (index < data.size()) {
                    return data.get(index).getName();
                }
                return index + "";
            }
        });

        YAxis yLeft = mLineChart.getAxisLeft();
        yLeft.setAxisMinimum(0f);
        yLeft.setAxisMaximum(getMaxHeight(data) + 20f);
        yLeft.setDrawGridLines(true);
        yLeft.setGranularityEnabled(true);
        yLeft.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (int) value + "";
            }
        });
        YAxis yRight = mLineChart.getAxisRight();
        yRight.setAxisMinimum(0f);
        yRight.setEnabled(false);
        yRight.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (int) value + "";
            }
        });
        // enable touch gestures
        mLineChart.setTouchEnabled(true);

        // enable scaling and dragging
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mLineChart.setPinchZoom(false);

        Legend l = mLineChart.getLegend();
        l.setWordWrapEnabled(true);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        mLineChart.setNoDataText("没有数据");

//        setBarChartData();
    }

    private void setBarChartData() {
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(getFix());
        dataSets.add(getReform());
        dataSets.add(getRepair());
        LineData data = new LineData(dataSets);
        mLineChart.setData(data);
        mLineChart.invalidate();
    }

    /**
     * 安装
     *
     * @return
     */
    private LineDataSet getFix() {
        LineDataSet d;
        ArrayList<Entry> values = new ArrayList<Entry>();
        for (int i = 0; i < data.size(); i++) {
            values.add(new Entry(i, Float.valueOf(data.get(i).getValue())));
        }
        d = new LineDataSet(values, "安装");
        d.setLineWidth(2.5f);
//            d.setCircleRadius(4f);
        d.setColor(ColorTemplate.COLORFUL_COLORS[0]);
        d.setCircleColor(ColorTemplate.COLORFUL_COLORS[0]);
        d.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return (int) value + "";
            }
        });
        return d;
    }

    /**
     * 改造
     *
     * @return
     */
    private LineDataSet getReform() {
        LineDataSet d;
        ArrayList<Entry> values = new ArrayList<Entry>();
        for (int i = 0; i < data1.size(); i++) {
            values.add(new Entry(i, Float.valueOf(data1.get(i).getValue())));
        }
        d = new LineDataSet(values, "改造");
        d.setLineWidth(2.5f);
//            d.setCircleRadius(4f);
        d.setColor(ColorTemplate.COLORFUL_COLORS[1]);
        d.setCircleColor(ColorTemplate.COLORFUL_COLORS[1]);
        d.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return (int) value + "";
            }
        });
        return d;
    }

    /**
     * 维修
     *
     * @return
     */
    private LineDataSet getRepair() {
        LineDataSet d;
        ArrayList<Entry> values = new ArrayList<Entry>();
        for (int i = 0; i < data2.size(); i++) {
            values.add(new Entry(i, Float.valueOf(data2.get(i).getValue())));
        }
        d = new LineDataSet(values, "维修");
        d.setLineWidth(2.5f);
//            d.setCircleRadius(4f);
        d.setColor(ColorTemplate.COLORFUL_COLORS[2]);
        d.setCircleColor(ColorTemplate.COLORFUL_COLORS[2]);
        d.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return (int) value + "";
            }
        });
        return d;
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
            case R.id.linear_section:
                if (null == mDialog || (mDialog != null && !mDialog.isShowing())) {
                    mDialog = new SectionYearPickerDialog.Builder(ElevatorRenovationActivity.this)
                            .setStartDate(Integer.valueOf(mIntervalStartDate.getText().toString()))
                            .setEndDate(Integer.valueOf(mIntervalEndDate.getText().toString()))
                            .setNegativeButton("取消", null)
                            .setPositiveButton("确定", new SectionYearPickerDialog.Builder.OnDatePickListener() {
                                @Override
                                public void onDatePick(DialogInterface dialog, int witch, int... args) {
                                    if ((args[1] - args[0]) > 15) {
                                        showMsg("时间区间范围最大为15年");
                                    } else if (args[1] <= args[0]) {
                                        showMsg("结束年限必须大于开始年限");
                                    } else {
                                        mIntervalStartDate.setText(args[0] + "");
                                        mIntervalEndDate.setText(args[1] + "");
                                        getAnalysisData();
                                    }
                                }
                            }).create();
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                }
                break;
        }
    }
}
