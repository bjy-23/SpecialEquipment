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

/**
 * Created by bjy on 2016/11/28.
 */

public class NumberPickerDialog extends Dialog {
    private Context mContext;

    public NumberPickerDialog(Context context) {
        super(context);
    }

    public NumberPickerDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected NumberPickerDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
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
        private LinearLayout mButtonPanel;
        private Button mNegativeButton;
        private Button mPositiveButton;
        private static final int DEFAULT_STYLE = R.style.UIDialog;
        private Context mContext;
        private CharSequence mPositiveText;
        private CharSequence mNegativeText;
        private int mMinValue, mMaxValue, value;
        private NumberPicker npYear;
        private TextView mUnitText;
        private CharSequence unit;
        private OnNumberPickListener mPositiveButtonListener;
        private OnClickListener mNegativeButtonListener;
        private NumberPickerDialog mDialog;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setMinValue(int minValue) {
            this.mMinValue = minValue;
            return this;
        }

        public Builder setMaxValue(int maxValue) {
            this.mMaxValue = maxValue;
            return this;
        }

        public Builder setValue(int value) {
            this.value = value;
            return this;
        }

        public Builder setUnit(CharSequence unit) {
            this.unit = unit;
            return this;
        }


        public Builder setNegativeButton(CharSequence text, OnClickListener listener) {
            this.mNegativeText = text;
            this.mNegativeButtonListener = listener;
            return this;
        }

        public Builder setPositiveButton(CharSequence text, OnNumberPickListener listener) {
            this.mPositiveText = text;
            this.mPositiveButtonListener = listener;
            return this;
        }

        public NumberPickerDialog create() {
            mDialog = new NumberPickerDialog(mContext, DEFAULT_STYLE);
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View contentView = inflater.inflate(R.layout.year_picker, null);

            setupDatePanel(contentView);

            setupButtonPanel(contentView);

            mDialog.setContentView(contentView);

            setupWindowParams();

            return mDialog;
        }

        public void setupDatePanel(View contentView) {
            npYear = (NumberPicker) contentView.findViewById(R.id.np_year);
            mUnitText = (TextView) contentView.findViewById(R.id.unit);
            mUnitText.setText("");
            if (!TextUtils.isEmpty(unit)) {
                mUnitText.setText(unit);
            }
            npYear.setMaxValue(mMaxValue);
            npYear.setMinValue(mMinValue);
            npYear.setValue(value);
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

                            mPositiveButtonListener.onNumberPick(mDialog, DialogInterface.BUTTON_POSITIVE,
                                    npYear.getValue());

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

        private void setupWindowParams() {
            mDialog.setAnimStyle(R.style.UIDialog);

            WindowManager wManager = mDialog.getWindow().getWindowManager();
            double width = wManager.getDefaultDisplay().getWidth() * 0.8;
            mDialog.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);
        }

        public interface OnNumberPickListener {
            void onNumberPick(DialogInterface dialog, int witch, int... args);
        }
    }

}
