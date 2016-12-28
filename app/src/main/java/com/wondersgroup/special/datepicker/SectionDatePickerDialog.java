package com.wondersgroup.special.datepicker;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wondersgroup.special.R;

import java.util.Calendar;

/**
 * Created by root on 11/22/16.
 */

public class SectionDatePickerDialog extends Dialog {
    public SectionDatePickerDialog(Context context) {
        super(context);
    }

    public SectionDatePickerDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected SectionDatePickerDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setLayout(double width, double height) {
        getWindow().setLayout((int) width, (int) height);
    }

    public void setAnimStyle(int styleId) {
        if (getWindow() != null) {
            getWindow().setWindowAnimations(styleId);
        }
    }

    public static class Builder {
        private static final int DEFAULT_STYLE = R.style.UIDialog;
        private Context mContext;
        private View mTitleDivide;
        private LinearLayout mTitlePanel;
        private LinearLayout mButtonPanel;
        private LinearLayout mDayPanel;
        private Button mNegativeButton;
        private Button mPositiveButton;
        private CharSequence mPositiveText;
        private CharSequence mNegativeText;
        private TextView mTitleTextView;
        private CharSequence mTitleText;
        private SectionDatePickerDialog.Builder.OnDatePickListener mPositiveButtonListener;
        private OnClickListener mNegativeButtonListener;
        private SectionDatePickerDialog mDialog;
        private View mDividerLineVertical;
        private NumberPicker npStartYear, npStartMonth, npStartDay, npEndYear, npEndMonth, npEndDay;
        private int mYear, mMonth, mDay, mEndYear, mEndMonth, mEndDay, mCurrentYear, mCurrentMonth, mCurrentDay;
        private Calendar calendar;
        private boolean mDayOff = false;

        public Builder(Context context) {
            this.mContext = context;
            calendar = Calendar.getInstance();
        }

        public SectionDatePickerDialog.Builder setTitle(int titleId) {
            this.mTitleText = getText(mContext, titleId);
            return this;
        }

        public SectionDatePickerDialog.Builder setTitle(CharSequence title) {
            this.mTitleText = title;
            return this;
        }

        public SectionDatePickerDialog.Builder setDayOff(boolean off) {
            this.mDayOff = off;
            return this;
        }

        public SectionDatePickerDialog.Builder setPositiveButton(int textId, SectionDatePickerDialog.Builder.OnDatePickListener listener) {
            this.mPositiveText = getText(mContext, textId);
            this.mPositiveButtonListener = listener;
            return this;
        }

        public SectionDatePickerDialog.Builder setPositiveButton(CharSequence text, SectionDatePickerDialog.Builder.OnDatePickListener listener) {
            this.mPositiveText = text;
            this.mPositiveButtonListener = listener;
            return this;
        }

        public SectionDatePickerDialog.Builder setNegativeButton(int textId, OnClickListener listener) {
            this.mNegativeText = getText(mContext, textId);
            this.mNegativeButtonListener = listener;
            return this;
        }

        public SectionDatePickerDialog.Builder setNegativeButton(CharSequence text, OnClickListener listener) {
            this.mNegativeText = text;
            this.mNegativeButtonListener = listener;
            return this;
        }

        public SectionDatePickerDialog.Builder setStartDate(int year, int month, int day) {
            this.mYear = year;
            this.mMonth = month;
            this.mDay = day;
            return this;
        }

        public SectionDatePickerDialog.Builder setEndDate(int year, int month, int day) {
            this.mEndYear = year;
            this.mEndMonth = month;
            this.mEndDay = day;
            return this;
        }

        public SectionDatePickerDialog create() {

            mDialog = new SectionDatePickerDialog(mContext, DEFAULT_STYLE);

            LayoutInflater inflater = LayoutInflater.from(mContext);
            View contentView = inflater.inflate(R.layout.layout_dialog_section_date, null);

            setupTitlePanel(contentView); // set title panel

            setupDatePanel(contentView); // set content panel
            initDate();
            initEndDate();
            setupButtonPanel(contentView); // set button panel

            mDialog.setContentView(contentView);

            setupWindowParams();

            return mDialog;
        }

