package com.wondersgroup.special.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.CombinedChart;
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
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.wondersgroup.special.R;
import com.wondersgroup.special.ResponseCallBack;
import com.wondersgroup.special.activity.AccidentListActivity;
import com.wondersgroup.special.activity.CaseListActivity;
import com.wondersgroup.special.activity.EquipmentListActivity;
import com.wondersgroup.special.activity.RecordActivity;
import com.wondersgroup.special.activity.unit.ProductUnitListActivity;
import com.wondersgroup.special.activity.unit.UseUnitActivity;
import com.wondersgroup.special.activity.unit.UseUnitListActivity;
import com.wondersgroup.special.constant.Params;
import com.wondersgroup.special.homepage.EmergentActivity;
import com.wondersgroup.special.homepage.EquipmentOverviewActivity;
import com.wondersgroup.special.homepage.PlaceActivity;
import com.wondersgroup.special.homepage.TotalEquipmentActivity;
import com.wondersgroup.special.constant.Constant;
import com.wondersgroup.special.constant.UrlConstant;
import com.wondersgroup.special.datepicker.DatePickerDialog;
import com.wondersgroup.special.datepicker.SectionDatePickerDialog;
import com.wondersgroup.special.datepicker.YearPickerDialog;
import com.wondersgroup.special.entity.AnalysisResult;
import com.wondersgroup.special.entity.EquipUnitTotal;
import com.wondersgroup.special.entity.SectionOverviewResult;
import com.wondersgroup.special.entity.WholeOverviewResult;
import com.wondersgroup.special.homepage.HomeDataActivity;
import com.wondersgroup.special.utils.DateUtils;
import com.wondersgroup.special.widget.DetailScrollview;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by chan on 11/2/16.
 */

public class HomeFragment extends BaseFragment implements DetailScrollview.OnScrollListener, View.OnClickListener {
    private View mParentView;
    private LinearLayout deviceOverviewDate, deviceUseInfoDate, elevatorUseInfoDate;
    private TextView deviceOverviewStartDate, deviceOverviewEndDate, deviceUseInfostartDate, deviceUseInfoEndDate, elevatorUseInfostartDate, elevatorUseInfoEndDate, emergencyDate;
    private TextView mOverviewDate, mTextEquipmentNo, mTextUseUnitNo, mTextProductionUnitNo, mTextManagementUnitNo,
            mTextElevatorMaintenanceUnit, mTextOverdueRate;
    private TextView mIntervalStartDate, mIntervalEndDate, mTextCheck, mTextCheckEquipment, mTextHiddenRectificationRate, mTextAccident, mTextCases;
    private LinearLayout mLinearPieChart, mLinearCombinedChart, mLinearHorizontalChart, mLinearLineChart;
    private boolean isLoadCombinedChart = false, isLoadHorChart = false, isLoadLineChart = false;
    private int mDetailScrollHeight, mLinearCombinedChartTop, mLinearHorizontalChartTop, mLinearLineChartTop;
    private DetailScrollview mDetailScroll;
    private TextSwitcher mTextSwitcher;
    private PieChart mChart;
    private CombinedChart mCombinedChart;
    private HorizontalBarChart mHorizontalBarChart;
    private LineChart mLineChart;
    private String[] msgArr = {"aaaa", "bbbb"};
    private String[] arrStart = new String[3];
    private String[] arrEnd = new String[3];
    private int index = 0;
    private ViewSwitcher.ViewFactory factory;
    private DatePickerDialog mDialog;
    private YearPickerDialog mYearDialog;
    private SectionDatePickerDialog mSectionDialog;
    private LinearLayout mLinearSection;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTextSwitcher.setText(msgArr[index]);
            index++;
            if (index == msgArr.length) index = 0;
        }
    };

    private Thread thread = new Thread() {

        @Override
        public void run() {
            super.run();
            while (index < msgArr.length) {
                try {
                    synchronized (this) {
                        handler.sendEmptyMessage(0);
                        this.sleep(2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private int scrollViewHeight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mParentView = getView();
        ((TextView) mParentView.findViewById(R.id.title)).setText("首页");
        mDetailScroll = (DetailScrollview) mParentView.findViewById(R.id.detail_scroll);
        ViewTreeObserver viewTreeObserver = mDetailScroll.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mDetailScrollHeight = mDetailScroll.getHeight();
            }
        });
        mDetailScroll.setOnScrollListener(this);
        mLinearSection = (LinearLayout) mParentView.findViewById(R.id.linear_section);
        mLinearSection.setOnClickListener(this);
        initWholeOverview();
        initSectionOverview();
        //设备概览
        initEquipmentOverview();
        mLinearPieChart = (LinearLayout) mParentView.findViewById(R.id.linear_pie_chart);
        mLinearCombinedChart = (LinearLayout) mParentView.findViewById(R.id.linear_combined_chart);
        mLinearHorizontalChart = (LinearLayout) mParentView.findViewById(R.id.linear_horizontal_chart);
        mLinearLineChart = (LinearLayout) mParentView.findViewById(R.id.linear_line_chart);
        mParentView.findViewById(R.id.linear_pie).setOnClickListener(this);
//        mParentView.findViewById(R.id.linear_combined).setOnClickListener(this);
        mParentView.findViewById(R.id.linear_hbar).setOnClickListener(this);
        mParentView.findViewById(R.id.linear_line).setOnClickListener(this);
        mLinearCombinedChart.setOnClickListener(this);
        mLinearHorizontalChart.setOnClickListener(this);
        mLinearLineChart.setOnClickListener(this);
        mTextSwitcher = (TextSwitcher) mParentView.findViewById(R.id.switcher);
        factory = new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(getActivity());
                textView.setSingleLine();
                textView.setTextSize(12);
                textView.setTextColor(getActivity().getResources().getColor(R.color.text_grey));
                textView.setEllipsize(TextUtils.TruncateAt.END);
                FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
                );
                lp.gravity = Gravity.LEFT;
                textView.setLayoutParams(lp);
                return textView;
            }
        };
        mTextSwitcher.setFactory(factory);
