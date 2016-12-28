package com.wondersgroup.special.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.wondersgroup.special.R;
import com.wondersgroup.special.ResponseCallBack;
import com.wondersgroup.special.adapter.AnalysisAdapter;
import com.wondersgroup.special.constant.Constant;
import com.wondersgroup.special.constant.Params;
import com.wondersgroup.special.constant.UrlConstant;
import com.wondersgroup.special.datepicker.NumberPickerDialog;
import com.wondersgroup.special.datepicker.SectionDatePickerDialog;
import com.wondersgroup.special.datepicker.SectionYearPickerDialog;
import com.wondersgroup.special.entity.AnalysisResult;
import com.wondersgroup.special.utils.DateUtils;
import com.wondersgroup.special.widget.SVListView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

public class AnalysisActivity extends BaseActivity {
    private String title, mChartName;
    private SVListView mList;
    private TextView mTextChartName;
    private PieChart mPieChart;
    private HorizontalBarChart mHorizontalBarChart;
    private BarChart mBarChart;
    private LineChart mLineChart;
    private String mParentType;//大类别，表示锅炉、压力容器等
    private String mChildType;//大类别里的小类别,比如锅炉里的类别分析、使用场所分析等
    private List<AnalysisResult> data;
    private LinearLayout mLinearSection;
    private SectionDatePickerDialog mSectionDialog;
    private TextView mIntervalStartDate, mIntervalEndDate;
    private AnalysisAdapter analysisAdapter;
    private RelativeLayout mRelativeTop;
    private SectionYearPickerDialog mDialog;
    private HashMap<String, String> params;
    private TextView mNumber;
    private NumberPickerDialog mNumberPickerDialog;
    private String organId;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_analysis);
        mTitle.setText("");
        mRelativeTop = (RelativeLayout) findViewById(R.id.relative_top);
        mPieChart = (PieChart) findViewById(R.id.pie_chart);
        mHorizontalBarChart = (HorizontalBarChart) findViewById(R.id.horizontal_bar_chart);
        mBarChart = (BarChart) findViewById(R.id.bar_chart);
        mLineChart = (LineChart) findViewById(R.id.line_chart);
        mList = (SVListView) findViewById(R.id.list);
        mTextChartName = (TextView) findViewById(R.id.chart_name);
        mLinearSection = (LinearLayout) findViewById(R.id.linear_section);
        mLinearSection.setOnClickListener(this);
        mIntervalStartDate = (TextView) findViewById(R.id.interval_start_date);
        mIntervalStartDate.setText(DateUtils.getFirstDay());
        mIntervalEndDate = (TextView) findViewById(R.id.interval_end_date);
        mIntervalEndDate.setText(DateUtils.getToday());
        mNumber = (TextView) findViewById(R.id.number);
        mNumber.setOnClickListener(this);
        mNumber.setText("5");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        final Bundle bundle = getIntent().getExtras();
        mParentType = bundle.getString("PARENT_TYPE");
        mChildType = bundle.getString("CHILD_TYPE");
        organId = bundle.getString("ORGAN_ID");
        organId = TextUtils.isEmpty(organId) ? "" : organId;
        mParentType = TextUtils.isEmpty(mParentType) ? Constant.Overview.BOILER : mParentType;
        mChildType = TextUtils.isEmpty(mChildType) ? Constant.Boiler.CATEGORY : mChildType;
        data = new ArrayList<>();
        params = new HashMap<>();
        analysisAdapter = new AnalysisAdapter(AnalysisActivity.this, data);
        mList.setAdapter(analysisAdapter);
        mList.setOnItemClickListener(new SVListView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(AnalysisActivity.this, EquipmentListActivity.class);
                params.put("type", mParentType);
                params.put("orderNum", mChildType);
                params.put("param", data.get(position).getUuid());
                if (mChildType.equals(Constant.Boiler.PRESSURE)
                        || mChildType.equals(Constant.Elevator.SERVICE_LIFE)
                        || mChildType.equals(Constant.Lifting.TONNAGE)) {
                    params.put("param", data.get(position).getName());
                }
                if (mChildType.equals(Constant.Elevator.GROWTH)) {
                    params.put("startYear", mIntervalStartDate.getText().toString());
                    params.put("endYear", mIntervalEndDate.getText().toString());
                } else {
                    params.put("startDate", mIntervalStartDate.getText().toString());
                    params.put("endDate", mIntervalEndDate.getText().toString());
                }
                params.put("section", mNumber.getText().toString());
                bundle.putSerializable("PARAMS", params);
                bundle.putString(Params.PARENT_TYPE, Constant.HomeDateType.TYPE_ANALYSIS);
//                bundle.putString("UUID", data.get(position).getUuid());
//                bundle.putString("START_YEAR", mIntervalStartDate.getText().toString());
//                bundle.putString("END_YEAR", mIntervalEndDate.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        if (mChildType.equals(Constant.Elevator.GROWTH)) {
            mIntervalStartDate.setText(DateUtils.getSixAgeYear());
            mIntervalEndDate.setText(DateUtils.getThisYear());
        }
        getAnalysisData();
    }

    private void showChart() {
        switch (mParentType) {
            case Constant.Overview.BOILER:
                showBoiler();
                break;
            case Constant.Overview.VESSEL:
                showVessel();
                break;
            case Constant.Overview.CYLINDER:
                showCylinder();
                break;
            case Constant.Overview.PIPE:
                showPipe();
                break;
            case Constant.Overview.ELEVATOR:
                showElevator();
                break;
            case Constant.Overview.LIFTING:
                showLifting();
                break;
            case Constant.Overview.VEHICLE:
                showVehicle();
                break;
            case Constant.Overview.FACILITY:
                showFacility();
                break;
            default:
                showHBarChart();
                break;
        }
        mTitle.setText(title);
        mTextChartName.setText(mChartName);
    }

    /**
     * 锅炉分析
     */
    private void showBoiler() {
        switch (mChildType) {
            case Constant.Boiler.CATEGORY:
                title = "锅炉类别分析";
                mChartName = "锅炉类别和数量";
                showPieChart();
                showPieChartData();
                setPieChartData();
                break;
            case Constant.Boiler.AREA:
                title = "锅炉使用场所分析";
                mChartName = "锅炉使用场所和数量";
                showHBarChart();
                showHBarChartData();
                setHBarChartData();
                break;
            case Constant.Boiler.FUEL:
                title = "锅炉燃料种类分析";
                mChartName = "锅炉燃料种类和数量";
                showHBarChart();
                showHBarChartData();
                setHBarChartData();
                break;
            case Constant.Boiler.PRESSURE:
                title = "锅炉压力分析";
                mChartName = "锅炉压力和数量";
                showHBarChart();
                showHBarChartData();
                setHBarChartData();
                break;
            default:
                break;
        }
    }

    /**
     * 压力容器
     */
    private void showVessel() {
        switch (mChildType) {
            case Constant.Vessel.CATEGORY:
                title = "压力容器类别分析";
                mChartName = "压力容器类别和数量";
                showPieChart();
                showPieChartData();
                setPieChartData();
                break;
            case Constant.Vessel.AREA:
                title = "压力容器使用场所分析";
                mChartName = "压力容器使用场所和数量";
                showHBarChart();
                showHBarChartData();
                setHBarChartData();
                break;
            case Constant.Vessel.PURPOSE:
                title = "压力容器用途分析";
                mChartName = "压力容器用途和数量";
                showHBarChart();
                showHBarChartData();
                setHBarChartData();
                break;
            case Constant.Vessel.EQUIPMENT:
                title = "压力容器特殊设备分析";
                mChartName = "压力容器特殊设备和数量";
                showHBarChart();
                showHBarChartData();
                setHBarChartData();
                break;
            default:
                break;
        }
    }

    /**
     * 气瓶
     */
    private void showCylinder() {
        switch (mChildType) {
            case Constant.Cylinder.CATEGORY:
                showHBarChart();
                break;
            case Constant.Cylinder.FILLING:
                showHBarChart();
                break;
            case Constant.Cylinder.MEDIUM:
                showHBarChart();
                break;
            case Constant.Cylinder.VOLUME:
                showHBarChart();
                break;
            default:
                break;
        }
    }

    /**
     * 压力管道
     */
    private void showPipe() {
        switch (mChildType) {
            case Constant.Pipe.CATEGORY:
                title = "压力管道类别分析";
                mChartName = "压力管道类别和数量";
                showPieChart();
                showPieChartData();
                setPieChartData();
                break;
            case Constant.Pipe.MEDIUM:
                title = "压力管道根据介质分类";
                mChartName = "压力管道介质和数量";
                showHBarChart();
                showHBarChartData();
                setHBarChartData();
                break;
            default:
                break;
        }
    }

    /**
     * 电梯
     */
    private void showElevator() {
        switch (mChildType) {
            case Constant.Elevator.CATEGORY:
                title = "电梯类别分析";
                mChartName = "电梯类别和数量";
                showHBarChart();
                showHBarChartData();
                setHBarChartData();
                break;
            case Constant.Elevator.AREA:
                title = "电梯使用场所分析";
                mChartName = "电梯使用场所和数量";
                showHBarChart();
                showHBarChartData();
                setHBarChartData();
                break;
            case Constant.Elevator.DISTRIBUTION:
                title = "电梯分布情况分析";
                mChartName = "电梯分布情况和数量";
                showHBarChart();
                showHBarChartData();
                setHBarChartData();
                break;
            case Constant.Elevator.SERVICE_LIFE:
                title = "住宅电梯使用年限分析";
                mChartName = "住宅电梯使用年限和数量";
                showNumberText();
                showHBarChart();
                showHBarChartData();
                setHBarChartData();
                break;
            case Constant.Elevator.EVALUATION:
                title = "电梯评估分析";
                mChartName = "电梯评估";
                showBarChart();
                showBarChartData();
                setBarChartData();
                break;
            case Constant.Elevator.GROWTH:
                title = "电梯增长情况分析";
                mChartName = "电梯增长情况和数量";
                showLineChart();
                showLineChartData();
                if (data.size() != 0) {
                    setLineChartData();
                }
                break;
            case Constant.Elevator.MAINTENANCE:
                title = "电梯维保单位分析";
                mChartName = "电梯维保单位和数量";
                mRelativeTop.setVisibility(View.GONE);
                showList();
                break;
            case Constant.Elevator.RENOVATION:
                title = "住宅电梯改造情况分析";
                mChartName = "安装";
                showBarChart();
                showBarChartData();
                setRenovationBarChartData();
                break;
            default:
                break;
        }
    }

    /**
     * 起重机械
     */
    private void showLifting() {
        switch (mChildType) {
            case Constant.Lifting.CATEGORY:
                title = "起重机械类别分析";
                mChartName = "起重机械类别和数量";
                showHBarChart();
                showHBarChartData();
                setHBarChartData();
                break;
            case Constant.Lifting.AREA:
                title = "起重机械使用场所分析";
                mChartName = "起重机械使用场所和数量";
                showHBarChart();
                showHBarChartData();
                setHBarChartData();
                break;
            case Constant.Lifting.TONNAGE:
                title = "起重机械吨位分析";
                mChartName = "起重机械吨位和数量";
                showNumberText();
                showHBarChart();
                showHBarChartData();
                setHBarChartData();
                break;
            default:
                break;
        }
    }

    /**
     * 场内车辆
     */
    private void showVehicle() {
        switch (mChildType) {
            case Constant.Vehicle.CATEGORY:
                title = "厂内车辆类别分析";
                mChartName = "厂内车辆类别和数量";
                showHBarChart();
                showHBarChartData();
                setHBarChartData();
                break;
            default:
                break;
        }
    }

    /**
     * 游乐设施
     */
    private void showFacility() {
        switch (mChildType) {
            case Constant.Facility.CATEGORY:
                title = "游乐设施类型分析";
                mChartName = "游乐设施类别和数量";
                showHBarChart();
                showHBarChartData();
                setHBarChartData();
                break;
            case Constant.Facility.LEVEL:
                title = "游乐设施按设备级别分析";
                mChartName = "游乐设施设备级别和数量";
                showHBarChart();
                showHBarChartData();
                setHBarChartData();
                break;
            default:
                break;
        }
    }

    private void showNumberText() {
        mLinearSection.setVisibility(View.GONE);
        mNumber.setVisibility(View.VISIBLE);
    }

    /**
     * 显示列表
     */
    private void showList() {
        mPieChart.setVisibility(View.GONE);
        mHorizontalBarChart.setVisibility(View.GONE);
        mLineChart.setVisibility(View.GONE);
        mBarChart.setVisibility(View.GONE);
    }

    /**
     * 显示饼图
     */
    private void showPieChart() {
        mPieChart.setVisibility(View.VISIBLE);
        mHorizontalBarChart.setVisibility(View.GONE);
        mLineChart.setVisibility(View.GONE);
        mBarChart.setVisibility(View.GONE);
    }

    /**
     * 显示横向柱状图
     */
    private void showHBarChart() {
        mPieChart.setVisibility(View.GONE);
        mHorizontalBarChart.setVisibility(View.VISIBLE);
        mLineChart.setVisibility(View.GONE);
        mBarChart.setVisibility(View.GONE);
    }

    /**
     * 显示纵向柱状图
     */
    private void showBarChart() {
        mPieChart.setVisibility(View.GONE);
        mHorizontalBarChart.setVisibility(View.GONE);
        mBarChart.setVisibility(View.VISIBLE);
        mLineChart.setVisibility(View.GONE);
    }

    /**
     * 显示线形图
     */
    private void showLineChart() {
        mPieChart.setVisibility(View.GONE);
        mHorizontalBarChart.setVisibility(View.GONE);
        mBarChart.setVisibility(View.GONE);
        mLineChart.setVisibility(View.VISIBLE);
    }

    /**
     * 显示饼图数据
     */
    private void showPieChartData() {
        mPieChart.setUsePercentValues(true);
        mPieChart.getDescription().setEnabled(false);
        mPieChart.setExtraOffsets(5, 10, 5, 5);

        mPieChart.setDragDecelerationFrictionCoef(0.95f);
        mPieChart.setDrawHoleEnabled(false);
        mPieChart.setHoleColor(Color.WHITE);

        mPieChart.setTransparentCircleColor(Color.WHITE);
        mPieChart.setTransparentCircleAlpha(110);

        mPieChart.setHoleRadius(58f);
        mPieChart.setTransparentCircleRadius(61f);

        mPieChart.setDrawCenterText(false);

        mPieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mPieChart.setRotationEnabled(true);
        mPieChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
//        mChart.setOnChartValueSelectedListener(this);

        mPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        Legend l = mPieChart.getLegend();
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
        mPieChart.setEntryLabelColor(Color.BLACK);
        mPieChart.setEntryLabelTextSize(10f);

        mPieChart.setNoDataText("没有数据");
    }

    private void setPieChartData() {
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
//        dataSet.setValueLinePart1OffsetPercentage(80.f);
//        dataSet.setValueLinePart1Length(0.2f);
//        dataSet.setValueLinePart2Length(0.4f);
//        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);
        mPieChart.setData(data);
        // undo all highlights
        mPieChart.highlightValues(null);

        mPieChart.invalidate();
    }

    /**
     * 显示横向柱状图数据
     */
    private void showHBarChartData() {
        mHorizontalBarChart.setDrawBarShadow(false);

        mHorizontalBarChart.setDrawValueAboveBar(true);

        mHorizontalBarChart.getDescription().setEnabled(false);
        mHorizontalBarChart.setTouchEnabled(true);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mHorizontalBarChart.setMaxVisibleValueCount(120);

        // scaling can now only be done on x- and y-axis separately
        mHorizontalBarChart.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        // mChart.setDrawBarShadow(true);

        mHorizontalBarChart.setDrawGridBackground(false);
        mHorizontalBarChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if (mChildType.equals(Constant.Elevator.DISTRIBUTION)) {
                    if (e == null)
                        return;
                    Intent intent = new Intent(AnalysisActivity.this, AnalysisActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("PARENT_TYPE", mParentType);
                    bundle.putString("CHILD_TYPE", mChildType);
                    bundle.putString("START_YEAR", mIntervalStartDate.getText().toString());
                    bundle.putString("END_YEAR", mIntervalEndDate.getText().toString());
                    bundle.putString("ORGAN_ID", data.get((int) e.getX()).getUuid());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected() {
            }
        });

        XAxis xl = mHorizontalBarChart.getXAxis();
        xl.setValueFormatter(new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String content = data.get((int) value).getName();
                if (content.length() > 6) {
                    content = content.substring(0, 5) + "...";
                }
                return content;
            }
        });
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(true);
        xl.setLabelCount(data.size());
        xl.setGranularity(1f);

        YAxis yl = mHorizontalBarChart.getAxisLeft();
        yl.setEnabled(false);
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(true);
        yl.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        yl.setInverted(true);

        YAxis yr = mHorizontalBarChart.getAxisRight();
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);
        yr.setGranularity(1f);
        yr.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        yr.setInverted(true);
