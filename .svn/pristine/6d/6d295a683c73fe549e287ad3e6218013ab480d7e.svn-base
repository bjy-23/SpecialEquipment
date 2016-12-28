package com.wondersgroup.special.activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.wondersgroup.special.R;
import com.wondersgroup.special.ResponseCallBack;
import com.wondersgroup.special.constant.UrlConstant;
import com.wondersgroup.special.datepicker.DatePickerDialog;
import com.wondersgroup.special.dialog.PromptDialog;
import com.wondersgroup.special.entity.AnalysisResult;
import com.wondersgroup.special.entity.StatisticsProUnitListResult;
import com.wondersgroup.special.utils.DateUtils;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

public class StatisticsProUnitActivity extends BaseActivity {
    private CombinedChart mCombinedChart;
    private TextView mType, mUnitDate;
    private DatePickerDialog mDialog;
    private int index = 4;
    private String[] select = new String[]{"锅炉", "压力容器", "气瓶", "压力管道", "电梯", "起重机械", "厂内车辆", "游乐设施"};

    @Override
    protected void initView() {
        setContentView(R.layout.activity_statistics_pro_unit);
        mCombinedChart = (CombinedChart) findViewById(R.id.combined_chart);
        mTitle.setText("生产单位许可统计");
        mType = (TextView) findViewById(R.id.type);
        mType.setOnClickListener(this);
        mType.setText(select[index]);
        mUnitDate = (TextView) findViewById(R.id.unit_date);
        mUnitDate.setOnClickListener(this);
        mUnitDate.setText(DateUtils.getYesterday());
        mCombinedChart.getDescription().setEnabled(false);
        mCombinedChart.setBackgroundColor(Color.WHITE);
        mCombinedChart.setDrawGridBackground(false);
        mCombinedChart.setDrawBarShadow(false);
        mCombinedChart.setHighlightFullBarEnabled(false);

        // draw bars behind lines
        mCombinedChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.LINE, CombinedChart.DrawOrder.BAR
        });
        Legend l = mCombinedChart.getLegend();
        l.setWordWrapEnabled(true);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(true);

        YAxis rightAxis = mCombinedChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (int) value + "";
            }
        });
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis leftAxis = mCombinedChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (int) value + "";
            }
        });
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        XAxis xAxis = mCombinedChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelRotationAngle(-30f);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getData();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.type:
                PromptDialog dialog = new PromptDialog()
                        .setItems(select, new PromptDialog.IOnClickItemListener() {
                            @Override
                            public void onClickItem(View view, int position) {
                                mType.setText(select[position]);
                                index = position;
                                getData();
                            }
                        });
                dialog.setCancelable(false);
                dialog.show(getSupportFragmentManager());
                break;
            case R.id.unit_date:
                String[] arr = DateUtils.getStrSplit(mUnitDate.getText().toString(), "-");
                if (null == mDialog || (mDialog != null && !mDialog.isShowing())) {
                    mDialog = new DatePickerDialog.Builder(StatisticsProUnitActivity.this).setDayOff(false)
                            .setDate(Integer.valueOf(arr[0]), Integer.valueOf(arr[1]), Integer.valueOf(arr[2]))
                            .setNegativeButton("取消", null)
                            .setPositiveButton("确定", new DatePickerDialog.Builder.OnDatePickListener() {
                                @Override
                                public void onDatePick(DialogInterface dialog, int witch, int... args) {
                                    mUnitDate.setText(args[0] + "-" + args[1] + "-" + args[2]);
                                    getData();
                                }
                            }).create();
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                }
                break;
            default:
                break;
        }
    }

    private void getData() {
        OkHttpUtils.get()
                .url(UrlConstant.STAT_EUNIT_CERT)
                .addParams("type", String.valueOf(index + 1))
                .addParams("endDate", mUnitDate.getText().toString())
                .build()
                .execute(new ResponseCallBack<StatisticsProUnitListResult>() {
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
                    public void onResponse(StatisticsProUnitListResult t) {
                        super.onResponse(t);
                        if (null != t) {
                            setData(t);
                        }
                    }
                });
    }

    private LineData generateLineData(List<AnalysisResult> data) {

        LineData d = new LineData();

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int index = 0; index < data.size(); index++)
            entries.add(new Entry(index, Float.parseFloat(data.get(index).getValue())));

        LineDataSet set = new LineDataSet(entries, "单位许可数");
        set.setColor(ColorTemplate.getHoloBlue());
        set.setLineWidth(2.5f);
        set.setCircleColor(ColorTemplate.getHoloBlue());
        set.setCircleRadius(5f);
        set.setFillColor(ColorTemplate.getHoloBlue());
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.BLACK);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);
        d.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return (int) value + "";
            }
        });

        return d;
    }

    private BarData generateBarData(List<AnalysisResult> data) {

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        for (int index = 0; index < data.size(); index++) {
            entries.add(new BarEntry(index, Float.parseFloat(data.get(index).getValue())));
        }

        BarDataSet set1 = new BarDataSet(entries, "单位数");
        set1.setColor(Color.rgb(60, 220, 78));
        set1.setValueTextColor(Color.BLACK);
        set1.setValueTextSize(10f);
        set1.setAxisDependency(YAxis.AxisDependency.RIGHT);

//        BarDataSet set2 = new BarDataSet(entries2, "");
//        set2.setStackLabels(new String[]{"Stack 1", "Stack 2"});
//        set2.setColors(new int[]{Color.rgb(61, 165, 255), Color.rgb(23, 197, 255)});
//        set2.setValueTextColor(Color.rgb(61, 165, 255));
//        set2.setValueTextSize(10f);
//        set2.setAxisDependency(YAxis.AxisDependency.RIGHT);

        float groupSpace = 0.06f;
        float barSpace = 0.02f; // x2 dataset
        float barWidth = 0.4f; // x2 dataset
        // (0.45 + 0.02) * 2 + 0.06 = 1.00 -> interval per "group"

        BarData d = new BarData(set1);
        d.setBarWidth(barWidth);
        d.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return (int) value + "";
            }
        });

        // make this BarData object grouped
//        d.groupBars(0, groupSpace, barSpace); // start at x = 0

        return d;
    }

    private void setData(StatisticsProUnitListResult t) {
        final List<AnalysisResult> certData = t.getCertData();
        List<AnalysisResult> unitData = t.getUnitData();

        CombinedData datas = new CombinedData();
        datas.setData(generateBarData(unitData));
        datas.setData(generateLineData(certData));
        XAxis xAxis = mCombinedChart.getXAxis();
        xAxis.setLabelCount(certData.size());
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String content = certData.get((int) value).getName();
                if (content.length() > 6) {
                    content = content.substring(0, 5) + "...";
                }
                return content;
            }
        });
        xAxis.setAxisMaximum(datas.getXMax() + 0.25f);

        mCombinedChart.setData(datas);
        mCombinedChart.invalidate();
    }
}