//        thread.start();
        getViewTop();
        mCombinedChart = (CombinedChart) mParentView.findViewById(R.id.combined_chart);
//        mCombinedChart.onTouchEvent();
        mCombinedChart.setOnChartGestureListener(new OnChartGestureListener() {
            @Override
            public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

            }

            @Override
            public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

            }

            @Override
            public void onChartLongPressed(MotionEvent me) {

            }

            @Override
            public void onChartDoubleTapped(MotionEvent me) {

            }

            @Override
            public void onChartSingleTapped(MotionEvent me) {
                Intent intent = new Intent(getActivity(), TotalEquipmentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("START_YEAR", deviceUseInfostartDate.getText().toString());
                bundle.putString("END_YEAR", deviceUseInfoEndDate.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

            }

            @Override
            public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

            }

            @Override
            public void onChartTranslate(MotionEvent me, float dX, float dY) {

            }
        });
        mHorizontalBarChart = (HorizontalBarChart) mParentView.findViewById(R.id.horizontal_bar_chart);
        mLineChart = (LineChart) getActivity().findViewById(R.id.line_chart);

        ViewTreeObserver observer1 = mLinearCombinedChart.getViewTreeObserver();
        observer1.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mLinearCombinedChartTop = mLinearCombinedChart.getTop();
                return true;
            }
        });

        ViewTreeObserver observer2 = mLinearHorizontalChart.getViewTreeObserver();
        observer2.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mLinearHorizontalChartTop = mLinearHorizontalChart.getTop();
                return true;
            }
        });

        ViewTreeObserver observer3 = mLinearLineChart.getViewTreeObserver();
        observer3.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mLinearLineChartTop = mLinearLineChart.getTop();
                return true;
            }
        });
    }

    private void getViewTop() {
        mDetailScroll.getViewTreeObserver().addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() {
            @Override
            public void onWindowFocusChanged(boolean hasFocus) {
                scrollViewHeight = mDetailScroll.getHeight();
            }
        });
        mLinearCombinedChart.getViewTreeObserver().addOnWindowFocusChangeListener(
                new ViewTreeObserver.OnWindowFocusChangeListener() {
                    public void onWindowFocusChanged(boolean hasFocus) {
                        mLinearCombinedChartTop = mLinearCombinedChart.getTop() - scrollViewHeight;
                    }
                });
        mLinearHorizontalChart.getViewTreeObserver().addOnWindowFocusChangeListener(
                new ViewTreeObserver.OnWindowFocusChangeListener() {
                    public void onWindowFocusChanged(boolean hasFocus) {
                        mLinearHorizontalChartTop = mLinearHorizontalChart.getTop() - scrollViewHeight;
                    }
                });
        mLinearLineChart.getViewTreeObserver().addOnWindowFocusChangeListener(
                new ViewTreeObserver.OnWindowFocusChangeListener() {
                    public void onWindowFocusChanged(boolean hasFocus) {
                        mLinearLineChartTop = mLinearLineChart.getTop() - scrollViewHeight;
                    }
                });
    }

    @Override
    public void onVisible() {
    }

    /**
     * 加下划线
     *
     * @param tv
     */
    private void addUnderLine(TextView tv) {
        tv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tv.getPaint().setAntiAlias(true);//抗锯齿
        tv.setTextColor(getResources().getColor(R.color.theme));
    }

    /**
     * 初始化整体数据概览
     */
    private void initWholeOverview() {
        mOverviewDate = (TextView) mParentView.findViewById(R.id.overall_data_overview_date);
        mOverviewDate.setText(DateUtils.getYesterday());
        mOverviewDate.setOnClickListener(this);
        mTextEquipmentNo = (TextView) mParentView.findViewById(R.id.text_equipment_no);
        mTextUseUnitNo = (TextView) mParentView.findViewById(R.id.text_use_unit_no);
        mTextProductionUnitNo = (TextView) mParentView.findViewById(R.id.text_production_unit_no);
        mTextManagementUnitNo = (TextView) mParentView.findViewById(R.id.text_management_unit_no);
        mTextElevatorMaintenanceUnit = (TextView) mParentView.findViewById(R.id.text_elevator_maintenance_unit);
        mTextOverdueRate = (TextView) mParentView.findViewById(R.id.text_overdue_rate);
        addUnderLine(mTextEquipmentNo);
        addUnderLine(mTextUseUnitNo);
        addUnderLine(mTextProductionUnitNo);
        addUnderLine(mTextManagementUnitNo);
        addUnderLine(mTextElevatorMaintenanceUnit);
        mParentView.findViewById(R.id.linear_equipment_no).setOnClickListener(this);
        mParentView.findViewById(R.id.linear_use).setOnClickListener(this);
        mParentView.findViewById(R.id.linear_product).setOnClickListener(this);
        mParentView.findViewById(R.id.linear_manage).setOnClickListener(this);
        mParentView.findViewById(R.id.linear_elevator).setOnClickListener(this);
        getWholeOverviewData();
    }

    /**
     * 初始化区间数据概览
     */
    private void initSectionOverview() {
        mIntervalStartDate = (TextView) mParentView.findViewById(R.id.interval_start_date);
        mIntervalStartDate.setText(DateUtils.getFirstDay());
        mIntervalEndDate = (TextView) mParentView.findViewById(R.id.interval_end_date);
        mIntervalEndDate.setText(DateUtils.getToday());
        mTextCheck = (TextView) mParentView.findViewById(R.id.text_check);
        mTextCheckEquipment = (TextView) mParentView.findViewById(R.id.text_check_equipment);
        mTextHiddenRectificationRate = (TextView) mParentView.findViewById(R.id.text_hidden_rectification_rate);
        mTextAccident = (TextView) mParentView.findViewById(R.id.text_accident);
        mTextCases = (TextView) mParentView.findViewById(R.id.text_cases);
        mTextCheck.setOnClickListener(this);
//        mTextCheckEquipment.setOnClickListener(this);
        mTextAccident.setOnClickListener(this);
        mTextCases.setOnClickListener(this);
        addUnderLine(mTextCheck);
        addUnderLine(mTextCheckEquipment);
        addUnderLine(mTextAccident);
        addUnderLine(mTextCases);
        mParentView.findViewById(R.id.linear_check_equ).setOnClickListener(this);
        getSectionOverviewData();
    }

    /**
     * 获取整体数据概览
     */
    private void getWholeOverviewData() {
        OkHttpUtils.get().url(UrlConstant.GET_WHOLE_OVERVIEW)
                .addParams("type", Constant.Overview.HOME_PAGE)
                .addParams("endDate", mOverviewDate.getText().toString())
                .build()
                .execute(new ResponseCallBack<WholeOverviewResult>() {
                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                        showDialog();
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
                        dismissDialog();
                    }

                    @Override
                    public void onResponse(WholeOverviewResult result) {
                        super.onResponse(result);
                        if (null != result) {
                            mTextEquipmentNo.setText(result.getUseNum() + "台");
                            mTextUseUnitNo.setText(result.getComNum() + "家");
                            mTextProductionUnitNo.setText(result.getProNum() + "家");
                            mTextManagementUnitNo.setText(result.getUnitNum() + "户");
                            mTextElevatorMaintenanceUnit.setText(result.getRepairNum() + "家");
                            mTextOverdueRate.setText((TextUtils.isEmpty(result.getRate()) ? "0" : result.getRate()) + "%");
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        super.onError(call, e);
                        mTextEquipmentNo.setText("0台");
                        mTextUseUnitNo.setText("0家");
                        mTextProductionUnitNo.setText("0家");
                        mTextManagementUnitNo.setText("0户");
                        mTextElevatorMaintenanceUnit.setText("0家");
                        mTextOverdueRate.setText("0%");
                    }
                });
    }

    /**
     * 获取区间数据概览
     */
    private void getSectionOverviewData() {
        OkHttpUtils.get().url(UrlConstant.GET_SECTION_OVERVIEW)
                .addParams("type", Constant.Overview.HOME_PAGE)
                .addParams("startDate", mIntervalStartDate.getText().toString())
                .addParams("endDate", mIntervalEndDate.getText().toString())
                .build()
                .execute(new ResponseCallBack<SectionOverviewResult>() {
                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                        showDialog();
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
                        dismissDialog();
                    }

                    @Override
                    public void onResponse(SectionOverviewResult result) {
                        super.onResponse(result);
                        if (null != result) {
                            mTextCheck.setText((TextUtils.isEmpty(result.getCheckNum()) ? "0" : result.getCheckNum()) + "家");
                            mTextCheckEquipment.setText((TextUtils.isEmpty(result.getEquipNum()) ? "0" : result.getEquipNum()) + "台");
                            mTextHiddenRectificationRate.setText((TextUtils.isEmpty(result.getRate()) ? "0" : result.getRate()) + "%");
                            mTextAccident.setText((TextUtils.isEmpty(result.getAccidentNum()) ? "0" : result.getAccidentNum()) + "件");
                            mTextCases.setText((TextUtils.isEmpty(result.getAccidentNum()) ? "0" : result.getAccidentNum()) + "件");
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        super.onError(call, e);
                        mTextCheck.setText("0家");
                        mTextCheckEquipment.setText("0台");
                        mTextAccident.setText("0件");
                        mTextCases.setText("0件");
                        mTextHiddenRectificationRate.setText("0%");
                    }
                });
    }

    /**
     * 初始化设备预览
     */
    private void initEquipmentOverview() {
        deviceOverviewDate = (LinearLayout) mParentView.findViewById(R.id.deviceOverviewDate);
        deviceOverviewDate.setOnClickListener(this);
        deviceOverviewStartDate = (TextView) deviceOverviewDate.findViewById(R.id.startDate);
        deviceOverviewStartDate.setText(DateUtils.getFirstDay());
        deviceOverviewEndDate = (TextView) deviceOverviewDate.findViewById(R.id.endDate);
        deviceOverviewEndDate.setText(DateUtils.getToday());
        mChart = (PieChart) mParentView.findViewById(R.id.pie_chart);
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
        mChart.setTouchEnabled(false);

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
        mChart.setDrawEntryLabels(false);
        mChart.setEntryLabelColor(Color.BLACK);
        mChart.setEntryLabelTextSize(10f);
    }

    /**
     * 初始化设备和使用单位总量
     */
    private void initTotalEquipment() {
        deviceUseInfoDate = (LinearLayout) mParentView.findViewById(R.id.deviceUseInfoDate);
        deviceUseInfoDate.setOnClickListener(this);
        deviceUseInfostartDate = (TextView) deviceUseInfoDate.findViewById(R.id.startDate);
        deviceUseInfostartDate.setText(DateUtils.getFirstDay());
        deviceUseInfoEndDate = (TextView) deviceUseInfoDate.findViewById(R.id.endDate);
        deviceUseInfoEndDate.setText(DateUtils.getToday());

        showTotalEquipment();
    }

    /**
     * 显示设备和使用单位总量
     */
    private void showTotalEquipment() {
        mCombinedChart.getDescription().setEnabled(false);
        mCombinedChart.setTouchEnabled(true);
        mCombinedChart.setBackgroundColor(Color.WHITE);
        mCombinedChart.setDrawGridBackground(false);
        mCombinedChart.setDrawBarShadow(false);
        mCombinedChart.setHighlightFullBarEnabled(false);
        mCombinedChart.setOnChartGestureListener(new OnChartGestureListener() {
            @Override
            public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

            }

            @Override
            public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

            }

            @Override
            public void onChartLongPressed(MotionEvent me) {

            }

            @Override
            public void onChartDoubleTapped(MotionEvent me) {

            }

            @Override
            public void onChartSingleTapped(MotionEvent me) {
                Intent intent = new Intent(getActivity(), TotalEquipmentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("START_YEAR", deviceUseInfostartDate.getText().toString());
                bundle.putString("END_YEAR", deviceUseInfoEndDate.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

            }

            @Override
            public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

            }

            @Override
            public void onChartTranslate(MotionEvent me, float dX, float dY) {

            }
        });