//        mHorizontalBarChart.setOnChartValueSelectedListener(null);
        mHorizontalBarChart.setFitBars(true);
        mHorizontalBarChart.animateY(2500);

        Legend l = mHorizontalBarChart.getLegend();
        l.setEnabled(false);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setXEntrySpace(4f);

        mHorizontalBarChart.setNoDataText("没有数据");
    }

    private void setHBarChartData() {
        float barWidth = 0.45f;
        float spaceForBar = 10f;
        String[] arr = new String[]{"商住两用楼", "其他场所", "文体场馆", "学校", "医院", "交通场所", "宾馆饭店", "商场超市", "办公楼宇", "住宅"};
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
//        yVals1.add(new BarEntry(0, 10));
//        yVals1.add(new BarEntry(1, 20));
//        yVals1.add(new BarEntry(2, 30));
//        yVals1.add(new BarEntry(3, 40));
//        yVals1.add(new BarEntry(4, 50));
//        yVals1.add(new BarEntry(5, 60));
//        yVals1.add(new BarEntry(6, 70));
//        yVals1.add(new BarEntry(7, 80));
//        yVals1.add(new BarEntry(8, 90));
//        yVals1.add(new BarEntry(9, 100));
        for (int i = 0; i < data.size(); i++) {
            float val = TextUtils.isEmpty(data.get(i).getValue()) ? 0 : Float.valueOf(data.get(i).getValue());
            yVals1.add(new BarEntry(i, val));
        }

        BarDataSet set1;

        if (mHorizontalBarChart.getData() != null &&
                mHorizontalBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mHorizontalBarChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mHorizontalBarChart.getData().notifyDataChanged();
            mHorizontalBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "");

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueFormatter(new IValueFormatter() {

                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return String.valueOf((int) value);
                }
            });
            data.setValueTextSize(10f);
            data.setBarWidth(barWidth);
            mHorizontalBarChart.setData(data);
            mHorizontalBarChart.invalidate();
        }
    }

    /**
     * 显示柱状图
     */
    private void showBarChartData() {
        mBarChart.setDrawBarShadow(false);
        mBarChart.setDrawValueAboveBar(true);

        mBarChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mBarChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        mBarChart.setPinchZoom(false);

        mBarChart.setDrawGridBackground(false);
        // mChart.setDrawYLabels(false);

        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return data.get((int) value).getName();
            }

