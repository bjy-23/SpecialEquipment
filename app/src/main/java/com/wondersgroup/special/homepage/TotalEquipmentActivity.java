package com.wondersgroup.special.homepage;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
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
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.wondersgroup.special.R;
import com.wondersgroup.special.ResponseCallBack;
import com.wondersgroup.special.activity.BaseActivity;
import com.wondersgroup.special.activity.EquipmentListActivity;
import com.wondersgroup.special.activity.unit.UseUnitListActivity;
import com.wondersgroup.special.adapter.AnalysisAdapter;
import com.wondersgroup.special.constant.Constant;
import com.wondersgroup.special.constant.Params;
import com.wondersgroup.special.constant.UrlConstant;
import com.wondersgroup.special.datepicker.SectionDatePickerDialog;
import com.wondersgroup.special.entity.AnalysisResult;
import com.wondersgroup.special.entity.EquipUnitTotal;
import com.wondersgroup.special.utils.DateUtils;
import com.wondersgroup.special.widget.SVListView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

public class TotalEquipmentActivity extends BaseActivity {
    private String title, mChartName, mChartName2;
    private SVListView mList, mList2;
    private TextView mTextChartName, mTextChartName2;
    private CombinedChart mCombinedChart;
    private String mParentType;//大类别，表示锅炉、压力容器等
    private String mChildType;//大类别里的小类别,比如锅炉里的类别分析、使用场所分析等
    private List<AnalysisResult> data, data2;
    private LinearLayout mLinearSection;
    private SectionDatePickerDialog mSectionDialog;
    private TextView mIntervalStartDate, mIntervalEndDate;
    private AnalysisAdapter analysisAdapter, analysisAdapter2;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_total_equipment);
        mTitle.setText("设备和使用单位总量");
        mCombinedChart = (CombinedChart) findViewById(R.id.combined_chart);
        mList = (SVListView) findViewById(R.id.list);
        mList2 = (SVListView) findViewById(R.id.list2);
        mTextChartName = (TextView) findViewById(R.id.chart_name);
        mTextChartName.setText("设备数量");
        mTextChartName2 = (TextView) findViewById(R.id.chart_name2);
        mTextChartName2.setText("使用单位");
        mLinearSection = (LinearLayout) findViewById(R.id.linear_section);
        mLinearSection.setOnClickListener(this);
        mIntervalStartDate = (TextView) findViewById(R.id.interval_start_date);
        mIntervalStartDate.setText(DateUtils.getFirstDay());
        mIntervalEndDate = (TextView) findViewById(R.id.interval_end_date);
        mIntervalEndDate.setText(DateUtils.getToday());
        mList.setOnItemClickListener(new SVListView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(TotalEquipmentActivity.this, EquipmentListActivity.class);
                Bundle bundle = new Bundle();
                HashMap<String, String> params = new HashMap<>();
                params.put("type", Constant.AreaType.TYPE_EQU);
                params.put("organId", data.get(position).getUuid());
                params.put("param", data.get(position).getUuid());
                params.put("startDate", mIntervalStartDate.getText().toString());
                params.put("endDate", mIntervalEndDate.getText().toString());
                bundle.putSerializable("PARAMS", params);
                bundle.putString(Params.PARENT_TYPE, Constant.HomeDateType.TYPE_DEVICE_USEINFO);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        mList2.setOnItemClickListener(new SVListView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(TotalEquipmentActivity.this, UseUnitListActivity.class);
                Bundle bundle = new Bundle();
                HashMap<String, String> params = new HashMap<>();
                params.put("type", Constant.AreaType.TYPE_UNIT);
                params.put("organId", data2.get(position).getUuid());
                params.put("param", data.get(position).getUuid());
                params.put("startDate", mIntervalStartDate.getText().toString());
                params.put("endDate", mIntervalEndDate.getText().toString());
                bundle.putSerializable("PARAMS", params);
                bundle.putString(Params.PARENT_TYPE, Constant.HomeDateType.TYPE_DEVICE_USEINFO);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        data = new ArrayList<>();
        data2 = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        String mStartYear = bundle.getString("START_YEAR");
        String mEndYear = bundle.getString("END_YEAR");
        mIntervalStartDate.setText(mStartYear);
        mIntervalEndDate.setText(mEndYear);
        showTotalEquipment();
    }

    /**
     * 显示设备和使用单位总量
     */
    private void showTotalEquipment() {
        mCombinedChart.getDescription().setEnabled(false);
        mCombinedChart.setBackgroundColor(Color.WHITE);
        mCombinedChart.setDrawGridBackground(false);
        mCombinedChart.setDrawBarShadow(false);
        mCombinedChart.setHighlightFullBarEnabled(false);
//        mCombinedChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
//            @Override
//            public void onValueSelected(Entry e, Highlight h) {
//                if (e == null)
//                    return;
//                Intent intent = new Intent(TotalEquipmentActivity.this, TotalEquipmentActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("START_YEAR", mIntervalStartDate.getText().toString());
//                bundle.putString("END_YEAR", mIntervalEndDate.getText().toString());
//                bundle.putString("ORGAN_ID",data.get((int) e.getX()).getUuid());
//                intent.putExtras(bundle);
//                startActivity(intent);
//            }
//
//            @Override
//            public void onNothingSelected() {
//
//            }
//        });

        // draw bars behind lines
        mCombinedChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.LINE, CombinedChart.DrawOrder.BAR
        });

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

    public void getEquipUnitTotal() {
        OkHttpUtils.get()
                .url(UrlConstant.GET_EQUIP_UNIT_TOTAL)
                .addParams("startDate", mIntervalStartDate.getText().toString())
                .addParams("endDate", mIntervalEndDate.getText().toString())
                .build()
                .execute(new ResponseCallBack<EquipUnitTotal>() {
                    @Override
                    public void onResponse(EquipUnitTotal result) {
                        super.onResponse(result);
                        if (null != result) {
                            setEquipUnitTotal(result);
                            data.clear();
                            data.addAll(result.getEquipData());
                            analysisAdapter = new AnalysisAdapter(TotalEquipmentActivity.this, data);
                            mList.setAdapter(analysisAdapter);
                            data2.clear();
                            data2.addAll(result.getUnitData());
                            analysisAdapter2 = new AnalysisAdapter(TotalEquipmentActivity.this, data2);
                            mList2.setAdapter(analysisAdapter2);
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

    public void setEquipUnitTotal(final EquipUnitTotal data) {
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
        datas.setData(generateBarData(equipData));
        datas.setData(generateLineData(unitData));

        xAxis.setAxisMaximum(datas.getXMax() + 0.25f);

        mCombinedChart.setData(datas);
        mCombinedChart.invalidate();

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.linear_section:
                String[] arrStart = DateUtils.getStrSplit(mIntervalStartDate.getText().toString(), "-");
                String[] arrEnd = DateUtils.getStrSplit(mIntervalEndDate.getText().toString(), "-");
                if (null == mSectionDialog || (mSectionDialog != null && !mSectionDialog.isShowing())) {
                    mSectionDialog = new SectionDatePickerDialog.Builder(TotalEquipmentActivity.this)
                            .setStartDate(Integer.valueOf(arrStart[0]), Integer.valueOf(arrStart[1]), Integer.valueOf(arrStart[2]))
                            .setEndDate(Integer.valueOf(arrEnd[0]), Integer.valueOf(arrEnd[1]), Integer.valueOf(arrEnd[2]))
                            .setNegativeButton("取消", null).setPositiveButton("确定", new SectionDatePickerDialog.Builder.OnDatePickListener() {
                                @Override
                                public void onDatePick(DialogInterface dialog, int witch, int... args) {
                                    mIntervalStartDate.setText(args[0] + "-" + args[1] + "-" + args[2]);
                                    mIntervalEndDate.setText(args[3] + "-" + args[4] + "-" + args[5]);
                                    showTotalEquipment();
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