//        mCombinedChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
//            @Override
//            public void onValueSelected(Entry e, Highlight h) {
//
//            }
//
//            @Override
//            public void onNothingSelected() {
//
//            }
//        });        // draw bars behind lines
        mCombinedChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.LINE, CombinedChart.DrawOrder.BAR
        });
        mCombinedChart.setNoDataText("无设备和使用单位总量数据");

        getEquipUnitTotal();

    }

    private LineData generateLineData(List<AnalysisResult> data) {

        LineData d = new LineData();

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int index = 0; index < data.size(); index++)
            entries.add(new Entry(index, Float.parseFloat(data.get(index).getValue())));

        LineDataSet set = new LineDataSet(entries, "设备数量");
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

        return d;
    }

    private BarData generateBarData(List<AnalysisResult> data) {

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        for (int index = 0; index < data.size(); index++) {
            entries.add(new BarEntry(index, Float.parseFloat(data.get(index).getValue())));
        }

        BarDataSet set1 = new BarDataSet(entries, "使用单位");
        set1.setColor(Color.rgb(60, 220, 78));
        set1.setValueTextColor(Color.rgb(60, 220, 78));
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
        float barWidth = 0.1f; // x2 dataset
        // (0.45 + 0.02) * 2 + 0.06 = 1.00 -> interval per "group"

        BarData d = new BarData(set1);
        d.setBarWidth(barWidth);

        // make this BarData object grouped
//        d.groupBars(0, groupSpace, barSpace); // start at x = 0

        return d;
    }

    /**
     * 初始化场所分析
     */
    private void initPlaceAnalysis() {
        elevatorUseInfoDate = (LinearLayout) mParentView.findViewById(R.id.elevatorUseInfoDate);
        elevatorUseInfoDate.setOnClickListener(this);
        elevatorUseInfostartDate = (TextView) elevatorUseInfoDate.findViewById(R.id.startDate);
        elevatorUseInfostartDate.setText(DateUtils.getFirstDay());
        elevatorUseInfoEndDate = (TextView) elevatorUseInfoDate.findViewById(R.id.endDate);
        elevatorUseInfoEndDate.setText(DateUtils.getToday());

        showPlaceAnalysis();
    }

    /**
     * 显示场所分析
     */
    private void showPlaceAnalysis() {
        mHorizontalBarChart.setDrawBarShadow(false);
        mHorizontalBarChart.setNoDataText("无场所分析数据");
        mHorizontalBarChart.setTouchEnabled(false);
        mHorizontalBarChart.setDrawValueAboveBar(true);

        mHorizontalBarChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mHorizontalBarChart.setMaxVisibleValueCount(120);

        // scaling can now only be done on x- and y-axis separately
        mHorizontalBarChart.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        // mChart.setDrawBarShadow(true);

        mHorizontalBarChart.setDrawGridBackground(false);
//        mHorizontalBarChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
//            @Override
//            public void onValueSelected(Entry e, Highlight h) {
//
//            }
//
//            @Override
//            public void onNothingSelected() {
//
//            }
//        });
        getPlaceAnalysis();
    }

    /**
     * 初始化突发事件分析
     */
    private void initEmergentEvents() {
        emergencyDate = (TextView) mParentView.findViewById(R.id.emergencyDate);
        emergencyDate.setOnClickListener(this);
        emergencyDate.setText(DateUtils.getThisYear());

        showEmergentEvents();
    }

    /**
     * 显示突发事件分析
     */
    private void showEmergentEvents() {
        // no description text
        mLineChart.getDescription().setEnabled(false);
        mLineChart.setNoDataText("无突发事件分析数据");
        mLineChart.setTouchEnabled(false);
        // enable touch gestures
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
//        mLineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
//            @Override
//            public void onValueSelected(Entry e, Highlight h) {
//
//            }
//
//            @Override
//            public void onNothingSelected() {
//
//            }
//        });
        getEmergentEvents();

        // add data


        mLineChart.animateX(2500);

        // get the legend (only possible after setting data)

    }

    private void setEquipmentOverviewData() {
        OkHttpUtils.get()
                .url(UrlConstant.GET_ANALYSIS_DATA)
                .addParams("type", Constant.Overview.HOME_PAGE)
                .addParams("orderNum", Constant.Boiler.CATEGORY)
                .addParams("startDate", deviceOverviewStartDate.getText().toString())
                .addParams("endDate", deviceOverviewEndDate.getText().toString())
                .build()
                .execute(new ResponseCallBack<AnalysisResult>() {
                    @Override
                    public void onResponse(List<AnalysisResult> t) {
                        super.onResponse(t);
                        if (t != null) {
                            List<AnalysisResult> data = new ArrayList<AnalysisResult>();
                            data.addAll(t);
                            setPieChartData(data);
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

    public void getPlaceAnalysis() {
        OkHttpUtils.get()
                .url(UrlConstant.GET_ANALYSIS_DATA)
                .addParams(getResources().getString(R.string.type), Constant.Overview.ELEVATOR)
                .addParams(getResources().getString(R.string.orderNum), Constant.Elevator.AREA)
                .addParams(getResources().getString(R.string.startDate), elevatorUseInfostartDate.getText().toString())
                .addParams(getResources().getString(R.string.endDate), elevatorUseInfoEndDate.getText().toString())
                .build()
                .execute(new ResponseCallBack<AnalysisResult>() {
                    @Override
                    public void onResponse(List<AnalysisResult> result) {
                        super.onResponse(result);
                        setPlaceAnalysisData(result);
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
                    }

                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        super.onError(call, e);
                    }
                });
    }

    private void setPlaceAnalysisData(final List<AnalysisResult> datas) {
//        float barWidth = 9f;
        float barWidth = 0.45f;
        float spaceForBar = 10f;

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = 0; i < datas.size(); i++) {
            yVals1.add(new BarEntry(i, Float.parseFloat(datas.get(i).getValue())));
        }

        XAxis xl = mHorizontalBarChart.getXAxis();
        xl.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return datas.get((int) value).getName();
            }
        });
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(false);
        xl.setLabelCount(10);
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
        }
    }

    public void getEmergentEvents() {
        OkHttpUtils.get()
                .url(UrlConstant.GET_EMERGENCIES)
                .addParams(getResources().getString(R.string.endYear), emergencyDate.getText().toString())
                .build()
                .execute(new ResponseCallBack<AnalysisResult>() {
                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                        showDialog();
                    }

                    @Override
                    public void onResponse(AnalysisResult analysisResult) {
                        super.onResponse(analysisResult);
                        dismissDialog();
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
                        }
                    }
                });
    }

    private void setEmergentEventsData(final List<AnalysisResult> datas) {
        int labelCount = datas.size() == 0 ? 11 : datas.size() - 1;
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
        xAxis.setLabelCount(labelCount);
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

    @Override
    public void onScroll(int scrollY) {
//        if (!isLoadPieChart && scrollY >= mLinearPieChartTop) {
//            showEquipmentOverview();
//            isLoadPieChart = true;
//        }
        if (!isLoadCombinedChart && scrollY >= (mLinearCombinedChartTop - mDetailScrollHeight)) {
            //设备和使用单位总量
            initTotalEquipment();
            isLoadCombinedChart = true;
        }
        if (!isLoadHorChart && scrollY >= (mLinearHorizontalChartTop - mDetailScrollHeight)) {
            //电梯使用场所分析
            initPlaceAnalysis();
            isLoadHorChart = true;
        }
        if (!isLoadLineChart && scrollY >= (mLinearLineChartTop - mDetailScrollHeight)) {
            initEmergentEvents();
            isLoadLineChart = true;
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        HashMap<String, String> params = new HashMap<>();
        switch (v.getId()) {
            case R.id.linear_equipment_no://特种设备数
                params.put("type", Constant.Overview.HOME_PAGE);
                params.put("orderNum", Constant.DataInfo.EQUIPMENT_NO);
                params.put("endDate", mOverviewDate.getText().toString());
                bundle.putSerializable("PARAMS", params);
                bundle.putString(Params.PARENT_TYPE, Constant.Overview.HOME_PAGE);
                intent.setClass(getActivity(), EquipmentListActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.linear_use://使用单位数
                params.put("type", Constant.Overview.HOME_PAGE);
                params.put("orderNum", Constant.DataInfo.USE_NO);
                params.put("endDate", mOverviewDate.getText().toString());
                bundle.putSerializable("PARAMS", params);
                bundle.putString(Params.PARENT_TYPE, Constant.Overview.HOME_PAGE);
                intent.setClass(getActivity(), UseUnitListActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.linear_product://生产单位数
                params.put("type", Constant.Overview.HOME_PAGE);
                params.put("orderNum", Constant.DataInfo.PRODUCTION_NO);
                params.put("endDate", mOverviewDate.getText().toString());
                bundle.putSerializable("PARAMS", params);
                bundle.putString(Params.PARENT_TYPE, Constant.Overview.HOME_PAGE);
                intent.setClass(getActivity(), ProductUnitListActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.linear_manage://管理单元数量
                params.put("type", Constant.Overview.HOME_PAGE);
                params.put("orderNum", Constant.DataInfo.MANAGE_NO);
                params.put("endDate", mOverviewDate.getText().toString());
                bundle.putSerializable("params", params);
                bundle.putString(Params.PARENT_TYPE, Constant.Overview.HOME_PAGE);
                intent.putExtra("type", 3);
                intent.putExtras(bundle);
                intent.setClass(getActivity(), RecordActivity.class);
                startActivity(intent);
                break;
            case R.id.linear_elevator://电梯维保单位
                params.put("type", Constant.Overview.HOME_PAGE);
                params.put("orderNum", Constant.DataInfo.ELEVATOR_UNIT);
                params.put("endDate", mOverviewDate.getText().toString());
                bundle.putSerializable("PARAMS", params);
                bundle.putString(Params.PARENT_TYPE, Constant.Overview.HOME_PAGE);
                intent.setClass(getActivity(), UseUnitListActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.text_check://检查单位数
                params.put("type", Constant.Overview.HOME_PAGE);
                params.put("orderNum", Constant.DataInfo.CHECK_NO);
                params.put("startDate", mIntervalStartDate.getText().toString());
                params.put("endDate", mIntervalEndDate.getText().toString());
                bundle.putSerializable("PARAMS", params);
                bundle.putString(Params.PARENT_TYPE, Constant.Overview.HOME_PAGE);
                intent.setClass(getActivity(), UseUnitListActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.linear_check_equ://检查设备数
                params.put("type", Constant.Overview.HOME_PAGE);
                params.put("orderNum", Constant.DataInfo.CHECK_EQU_NO);
                params.put("startDate", mIntervalStartDate.getText().toString());
                params.put("endDate", mIntervalEndDate.getText().toString());
                bundle.putSerializable("PARAMS", params);
                bundle.putString(Params.PARENT_TYPE, Constant.Overview.HOME_PAGE);
                intent.setClass(getActivity(), EquipmentListActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.text_accident://事故数
                params.put("type", Constant.Overview.HOME_PAGE);
                params.put("orderNum", Constant.DataInfo.ACCIDENT_NO);
                params.put("startDate", mIntervalStartDate.getText().toString());
                params.put("endDate", mIntervalEndDate.getText().toString());
                bundle.putSerializable(Params.DATA, params);
                bundle.putString(Params.PARENT_TYPE, Constant.Overview.HOME_PAGE);
                intent.setClass(getActivity(), AccidentListActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.text_cases://案件数
                params.put("type", Constant.Overview.HOME_PAGE);
                params.put("orderNum", Constant.DataInfo.CASES_NO);
                params.put("startDate", mIntervalStartDate.getText().toString());
                params.put("endDate", mIntervalEndDate.getText().toString());
                bundle.putSerializable(Params.DATA, params);
                bundle.putString(Params.PARENT_TYPE, Constant.Overview.HOME_PAGE);
                intent.setClass(getActivity(), CaseListActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.overall_data_overview_date:
                String[] arr = DateUtils.getStrSplit(mOverviewDate.getText().toString(), "-");
                if (null == mDialog || (mDialog != null && !mDialog.isShowing())) {
                    mDialog = new DatePickerDialog.Builder(getActivity()).setDayOff(false)
                            .setDate(Integer.valueOf(arr[0]), Integer.valueOf(arr[1]), Integer.valueOf(arr[2]))
                            .setNegativeButton("取消", null)
                            .setPositiveButton("确定", new DatePickerDialog.Builder.OnDatePickListener() {
                                @Override
                                public void onDatePick(DialogInterface dialog, int witch, int... args) {
                                    mOverviewDate.setText(args[0] + "-" + args[1] + "-" + args[2]);
                                    getWholeOverviewData();
                                }
                            }).create();
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                }
                break;

            case R.id.linear_section:
                arrStart = DateUtils.getStrSplit(mIntervalStartDate.getText().toString(), "-");
                arrEnd = DateUtils.getStrSplit(mIntervalEndDate.getText().toString(), "-");
                if (null == mSectionDialog || (mSectionDialog != null && !mSectionDialog.isShowing())) {
                    mSectionDialog = new SectionDatePickerDialog.Builder(getActivity())
                            .setStartDate(Integer.valueOf(arrStart[0]), Integer.valueOf(arrStart[1]), Integer.valueOf(arrStart[2]))
                            .setEndDate(Integer.valueOf(arrEnd[0]), Integer.valueOf(arrEnd[1]), Integer.valueOf(arrEnd[2]))
                            .setNegativeButton("取消", null).setPositiveButton("确定", new SectionDatePickerDialog.Builder.OnDatePickListener() {
                                @Override
                                public void onDatePick(DialogInterface dialog, int witch, int... args) {
                                    mIntervalStartDate.setText(args[0] + "-" + args[1] + "-" + args[2]);
                                    mIntervalEndDate.setText(args[3] + "-" + args[4] + "-" + args[5]);
                                    getSectionOverviewData();
                                }
                            }).create();
                    mSectionDialog.setCanceledOnTouchOutside(false);
                    mSectionDialog.show();
                }
                break;

            case R.id.deviceOverviewDate:
                arrStart = DateUtils.getStrSplit(deviceOverviewStartDate.getText().toString(), "-");
                arrEnd = DateUtils.getStrSplit(deviceOverviewEndDate.getText().toString(), "-");
                if (null == mSectionDialog || (mSectionDialog != null && !mSectionDialog.isShowing())) {
                    mSectionDialog = new SectionDatePickerDialog.Builder(getActivity())
                            .setStartDate(Integer.valueOf(arrStart[0]), Integer.valueOf(arrStart[1]), Integer.valueOf(arrStart[2]))
                            .setEndDate(Integer.valueOf(arrEnd[0]), Integer.valueOf(arrEnd[1]), Integer.valueOf(arrEnd[2]))
                            .setNegativeButton("取消", null).setPositiveButton("确定", new SectionDatePickerDialog.Builder.OnDatePickListener() {
                                @Override
                                public void onDatePick(DialogInterface dialog, int witch, int... args) {
                                    deviceOverviewStartDate.setText(args[0] + "-" + args[1] + "-" + args[2]);
                                    deviceOverviewEndDate.setText(args[3] + "-" + args[4] + "-" + args[5]);
                                    showEquipmentOverview();
                                }
                            }).create();
                    mSectionDialog.setCanceledOnTouchOutside(false);
                    mSectionDialog.show();
                }

                break;
            case R.id.deviceUseInfoDate:
                arrStart = DateUtils.getStrSplit(deviceUseInfostartDate.getText().toString(), "-");
                arrEnd = DateUtils.getStrSplit(deviceUseInfoEndDate.getText().toString(), "-");
                if (null == mSectionDialog || (mSectionDialog != null && !mSectionDialog.isShowing())) {
                    mSectionDialog = new SectionDatePickerDialog.Builder(getActivity())
                            .setStartDate(Integer.valueOf(arrStart[0]), Integer.valueOf(arrStart[1]), Integer.valueOf(arrStart[2]))
                            .setEndDate(Integer.valueOf(arrEnd[0]), Integer.valueOf(arrEnd[1]), Integer.valueOf(arrEnd[2]))
                            .setNegativeButton("取消", null).setPositiveButton("确定", new SectionDatePickerDialog.Builder.OnDatePickListener() {
                                @Override
                                public void onDatePick(DialogInterface dialog, int witch, int... args) {
                                    deviceUseInfostartDate.setText(args[0] + "-" + args[1] + "-" + args[2]);
                                    deviceUseInfoEndDate.setText(args[3] + "-" + args[4] + "-" + args[5]);
                                    showTotalEquipment();
                                }
                            }).create();
                    mSectionDialog.setCanceledOnTouchOutside(false);
                    mSectionDialog.show();
                }

                break;
            case R.id.elevatorUseInfoDate:
                arrStart = DateUtils.getStrSplit(elevatorUseInfostartDate.getText().toString(), "-");
                arrEnd = DateUtils.getStrSplit(elevatorUseInfoEndDate.getText().toString(), "-");
                if (null == mSectionDialog || (mSectionDialog != null && !mSectionDialog.isShowing())) {
                    mSectionDialog = new SectionDatePickerDialog.Builder(getActivity())
                            .setStartDate(Integer.valueOf(arrStart[0]), Integer.valueOf(arrStart[1]), Integer.valueOf(arrStart[2]))
                            .setEndDate(Integer.valueOf(arrEnd[0]), Integer.valueOf(arrEnd[1]), Integer.valueOf(arrEnd[2]))
                            .setNegativeButton("取消", null).setPositiveButton("确定", new SectionDatePickerDialog.Builder.OnDatePickListener() {
                                @Override
                                public void onDatePick(DialogInterface dialog, int witch, int... args) {
                                    elevatorUseInfostartDate.setText(args[0] + "-" + args[1] + "-" + args[2]);
                                    elevatorUseInfoEndDate.setText(args[3] + "-" + args[4] + "-" + args[5]);
                                    showPlaceAnalysis();
                                }
                            }).create();
                    mSectionDialog.setCanceledOnTouchOutside(false);
                    mSectionDialog.show();
                }
                break;
            case R.id.emergencyDate:
                String endDate = emergencyDate.getText().toString();
                if (null == mYearDialog || (mYearDialog != null && !mYearDialog.isShowing())) {
                    mYearDialog = new YearPickerDialog.Builder(getActivity())
                            .setYear(Integer.parseInt(endDate))
                            .setNegativeButton("取消", null)
                            .setPositiveButton("确定", new YearPickerDialog.Builder.OnYearPickListener() {
                                @Override
                                public void onYearPick(DialogInterface dialog, int witch, int... args) {
                                    dialog.dismiss();
                                    emergencyDate.setText(args[0] + "");
                                    showEmergentEvents();
                                }
                            })
                            .create();
                    mYearDialog.setCanceledOnTouchOutside(false);
                    mYearDialog.show();
                }
                break;
            case R.id.linear_pie:
                intent.setClass(getActivity(), EquipmentOverviewActivity.class);
                bundle.putString("START_YEAR", mIntervalStartDate.getText().toString());
                bundle.putString("END_YEAR", mIntervalEndDate.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
                break;
//            case R.id.linear_combined:
//                intent.setClass(getActivity(), TotalEquipmentActivity.class);
//                bundle.putString("START_YEAR", deviceUseInfostartDate.getText().toString());
//                bundle.putString("END_YEAR", deviceUseInfoEndDate.getText().toString());
//                intent.putExtras(bundle);
//                startActivity(intent);
//                break;
            case R.id.linear_hbar:
                intent.setClass(getActivity(), PlaceActivity.class);
                bundle.putString("START_YEAR", elevatorUseInfostartDate.getText().toString());
                bundle.putString("END_YEAR", elevatorUseInfoEndDate.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.linear_line:
                intent.setClass(getActivity(), EmergentActivity.class);
                bundle.putString("END_YEAR", emergencyDate.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
                break;

            default:
                break;


        }
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
//        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
//            @Override
//            public void onValueSelected(Entry e, Highlight h) {
//
//            }
//
//            @Override
//            public void onNothingSelected() {
//            }
//        });
        mChart.invalidate();
    }

    public void getEquipUnitTotal() {
        OkHttpUtils.get()
                .url(UrlConstant.GET_EQUIP_UNIT_TOTAL)
                .addParams("startDate", deviceUseInfostartDate.getText().toString())
                .addParams("endDate", deviceUseInfoEndDate.getText().toString())
                .build()
                .execute(new ResponseCallBack<EquipUnitTotal>() {
                    @Override
                    public void onResponse(EquipUnitTotal result) {
                        super.onResponse(result);
                        if (null != result) {
                            setEquipUnitTotal(result);
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

    public void setEquipUnitTotal(EquipUnitTotal data) {
        final List<AnalysisResult> equipData = (null == data.getEquipData()) ? new ArrayList<AnalysisResult>() : data.getEquipData();
        List<AnalysisResult> unitData = (null == data.getUnitData()) ? new ArrayList<AnalysisResult>() : data.getUnitData();
        Legend l = mCombinedChart.getLegend();
        l.setWordWrapEnabled(true);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        YAxis rightAxis = mCombinedChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis leftAxis = mCombinedChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        XAxis xAxis = mCombinedChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(equipData.size());
        xAxis.setLabelRotationAngle(-30f);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String content = equipData.get((int) value).getName();
                if (content.length() > 6) {
                    content = content.substring(0, 5) + "...";
                }
                return content;
            }
        });

        CombinedData datas = new CombinedData();
        datas.setData(generateBarData(unitData));
        datas.setData(generateLineData(equipData));

        xAxis.setAxisMaximum(datas.getXMax() + 0.25f);

        mCombinedChart.setData(datas);
        mCombinedChart.invalidate();

    }

    public float getMaxHeight(List<AnalysisResult> datas) {
        int max = 0;
        for (AnalysisResult analysisResult : datas) {
            if (max < Integer.parseInt(analysisResult.getValue()))
                max = Integer.parseInt(analysisResult.getValue());
        }
        return (float) max;
    }
}