//            @Override
//            public int getDecimalDigits() {
//                return 0;
//            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setCenterAxisLabels(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(data.size());
//        xAxis.setValueFormatter(xAxisFormatter);

        YAxis leftAxis = mBarChart.getAxisLeft();
//        leftAxis.setLabelCount(data.size());
//        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = mBarChart.getAxisRight();
        rightAxis.setEnabled(false);
        rightAxis.setDrawGridLines(false);
        rightAxis.setLabelCount(8, false);
//        rightAxis.setValueFormatter(custom);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        Legend l = mBarChart.getLegend();
        l.setEnabled(false);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);
        mBarChart.setNoDataText("没有数据");

//        setBarChartData();
    }

    private void setBarChartData() {
        float start = 1f;

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = 0; i < data.size(); i++) {
            float val = TextUtils.isEmpty(data.get(i).getValue()) ? 0 : Float.valueOf(data.get(i).getValue());
            yVals1.add(new BarEntry(i, val));
        }

        BarDataSet set1;

        if (mBarChart.getData() != null &&
                mBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mBarChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mBarChart.getData().notifyDataChanged();
            mBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "");
            set1.setColors(ColorTemplate.MATERIAL_COLORS);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);

            mBarChart.setData(data);
        }
    }

    /**
     * 住宅电梯改造情况分析
     */
    private void setRenovationBarChartData() {
        float groupSpace = 0.08f;
        float barSpace = 0.03f;
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals3 = new ArrayList<BarEntry>();

        for (int i = 0; i < data.size(); i++) {
            float val = TextUtils.isEmpty(data.get(i).getValue()) ? 0 : Float.valueOf(data.get(i).getValue());
            yVals1.add(new BarEntry(i, val));
            yVals2.add(new BarEntry(i, val));
            yVals3.add(new BarEntry(i, val));
        }

        BarDataSet set1, set2, set3;

        if (mBarChart.getData() != null &&
                mBarChart.getData().getDataSetCount() > 2) {
            set1 = (BarDataSet) mBarChart.getData().getDataSetByIndex(0);
            set2 = (BarDataSet) mBarChart.getData().getDataSetByIndex(1);
            set3 = (BarDataSet) mBarChart.getData().getDataSetByIndex(2);
            set1.setValues(yVals1);
            set2.setValues(yVals2);
            set3.setValues(yVals3);
            mBarChart.getData().notifyDataChanged();
            mBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "大修");
            set1.setColor(Color.rgb(104, 241, 175));
            set2 = new BarDataSet(yVals2, "更新");
            set2.setColor(Color.rgb(164, 228, 251));
            set3 = new BarDataSet(yVals3, "改造");
            set3.setColor(Color.rgb(242, 247, 158));

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData barData = new BarData(set1, set2, set3);
            barData.setValueTextSize(10f);
            barData.setBarWidth(0.25f);

            mBarChart.setData(barData);
            // specify the width each bar should have
//            mBarChart.getBarData().setBarWidth(barWidth);

            // restrict the x-axis range
            mBarChart.getXAxis().setAxisMinimum(0);
            // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
            mBarChart.getXAxis().setAxisMaximum(0 + mBarChart.getBarData().getGroupWidth(groupSpace, barSpace) * data.size());
            mBarChart.groupBars(0, groupSpace, barSpace);
            mBarChart.getLegend().setEnabled(true);
            mBarChart.invalidate();
        }
    }

    private void showLineChartData() {
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

        // add data
        mLineChart.animateX(2500);

        // get the legend (only possible after setting data)
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
        xAxis.setLabelCount(data.size() - 1);
        xAxis.setTextSize(11f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
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
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);

        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setAxisMaximum(getMaxHeight(data) + 20f);
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
        mLineChart.setNoDataText("没有数据");
    }

    private void setLineChartData() {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        LineDataSet set1;
        for (int i = 0; i < data.size(); i++) {
            float val = TextUtils.isEmpty(data.get(i).getValue()) ? 0 : Float.valueOf(data.get(i).getValue());
            yVals1.add(new Entry(i, val));
        }
        if (mLineChart.getData() != null &&
                mLineChart.getData().getDataSets().size() > 0) {
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

            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1); // add the datasets
            // create a data object with the datasets
            LineData lData = new LineData(dataSets);
            lData.setValueTextColor(Color.BLACK);
            lData.setValueTextSize(9f);

            // set data
            mLineChart.setData(lData);
            mLineChart.invalidate();
        }
    }

    private void getAnalysisData() {
        HashMap<String, String> params = new HashMap<>();
        params.put("type", mParentType);
        params.put("orderNum", mChildType);
        params.put("section", mNumber.getText().toString());
        if (mChildType.equals(Constant.Elevator.SERVICE_LIFE)
                || mChildType.equals(Constant.Elevator.GROWTH)) {
            params.put("startYear", mIntervalStartDate.getText().toString());
            params.put("endYear", mIntervalEndDate.getText().toString());
        } else {
            params.put("startDate", mIntervalStartDate.getText().toString());
            params.put("endDate", mIntervalEndDate.getText().toString());
        }
        params.put("organId", organId);
        OkHttpUtils.get().url(UrlConstant.GET_ANALYSIS_DATA)
                .params(params)
//                .addParams("type", mParentType)
//                .addParams("orderNum", mChildType)
////                .addParams("organId")
//                .addParams("startDate", mIntervalStartDate.getText().toString())
//                .addParams("endDate", mIntervalEndDate.getText().toString())
//                .addParams("startYear")
//                .addParams("endYear")
                .build().execute(new ResponseCallBack<AnalysisResult>() {
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
            public void onResponse(List<AnalysisResult> result) {
                super.onResponse(result);
                if (null != result) {
                    data.clear();
                    data.addAll(result);
                    mList.setAdapter(analysisAdapter);
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

    public float getMaxHeight(List<AnalysisResult> datas) {
        int max = 0;
        for (AnalysisResult analysisResult : datas) {
            if (max < Integer.parseInt(analysisResult.getValue()))
                max = Integer.parseInt(analysisResult.getValue());
        }
        return (float) max;
    }

    /**
     * 选择时间
     */
    private void condition() {
        if (mChildType.equals(Constant.Elevator.GROWTH)) {
            if (null == mDialog || (mDialog != null && !mDialog.isShowing())) {
                mDialog = new SectionYearPickerDialog.Builder(AnalysisActivity.this)
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
        } else {
            String[] arrStart = DateUtils.getStrSplit(mIntervalStartDate.getText().toString(), "-");
            String[] arrEnd = DateUtils.getStrSplit(mIntervalEndDate.getText().toString(), "-");
            if (null == mSectionDialog || (mSectionDialog != null && !mSectionDialog.isShowing())) {
                mSectionDialog = new SectionDatePickerDialog.Builder(AnalysisActivity.this)
                        .setStartDate(Integer.valueOf(arrStart[0]), Integer.valueOf(arrStart[1]), Integer.valueOf(arrStart[2]))
                        .setEndDate(Integer.valueOf(arrEnd[0]), Integer.valueOf(arrEnd[1]), Integer.valueOf(arrEnd[2]))
                        .setNegativeButton("取消", null).setPositiveButton("确定", new SectionDatePickerDialog.Builder.OnDatePickListener() {
                            @Override
                            public void onDatePick(DialogInterface dialog, int witch, int... args) {
                                mIntervalStartDate.setText(args[0] + "-" + args[1] + "-" + args[2]);
                                mIntervalEndDate.setText(args[3] + "-" + args[4] + "-" + args[5]);
                                getAnalysisData();
                            }
                        }).create();
                mSectionDialog.setCanceledOnTouchOutside(false);
                mSectionDialog.show();
            }
        }
    }

    /**
     * 点击数字
     */
    private void number() {
        if (null == mNumberPickerDialog || (null != mNumberPickerDialog && !mNumberPickerDialog.isShowing())) {
            mNumberPickerDialog = new NumberPickerDialog.Builder(AnalysisActivity.this)
                    .setMinValue(1)
                    .setMaxValue(36)
                    .setValue(Integer.valueOf(mNumber.getText().toString()))
                    .setNegativeButton("取消", null)
                    .setPositiveButton("确定", new NumberPickerDialog.Builder.OnNumberPickListener() {
                        @Override
                        public void onNumberPick(DialogInterface dialog, int witch, int... args) {
                            mNumber.setText(String.valueOf(args[0]));
                            getAnalysisData();
                        }
                    }).create();
            mNumberPickerDialog.setCanceledOnTouchOutside(false);
            mNumberPickerDialog.show();
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.linear_section:
                condition();
                break;
            case R.id.number:
                number();
                break;
        }
    }
}