        private void setupWindowParams() {
            mDialog.setAnimStyle(R.style.UIDialog);

            WindowManager wManager = mDialog.getWindow().getWindowManager();
            double width = wManager.getDefaultDisplay().getWidth() * 0.8;
            mDialog.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);
        }

        private void setupTitlePanel(View contentView) {
            mTitlePanel = (LinearLayout) contentView.findViewById(R.id.title_panel);
            mTitleTextView = (TextView) contentView.findViewById(R.id.title);
            mTitleDivide = (View) contentView.findViewById(R.id.include_title_divide);

            if (!TextUtils.isEmpty(mTitleText)) {
                mTitleTextView.setText(mTitleText);
            } else {
                mTitlePanel.setVisibility(View.GONE);
                mTitleDivide.setVisibility(View.GONE);
            }
        }

        private void setupDayPanel(View contentView) {
            mDividerLineVertical = (View) contentView.findViewById(R.id.divider_line_vertical);
            mDividerLineVertical.setVisibility(View.VISIBLE);
            mDayPanel = (LinearLayout) contentView.findViewById(R.id.linear_day);
            mDayPanel.setVisibility(View.VISIBLE);
            npStartDay = (NumberPicker) contentView.findViewById(R.id.np_day);
            npEndDay = (NumberPicker) contentView.findViewById(R.id.np_day2);
            mCurrentDay = calendar.get(Calendar.DAY_OF_MONTH);
            npStartDay.setMaxValue(31);
            npEndDay.setMaxValue(31);
            npStartDay.setMinValue(1);
            npEndDay.setMinValue(1);
            npStartMonth.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    if (picker.getValue() == mCurrentMonth) {
                        npStartDay.setMaxValue(mCurrentDay);
                    } else if (picker.getValue() == 2) {
                        if (npStartYear.getValue() % 4 == 0 && npStartYear.getValue() % 100 != 0 || npStartYear.getValue() % 400 == 0) {
                            npStartDay.setMaxValue(29);
                        } else {
                            npStartDay.setMaxValue(28);
                        }
                    } else if (picker.getValue() == 1 || picker.getValue() == 3 || picker.getValue() == 5
                            || picker.getValue() == 7 || picker.getValue() == 8 || picker.getValue() == 10
                            || picker.getValue() == 12) {
                        npStartDay.setMaxValue(31);
                    } else {
                        npStartDay.setMaxValue(30);
                    }
                    npStartDay.setMinValue(1);
                }
            });
            npEndMonth.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    if (picker.getValue() == mCurrentMonth) {
                        npEndDay.setMaxValue(mCurrentDay);
                    } else if (picker.getValue() == 2) {
                        if (npEndYear.getValue() % 4 == 0 && npEndYear.getValue() % 100 != 0 || npEndYear.getValue() % 400 == 0) {
                            npEndDay.setMaxValue(29);
                        } else {
                            npEndDay.setMaxValue(28);
                        }
                    } else if (picker.getValue() == 1 || picker.getValue() == 3 || picker.getValue() == 5
                            || picker.getValue() == 7 || picker.getValue() == 8 || picker.getValue() == 10
                            || picker.getValue() == 12) {
                        npEndDay.setMaxValue(31);
                    } else {
                        npEndDay.setMaxValue(30);
                    }
                    npEndDay.setMinValue(1);
                }
            });
        }

        private void setupDatePanel(View contentView) {
            npStartYear = (NumberPicker) contentView.findViewById(R.id.np_year);
            npEndYear = (NumberPicker) contentView.findViewById(R.id.np_year2);
            npStartMonth = (NumberPicker) contentView.findViewById(R.id.np_month);
            npEndMonth = (NumberPicker) contentView.findViewById(R.id.np_month2);
            if (!mDayOff) {
                setupDayPanel(contentView);
            }
            mCurrentYear = calendar.get(Calendar.YEAR);
            mCurrentMonth = calendar.get(Calendar.MONTH) + 1;

            npStartYear.setMaxValue(mCurrentYear);
            npStartYear.setMinValue(2010);
            npStartYear.setValue(mCurrentYear);
            npEndYear.setMaxValue(mCurrentYear);
            npEndYear.setMinValue(2010);
            npEndYear.setValue(mCurrentYear);

            npStartMonth.setMaxValue(12);
            npStartMonth.setMinValue(1);
            npEndMonth.setMaxValue(12);
            npEndMonth.setMinValue(1);

            npStartYear.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    // 选择的年份与当前年份相同时最大月份是当前月，选择的月份与当前月分相同时最大日期是当前日期
                    if (picker.getValue() == mCurrentYear) {
                        npStartMonth.setMaxValue(mCurrentMonth);
                        npStartMonth.setMinValue(1);
                    } else {
                        npStartMonth.setMaxValue(12);
                        npStartMonth.setMinValue(1);
                        if (!mDayOff) {
                            if (npStartMonth.getValue() == mCurrentMonth) {
                                npStartDay.setMaxValue(mCurrentDay);
                            } else if (npStartMonth.getValue() == 2) {
                                if (picker.getValue() % 4 == 0 && picker.getValue() % 100 != 0
                                        || picker.getValue() % 400 == 0) {
                                    npStartDay.setMaxValue(29);
                                } else {
                                    npStartDay.setMaxValue(28);
                                }
                            }
                            npStartDay.setMinValue(1);
                        }
                    }
                }
            });
            npEndYear.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    // 选择的年份与当前年份相同时最大月份是当前月，选择的月份与当前月分相同时最大日期是当前日期
                    if (picker.getValue() == mCurrentYear) {
                        npEndMonth.setMaxValue(mCurrentMonth);
                        npEndMonth.setMinValue(1);
                    } else {
                        npEndMonth.setMaxValue(12);
                        npEndMonth.setMinValue(1);
                        if (!mDayOff) {
                            if (npEndMonth.getValue() == mCurrentMonth) {
                                npEndDay.setMaxValue(mCurrentDay);
                            } else if (npEndMonth.getValue() == 2) {
                                if (picker.getValue() % 4 == 0 && picker.getValue() % 100 != 0
                                        || picker.getValue() % 400 == 0) {
                                    npEndDay.setMaxValue(29);
                                } else {
                                    npEndDay.setMaxValue(28);
                                }
                            }
                            npEndDay.setMinValue(1);
                        }
                    }
                }
            });

        }

        private void initDate() {
            if (mYear == 0 && mMonth == 0 && mDay == 0) {
                mYear = mCurrentYear;
                mMonth = mCurrentMonth;
                mDay = mCurrentDay;
            }
            if (mYear == mCurrentYear) {
                npStartYear.setValue(mYear);
                npStartMonth.setMaxValue(mCurrentMonth);
                npStartMonth.setMinValue(1);
            } else if (mYear <= npStartYear.getMaxValue() && mYear >= npStartYear.getMinValue()) {
                npStartYear.setValue(mYear);
            }
            if (mMonth == mCurrentMonth) {
                npStartMonth.setValue(mMonth);
                if (!mDayOff) {
                    npStartDay.setMaxValue(mCurrentDay);
                    npStartDay.setMinValue(1);
                }
            } else if (mMonth <= npStartMonth.getMaxValue() && mMonth >= npStartMonth.getMinValue()) {
                npStartMonth.setValue(mMonth);
                if (!mDayOff) {
                    if (mMonth == 2) {
                        if (mYear % 4 == 0 && mYear % 100 != 0 || mYear % 400 == 0) {
                            npStartDay.setMaxValue(29);
                        } else {
                            npStartDay.setMaxValue(28);
                        }
                    } else if (mMonth == 1 || mMonth == 3 || mMonth == 5 || mMonth == 7 || mMonth == 8 || mMonth == 10
                            || mMonth == 12) {
                        npStartDay.setMaxValue(31);
                    } else {
                        npStartDay.setMaxValue(30);
                    }
                    npStartDay.setMinValue(1);
                }
            }
            if (!mDayOff) {
                if (mDay <= npStartDay.getMaxValue() && mDay >= npStartDay.getMinValue()) {
                    npStartDay.setValue(mDay);
                }
            }
        }

        private void initEndDate() {
            if (mEndYear == 0 && mEndMonth == 0 && mEndDay == 0) {
                mEndYear = mCurrentYear;
                mEndMonth = mCurrentMonth;
                mEndDay = mCurrentDay;
            }
            if (mEndYear == mCurrentYear) {
                npEndYear.setValue(mEndYear);
                npEndMonth.setMaxValue(mCurrentMonth);
                npEndMonth.setMinValue(1);
            } else if (mEndYear <= npEndYear.getMaxValue() && mEndYear >= npEndYear.getMinValue()) {
                npEndYear.setValue(mEndYear);
            }
            if (mEndMonth == mCurrentMonth) {
                npEndMonth.setValue(mEndMonth);
                if (!mDayOff) {
                    npEndDay.setMaxValue(mCurrentDay);
                    npEndDay.setMinValue(1);
                }
            } else if (mEndMonth <= npEndMonth.getMaxValue() && mEndMonth >= npEndMonth.getMinValue()) {
                npEndMonth.setValue(mEndMonth);
                if (!mDayOff) {
                    if (mEndMonth == 2) {
                        if (mEndYear % 4 == 0 && mEndYear % 100 != 0 || mEndYear % 400 == 0) {
                            npEndDay.setMaxValue(29);
                        } else {
                            npEndDay.setMaxValue(28);
                        }
                    } else if (mEndMonth == 1 || mEndMonth == 3 || mEndMonth == 5 || mEndMonth == 7 || mEndMonth == 8 || mEndMonth == 10
                            || mEndMonth == 12) {
                        npEndDay.setMaxValue(31);
                    } else {
                        npEndDay.setMaxValue(30);
                    }
                    npEndDay.setMinValue(1);
                }
            }
            if (!mDayOff) {
                if (mEndDay <= npEndDay.getMaxValue() && mEndDay >= npEndDay.getMinValue()) {
                    npEndDay.setValue(mEndDay);
                }
            }
        }

        private void setupButtonPanel(View contentView) {
            mPositiveButton = (Button) contentView.findViewById(R.id.btn_positive);
            mNegativeButton = (Button) contentView.findViewById(R.id.btn_negative);
            mButtonPanel = (LinearLayout) contentView.findViewById(R.id.button_panel);

            boolean showPositive = false;
            boolean showNegative = false;

            // set the confirm button visible
            if (!TextUtils.isEmpty(mPositiveText)) {
                showPositive = true;
                mPositiveButton.setText(mPositiveText);
                mPositiveButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (mPositiveButtonListener != null) {
                            if (!mDayOff) {
                                mPositiveButtonListener.onDatePick(mDialog, DialogInterface.BUTTON_POSITIVE,
                                        npStartYear.getValue(), npStartMonth.getValue(), npStartDay.getValue(),
                                        npEndYear.getValue(), npEndMonth.getValue(), npEndDay.getValue());
                            } else {
                                mPositiveButtonListener.onDatePick(mDialog, DialogInterface.BUTTON_POSITIVE,
                                        npStartYear.getValue(), npStartMonth.getValue(), npEndYear.getValue(), npEndMonth.getValue());
                            }
                        }
                        mDialog.dismiss();
                    }
                });
            } else {
                mPositiveButton.setVisibility(View.GONE);
            }

            // set the cancel button visible
            if (!TextUtils.isEmpty(mNegativeText)) {
                showNegative = true;
                mNegativeButton.setText(mNegativeText);
                mNegativeButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (mNegativeButtonListener != null) {
                            mNegativeButtonListener.onClick(mDialog, DialogInterface.BUTTON_NEGATIVE);
                        }
                        mDialog.dismiss();
                    }
                });
            } else {
                mNegativeButton.setVisibility(View.GONE);
            }

            // set the button panel layout visible
            if (!showPositive && !showNegative) {
                mButtonPanel.setVisibility(View.GONE);
            }
        }

        public SectionDatePickerDialog show() {
            mDialog = create();
            mDialog.show();
            return mDialog;
        }

        private CharSequence getText(Context context, int resId) {
            if (context == null) {
                return null;
            }
            try {
                return context.getText(resId);
            } catch (Exception e) {
                android.util.Log.e("test", "getText exception: " + e.getMessage());
            }
            return null;
        }

        public interface OnDatePickListener {
            void onDatePick(DialogInterface dialog, int witch, int... args);
        }
    }
}
