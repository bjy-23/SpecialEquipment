package com.wondersgroup.special.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ListView;
import android.widget.ScrollView;

public class DetailScrollview extends ScrollView {
    private ListView ls;

    private OnScrollListener onScrollListener;
    private float touchDownY;
    private int mTouchSlop;
    /**
     * 主要是用在用户手指离开MyScrollView，MyScrollView还在继续滑动，我们用来保存Y的距离，然后做比较
     */
    private int lastScrollY;

    public DetailScrollview(Context context) {
        this(context, null);
    }

    public DetailScrollview(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DetailScrollview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mTouchSlop = ViewConfiguration.get(
                getContext()).getScaledTouchSlop();
    }

    /**
     * 设置滚动接口
     *0
     * @param onScrollListener
     */
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    /**
     * 用于用户手指离开DetailScrollview的时候获取DetailScrollview滚动的Y距离，然后回调给onScroll方法中
     */
    private Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {
            int scrollY = DetailScrollview.this.getScrollY();

            // 此时的距离和记录下的距离不相等，在隔5毫秒给handler发送消息
            if (lastScrollY != scrollY) {
                lastScrollY = scrollY;
                handler.sendMessageDelayed(handler.obtainMessage(), 5);
            }
            if (onScrollListener != null) {
                onScrollListener.onScroll(scrollY);
            }
        }

        ;

    };

//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                touchDownY = event.getY();
//                mScrolling = false;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if (Math.abs(touchDownY - event.getY()) >= ViewConfiguration.get(
//                        getContext()).getScaledTouchSlop()) {
//                    mScrolling = true;
//                } else {
//                    mScrolling = false;
////                    noListener.noOnClick();
//                }
////                mScrolling = true;
//                break;
//            case MotionEvent.ACTION_UP:
//                mScrolling = false;
////                yesListener.yesOnClick();
//                break;
//        }
//        return mScrolling;
//    }

    /**
     * 重写onTouchEvent， 当用户的手在DetailScrollview上面的时候，
     * 直接将DetailScrollview滑动的Y方向距离回调给onScroll方法中，当用户抬起手的时候，
     * DetailScrollview可能还在滑动，所以当用户抬起手我们隔5毫秒给handler发送消息，在handler处理
     * DetailScrollview滑动的距离
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchDownY = ev.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                if (Math.abs(touchDownY-ev.getY())<ViewConfiguration.get(getContext()).getScaledTouchSlop()){
                    int k = 0;
                }
                onScrollListener.onScroll((int) this.getScaleY());

                break;

            case MotionEvent.ACTION_UP:
                handler.sendMessageDelayed(handler.obtainMessage(), 5);
                break;

        }
        return super.onTouchEvent(ev);
    }

    /**
     * 滚动的回调接口
     *
     * @author xiaanming
     */
    public interface OnScrollListener {
        /**
         * 回调方法， 返回MyScrollView滑动的Y方向距离
         *
         * @param scrollY 、
         */
        public void onScroll(int scrollY);
    }
}