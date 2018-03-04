package com.develop.boris.flingdelete;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.develop.boris.flingdelete.MyAdapter.DataHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MyLinearLayout.OnScrollListener {

  private MyListView listView;
  private MyLinearLayout lastScrollView;
  private int mlastX;
  private final int MAX_WIDTH = 120;
  private MyAdapter adapter;


  // 換算dp to px
  private int dipToPx(Context context, int dip) {
    return (int) (dip * context.getResources().getDisplayMetrics().density + 0.5f);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    listView = (MyListView) findViewById(R.id.listview);

    // 準備要放入ListView的資料
    final List<DataHolder> items = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      DataHolder item = new DataHolder();
      item.title = "第" + i + "項";
      items.add(item);
    }

    adapter = new MyAdapter(this, items);
    listView.setAdapter(adapter);
    listView.setFocusable(false);
  }


  // 寫螢幕動作的行為
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {

//        int maxLength = dipToPx(this, MAX_WIDTH);
//
//        int newScrollX = 0;
//        int scrollX = itemRoot.getScrollX();
//        int x = (int) event.getX();
//
//        // 滑動的行為，只能向左滑動(linear layout向右移動)，且不能超過刪除鈕的範圍
//        if (event.getAction() == MotionEvent.ACTION_MOVE) {
//        Log.i("OnTouchEvent", "OnTouchEvent");
//            newScrollX = scrollX + mlastX - x;
//            if (newScrollX < 0) {
//                newScrollX = 0;
//            } else if (newScrollX > maxLength) {
//                newScrollX = maxLength;
//            }
//            itemRoot.scrollTo(newScrollX, 0);
//        }
//
//        if (event.getAction() == MotionEvent.ACTION_UP) {
//            if (scrollX > maxLength / 2) {
//                newScrollX = maxLength;
//            } else {
//                newScrollX = 0;
//            }
//            itemRoot.scrollTo(newScrollX, 0);
//        }
//
//        mlastX = x;
//
//        Log.i("OnTouchEvent", "Root ScrollX: " + scrollX);
//        Log.i("OnTouchEvent", "Motion EventX: " + x);
//        Log.i("OnTouchEvent", "LastX: " + mlastX);

//        return super.onTouchEvent(event);
//    }

  @Override
  public void onClick(View v) {
    if (v.getId() == R.id.tv_delete) {
      int position = listView.getPositionForView(v);
      adapter.removeItem(position);
    }


//        int id = v.getId();
//
//        switch (id) {
//            case R.id.btn_scroll_to:
//                itemRoot.scrollTo(100, 100);
//                break;
//            case R.id.btn_scroll_by:
//                itemRoot.scrollBy(-50, -50);
//                break;
//            case R.id.btn_reset:
//                itemRoot.scrollTo(0, 0);
//                break;
//            case R.id.btn_to_next:
//                Intent it = new Intent(this, MergeActivity.class);
//                startActivity(it);
//            default:
//                break;
//        }


  }

  @Override
  public void OnScroll(MyLinearLayout view) {
    if (lastScrollView != null) {
      if (lastScrollView == view) {
        return;
      } else {

        lastScrollView.smoothScrollTo(0, 0);
        lastScrollView.setBackgroundColor(0xff000000);
      }
    }
    lastScrollView = view;
  }
}
