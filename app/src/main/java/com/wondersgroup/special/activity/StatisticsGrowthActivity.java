package com.wondersgroup.special.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gc.materialdesign.views.CheckBox;
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
import com.wondersgroup.special.constant.UrlConstant;
import com.wondersgroup.special.datepicker.SectionYearPickerDialog;
import com.wondersgroup.special.entity.AnalysisResult;
import com.wondersgroup.special.entity.StatisticsGrowthListResult;
import com.wondersgroup.special.utils.DateUtils;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

public class StatisticsGrowthActivity extends BaseActivity {
    private LineChart mChart;
    private TextView mYearSection;
    private TextView mSelect;
    private List<AnalysisResult> cylinFillData = new ArrayList<>(), designData = new ArrayList<>(), instalDta = new ArrayList<>(),
            maintainData = new ArrayList<>(), makeData = new ArrayList<>(), reformData = new ArrayList<>();
    private SectionYearPickerDialog mDialog;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_statistics_growth);
        mTitle.setText("从业单位增长情况");
        mChart = (LineChart) findViewById(R.id.chart);
        mYearSection = (TextView) findViewById(R.id.year_section);
        mYearSection.setOnClickListener(this);
        mSelect = (TextView) findViewById(R.id.select);
        mSelect.setOnClickListener(this);
        mYearSection.setText("2010-2016");
        mChart.setDrawGridBackground(false);
        mChart.getDescription().setEnabled(false);
        mChart.setDrawBorders(false);

        mChart.getAxisLeft().setEnabled(true);
        mChart.getAxisRight().setDrawAxisLine(false);
        mChart.getAxisRight().setDrawGridLines(false);
        mChart.getXAxis().setDrawAxisLine(false);
        mChart.getXAxis().setDrawGridLines(false);
        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelRotationAngle(-30);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int index = (int) value;
                if (index < cylinFillData.size()) {
                    return cylinFillData.get(index).getName();
                }
                return index + "";
            }
        });

        YAxis yLeft = mChart.getAxisLeft();
        yLeft.setAxisMinimum(0f);
        yLeft.setDrawGridLines(true);
        yLeft.setGranularityEnabled(true);
        yLeft.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (int) value + "";
            }
        });
        YAxis yRight = mChart.getAxisRight();
        yRight.setAxisMinimum(0f);
        yRight.setEnabled(false);
        yRight.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (int) value + "";
            }
        });
        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        Legend l = mChart.getLegend();
        l.setWordWrapEnabled(true);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getData("2010", "2016");
    }

    private void getData(String startYear, String endYear) {
        OkHttpUtils.get()
                .url(UrlConstant.STAT_EUNIT_GROW)
                .addParams("startYear", startYear)
                .addParams("endYear", endYear)
                .build()
                .execute(new ResponseCallBack<StatisticsGrowthListResult>() {
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
                    public void onResponse(StatisticsGrowthListResult t) {
                        super.onResponse(t);
                        if (null != t) {
                            setData(t);
                        }
                    }
                });
    }

    private void setData(StatisticsGrowthListResult t) {
        mChart.resetTracking();
        cylinFillData.clear();
        cylinFillData = t.getCylinFillData();
        if (null == cylinFillData) cylinFillData = new ArrayList<>();
        designData.clear();
        designData = t.getDesignData();
        if (null == designData) designData = new ArrayList<>();
        instalDta.clear();
        instalDta = t.getInstalDta();
        if (null == instalDta) instalDta = new ArrayList<>();
        maintainData.clear();
        maintainData = t.getMaintainData();
        if (null == maintainData) maintainData = new ArrayList<>();
        makeData.clear();
        makeData = t.getMakeData();
        if (null == makeData) makeData = new ArrayList<>();
        reformData.clear();
        reformData = t.getReformData();
        if (null == reformData) reformData = new ArrayList<>();
        mChart.getXAxis().setLabelCount(cylinFillData.size());
        mChart.getXAxis().setAxisMinimum(0f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(getCylinFillData());
        dataSets.add(getDesign());
        dataSets.add(getInstal());
        dataSets.add(getMaintain());
        dataSets.add(getMake());
        dataSets.add(getReform());

        // make the first DataSet dashed
//        ((LineDataSet) dataSets.get(0)).enableDashedLine(10, 10, 0);
//        ((LineDataSet) dataSets.get(0)).setColors(ColorTemplate.VORDIPLOM_COLORS);
//        ((LineDataSet) dataSets.get(0)).setCircleColors(ColorTemplate.VORDIPLOM_COLORS);
        invalidateChart(dataSets);
    }

    /**
     * 刷新图表
     *
     * @param dataSets
     */
    private void invalidateChart(ArrayList<ILineDataSet> dataSets) {
        LineData data = new LineData(dataSets);
        mChart.setData(data);
        mChart.invalidate();
    }

    /**
     * 气瓶充装
     *
     * @return
     */
    private LineDataSet getCylinFillData() {
        LineDataSet d;
        ArrayList<Entry> values = new ArrayList<Entry>();

        for (int i = 0; i < cylinFillData.size(); i++) {
            values.add(new Entry(i, Float.valueOf(cylinFillData.get(i).getValue())));
        }
        d = new LineDataSet(values, "气瓶充装");
        d.setLineWidth(2.5f);
//            d.setCircleRadius(4f);
        d.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return (int) value + "";
            }
        });
        return d;
    }

    /**
     * 设计
     *
     * @return
     */
    private LineDataSet getDesign() {
        LineDataSet d;
        ArrayList<Entry> values = new ArrayList<Entry>();
        for (int i = 0; i < designData.size(); i++) {
            values.add(new Entry(i, Float.valueOf(designData.get(i).getValue())));
        }
        d = new LineDataSet(values, "设计");
        d.setLineWidth(2.5f);
//            d.setCircleRadius(4f);
        d.setColor(ColorTemplate.VORDIPLOM_COLORS[1]);
        d.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[1]);
        d.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return (int) value + "";
            }
        });
        return d;
    }

    /**
     * 安装
     *
     * @return
     */
    private LineDataSet getInstal() {
        LineDataSet d;
        ArrayList<Entry> values = new ArrayList<Entry>();
        for (int i = 0; i < instalDta.size(); i++) {
            values.add(new Entry(i, Float.valueOf(instalDta.get(i).getValue())));
        }
        d = new LineDataSet(values, "安装");
        d.setLineWidth(2.5f);
//            d.setCircleRadius(4f);
        d.setColor(ColorTemplate.VORDIPLOM_COLORS[2]);
        d.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[2]);
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
    private LineDataSet getMaintain() {
        LineDataSet d;
        ArrayList<Entry> values = new ArrayList<Entry>();
        for (int i = 0; i < maintainData.size(); i++) {
            values.add(new Entry(i, Float.valueOf(maintainData.get(i).getValue())));
        }
        d = new LineDataSet(values, "维修");
        d.setLineWidth(2.5f);
//            d.setCircleRadius(4f);
        d.setColor(ColorTemplate.VORDIPLOM_COLORS[3]);
        d.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[3]);
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
    private LineDataSet getMake() {
        LineDataSet d;
        ArrayList<Entry> values = new ArrayList<Entry>();
        for (int i = 0; i < makeData.size(); i++) {
            values.add(new Entry(i, Float.valueOf(makeData.get(i).getValue())));
        }
        d = new LineDataSet(values, "制造");
        d.setLineWidth(2.5f);
//            d.setCircleRadius(4f);
        d.setColor(ColorTemplate.VORDIPLOM_COLORS[4]);
        d.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[4]);
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
        for (int i = 0; i < reformData.size(); i++) {
            values.add(new Entry(i, Float.valueOf(reformData.get(i).getValue())));
        }
        d = new LineDataSet(values, "改造");
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


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.select:
                showSelectDialog();
                break;
            case R.id.year_section:
                String[] arr = DateUtils.getStrSplit(mYearSection.getText().toString(), "-");
                if (null == mDialog || (mDialog != null && !mDialog.isShowing())) {
                    mDialog = new SectionYearPickerDialog.Builder(StatisticsGrowthActivity.this)
                            .setStartDate(Integer.valueOf(arr[0]))
                            .setEndDate(Integer.valueOf(arr[1]))
                            .setNegativeButton("取消", null)
                            .setPositiveButton("确定", new SectionYearPickerDialog.Builder.OnDatePickListener() {
                                @Override
                                public void onDatePick(DialogInterface dialog, int witch, int... args) {
                                    if ((args[1] - args[0]) > 15) {
                                        showMsg("时间区间范围最大为15年");
                                    } else if (args[1] <= args[0]) {
                                        showMsg("结束年限必须大于开始年限");
                                    } else {
                                        mYearSection.setText(args[0] + "-" + args[1]);
                                        getData(args[0] + "", args[1] + "");
                                    }
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

    private void showSelectDialog() {
        final Dialog dialog = new Dialog(StatisticsGrowthActivity.this, R.style.UIDialog);
        View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.layout_growth_dialog, null);
        dialog.setContentView(view);
        final CheckBox mDesign, mMake, mInstal, mReform, mMaintain, mFill;
        Button mBtnNegative, mBtnPositive;
        mDesign = (CheckBox) view.findViewById(R.id.design);
        mMake = (CheckBox) view.findViewById(R.id.make);
        mInstal = (CheckBox) view.findViewById(R.id.instal);
        mReform = (CheckBox) view.findViewById(R.id.reform);
        mMaintain = (CheckBox) view.findViewById(R.id.maintain);
        mFill = (CheckBox) view.findViewById(R.id.fill);
        mBtnNegative = (Button) view.findViewById(R.id.btn_negative);
        mBtnNegative.setText("取消");
        mBtnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        mBtnPositive = (Button) view.findViewById(R.id.btn_positive);
        mBtnPositive.setText("确定");
        mBtnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
                if (mDesign.isCheck()) {
                    dataSets.add(getDesign());
                }
                if (mMake.isCheck()) {
                    dataSets.add(getMake());
                }
                if (mInstal.isCheck()) {
                    dataSets.add(getInstal());
                }
                if (mReform.isCheck()) {
                    dataSets.add(getReform());
                }
                if (mMaintain.isCheck()) {
                    dataSets.add(getMaintain());
                }
                if (mFill.isCheck()) {
                    dataSets.add(getCylinFillData());
                }
                invalidateChart(dataSets);
                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
