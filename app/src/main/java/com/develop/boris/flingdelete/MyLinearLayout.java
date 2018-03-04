package com.develop.boris.flingdelete;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.animation.LinearInterpolator;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * Created by user on 2017/12/16.
 */

public class MyLinearLayout extends LinearLayout {

  private Context mContext;
  private int mlastX = 0;
  private final int MAX_WIDTH = 120;
  private Scroller mScroller;

  private OnScrollListener mScrollListener;

  private TextView delete;

  public static interface OnScrollListener {
    public void OnScroll(MyLinearLayout view);
  }

  public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    mScroller = new Scroller(context, new LinearInterpolator(context, null));
    mContext = context;
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent event) {
    int maxLength = dpToPx(mContext, MAX_WIDTH);
    int scrollX = 0;
    int newScrollX = 0;

    // 取得事件座標
    int x = (int) event.getX();
    int y = (int) event.getY();

    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        mScrollListener.OnScroll(this);
        this.setBackgroundColor(0xff00ff00);
        delete.dispatchTouchEvent(event);
        break;
      case MotionEvent.ACTION_MOVE:
        scrollX = this.getScrollX();
        newScrollX = scrollX + mlastX - x;
        if (newScrollX < 0) {
          newScrollX = 0;
        } else if (newScrollX > maxLength) {
          newScrollX = maxLength;
        }
        this.scrollTo(newScrollX, 0);
        break;
      case MotionEvent.ACTION_UP:
        scrollX = this.getScrollX();
        newScrollX = scrollX + mlastX - x;
        if (newScrollX > maxLength / 3) {
          newScrollX = maxLength;

        } else {
          newScrollX = 0;
        }
        mScroller.startScroll(scrollX, 0, newScrollX - scrollX, 0, 150);
        invalidate();
        break;
    }
    mlastX = x;
    return true;
  }

  @Override
  public void computeScroll() {
    if (mScroller.computeScrollOffset()) {
      this.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
    }
    invalidate();
  }

  private int dpToPx(Context context, int dp) {
    return (int) (dp * context.getResources().getDisplayMetrics().density + 0.5f);
  }

  public void setOnScrollListener(OnScrollListener scrollListener) {
    mScrollListener = scrollListener;
  }

  public void smoothScrollTo(int x, int y) {
    int scrollX = getScrollX();
    int delta = x - scrollX;
    mScroller.startScroll(scrollX, 0, delta, 0, 150);
    invalidate();
  }
}
