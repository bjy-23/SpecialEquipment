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

public class SectionYearPickerDialog extends Dialog {
    public SectionYearPickerDialog(Context context) {
        super(context);
    }

    public SectionYearPickerDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected SectionYearPickerDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
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
        private Button mNegativeButton;
        private Button mPositiveButton;
        private CharSequence mPositiveText;
        private CharSequence mNegativeText;
        private TextView mTitleTextView;
        private CharSequence mTitleText;
        private SectionYearPickerDialog.Builder.OnDatePickListener mPositiveButtonListener;
        private OnClickListener mNegativeButtonListener;
        private SectionYearPickerDialog mDialog;
        private NumberPicker npStartYear, npEndYear;
        private int mYear, mEndYear, mCurrentYear,
                mMinStartYear = -1, mMinEndYear = -1, mMaxStartYear = -1, mMaxEndYear = -1;
        private Calendar calendar;

        public Builder(Context context) {
            this.mContext = context;
            calendar = Calendar.getInstance();
        }

        public SectionYearPickerDialog.Builder setTitle(int titleId) {
            this.mTitleText = getText(mContext, titleId);
            return this;
        }

        public SectionYearPickerDialog.Builder setTitle(CharSequence title) {
            this.mTitleText = title;
            return this;
        }

        public SectionYearPickerDialog.Builder setPositiveButton(int textId, SectionYearPickerDialog.Builder.OnDatePickListener listener) {
            this.mPositiveText = getText(mContext, textId);
            this.mPositiveButtonListener = listener;
            return this;
        }

        public SectionYearPickerDialog.Builder setPositiveButton(CharSequence text, SectionYearPickerDialog.Builder.OnDatePickListener listener) {
            this.mPositiveText = text;
            this.mPositiveButtonListener = listener;
            return this;
        }

        public SectionYearPickerDialog.Builder setNegativeButton(int textId, OnClickListener listener) {
            this.mNegativeText = getText(mContext, textId);
            this.mNegativeButtonListener = listener;
            return this;
        }

        public SectionYearPickerDialog.Builder setNegativeButton(CharSequence text, OnClickListener listener) {
            this.mNegativeText = text;
            this.mNegativeButtonListener = listener;
            return this;
        }

        public SectionYearPickerDialog.Builder setMinStartYear(int year) {
            this.mMinStartYear = year;
            return this;
        }

        public SectionYearPickerDialog.Builder setMinEndYear(int year) {
            this.mMinEndYear = year;
            return this;
        }

        public SectionYearPickerDialog.Builder setMaxStartYear(int year) {
            this.mMaxStartYear = year;
            return this;
        }

        public SectionYearPickerDialog.Builder setMaxEndYear(int year) {
            this.mMaxEndYear = year;
            return this;
        }

        public SectionYearPickerDialog.Builder setStartDate(int year) {
            this.mYear = year;
            return this;
        }

        public SectionYearPickerDialog.Builder setEndDate(int year) {
            this.mEndYear = year;
            return this;
        }

        public SectionYearPickerDialog create() {

            mDialog = new SectionYearPickerDialog(mContext, DEFAULT_STYLE);

            LayoutInflater inflater = LayoutInflater.from(mContext);
            View contentView = inflater.inflate(R.layout.layout_dialog_section_year, null);

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

        private void setupDatePanel(View contentView) {
            npStartYear = (NumberPicker) contentView.findViewById(R.id.np_year);
            npEndYear = (NumberPicker) contentView.findViewById(R.id.np_year2);

            mCurrentYear = calendar.get(Calendar.YEAR);


            if (mMinStartYear < 0) {
                npStartYear.setMinValue(1995);
            } else {
                npStartYear.setMinValue(mMinStartYear);
            }
            if (mMinEndYear < 0) {
                npEndYear.setMinValue(1995);
            } else {
                npEndYear.setMinValue(mMinEndYear);
            }

            if (mMaxStartYear < 0) {
                npStartYear.setMaxValue(mCurrentYear);
            } else {
                npStartYear.setMaxValue(mMaxStartYear);
            }
            if (mMinEndYear < 0) {
                npEndYear.setMaxValue(mCurrentYear);
            } else {
                npEndYear.setMaxValue(mMaxEndYear);
            }
            npStartYear.setValue(mCurrentYear);
            npEndYear.setValue(mCurrentYear);


        }

        private void initDate() {
            if (mYear == 0) {
                mYear = mCurrentYear;
            }
            if (mYear == mCurrentYear) {
                npStartYear.setValue(mYear);

            } else if (mYear <= npStartYear.getMaxValue() && mYear >= npStartYear.getMinValue()) {
                npStartYear.setValue(mYear);
            }
        }

        private void initEndDate() {
            if (mEndYear == 0) {
                mEndYear = mCurrentYear;
            }
            if (mEndYear == mCurrentYear) {
                npEndYear.setValue(mEndYear);
            } else if (mEndYear <= npEndYear.getMaxValue() && mEndYear >= npEndYear.getMinValue()) {
                npEndYear.setValue(mEndYear);
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
                            mPositiveButtonListener.onDatePick(mDialog, DialogInterface.BUTTON_POSITIVE,
                                    npStartYear.getValue(), npEndYear.getValue());
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

        public SectionYearPickerDialog show() {
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
