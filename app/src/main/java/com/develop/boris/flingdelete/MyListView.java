package com.develop.boris.flingdelete;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Scroller;

import com.develop.boris.flingdelete.MyAdapter.DataHolder;

/**
 * Created by user on 2017/12/17.
 */

public class MyListView extends ListView {
//  // 滑動的最大距離(dp)
//  private final int MAX_WIDTH = 120;
//  // UI
//  private LinearLayout itemRoot;
//  private LinearLayout mPreScrollView;
//  // 滑動後最後的位置
//  private int mlastX = 0;
//  // scroller
//  private Scroller mscroller;
//  private Scroller mPreScroller;

  private MyLinearLayout mCurrentView;

  public MyListView(Context context, AttributeSet attrs) {
    super(context, attrs);
//    mscroller = new Scroller(context, new LinearInterpolator(context, null));
//    mPreScroller = new Scroller(context, new LinearInterpolator(context, null));
  }


  // 換算dp to px
  private int dpToPx(Context context, int dip) {
    return (int) (dip * context.getResources().getDisplayMetrics().density);
  }

  // 呼叫adapter的getItem(position)
  @Override
  public Object getItemAtPosition(int position) {
    return super.getItemAtPosition(position);
  }

  @Override
  public int pointToPosition(int x, int y) {
    Log.i("MyListView", "pointToPosition");
    return super.pointToPosition(x, y);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
//    int maxLength = dpToPx(getContext(), MAX_WIDTH);
//
//    int x = (int) event.getX();
//    int y = (int) event.getY();
//
//    switch (event.getAction()) {
//      case MotionEvent.ACTION_DOWN:
//        //我们想知道当前点击了哪一行
//        if (mPreScrollView != null) {
//          int preScrollX = mPreScrollView.getScrollX();
//          mPreScroller.startScroll(preScrollX, 0, 0 - preScrollX, 0, 150);
//        }
//
//
//        int position = pointToPosition(x, y);
//        if (position != INVALID_POSITION) {
//          DataHolder data = (DataHolder) getItemAtPosition(position);
//          itemRoot = data.rootView;
////          itemRoot.setBackgroundColor(R.color.colorPrimary);
//        }
//        break;
//
//      case MotionEvent.ACTION_MOVE: {
//        int scrollX = itemRoot.getScrollX();
//        int newScrollX = scrollX + mlastX - x;
//        if (newScrollX < 0) {
//          newScrollX = 0;
//        } else if (newScrollX > maxLength) {
//          newScrollX = maxLength;
//        }
//        itemRoot.scrollTo(newScrollX, 0);
//
//        break;
//      }
//      case MotionEvent.ACTION_UP: {
//        int scrollX = itemRoot.getScrollX();
//        int newScrollX = scrollX + mlastX - x;
//        if (scrollX > maxLength / 2) {
//          newScrollX = maxLength;
//        } else {
//          newScrollX = 0;
//        }
//        mPreScrollView = itemRoot;
//        mscroller.startScroll(scrollX, 0, newScrollX - scrollX, 0, 250);
//        invalidate();
////        itemRoot.scrollTo(newScrollX, 0);
//      }
//      break;
//    }
//    mlastX = x;
//    return super.onTouchEvent(event);

    int x = (int) event.getX();
    int y = (int) event.getY();

    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        int position = pointToPosition(x, y);
        if (position != INVALID_POSITION) {
          DataHolder data = (DataHolder) getItemAtPosition(position);
          mCurrentView = data.rootView;
        }
        break;
      default:
        break;
    }

    if (mCurrentView != null) {
      mCurrentView.dispatchTouchEvent(event);
    }


    return super.onTouchEvent(event);
  }

//  @Override
//  public void computeScroll() {
//
//    if (mscroller.computeScrollOffset()) { // return true when animation not finished yet
//      itemRoot.scrollTo(mscroller.getCurrX(), mscroller.getCurrY());
//    }
////    invalidate();
//
//    if (mPreScroller.computeScrollOffset()) {
//      mPreScrollView.scrollTo(mPreScroller.getCurrX(), mPreScroller.getCurrY());
//    }
//    invalidate();
//
//  }


}
