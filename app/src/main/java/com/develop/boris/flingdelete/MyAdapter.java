package com.develop.boris.flingdelete;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017/12/17.
 */

public class MyAdapter extends BaseAdapter {
  private Context context;
  private LayoutInflater inflater;
  private List<DataHolder> dataList;
  private View.OnClickListener deleteOnClickListener;
  private MyLinearLayout.OnScrollListener mScrollListener;

  public MyAdapter(Context context, List<DataHolder> list) {
    this.context = context;
    inflater = LayoutInflater.from(context);
    if (list != null && list.size() > 0) {
      dataList = new ArrayList<>();
      dataList.addAll(list);
    }
    deleteOnClickListener = (View.OnClickListener) context;
    mScrollListener = (MyLinearLayout.OnScrollListener) context;
  }

  @Override
  public int getCount() {
    return dataList.size();
  }

  // 使用ListView的getItemAtPosition(position)會呼叫此方法
  @Override
  public Object getItem(int position) {
    return dataList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @SuppressLint("ResourceAsColor")
  @Override
  public View getView(int position, View view, ViewGroup parent) {
    ViewHolder holder = null;
    if (view == null || view.getTag() == null) {
      holder = new ViewHolder();
      view = inflater.inflate(R.layout.item, parent, false);
      holder.title = (TextView) view.findViewById(R.id.title);
      holder.delete = (TextView) view.findViewById(R.id.tv_delete);
      holder.delete.setOnClickListener(deleteOnClickListener);
      view.setTag(holder);
    } else {
      holder = (ViewHolder) view.getTag();
    }
    DataHolder item = dataList.get(position);
    holder.title.setText(item.title);

    item.rootView = view.findViewById(R.id.lin_root);
    item.rootView.setBackgroundColor(0xff000000);
    item.rootView.scrollTo(0, 0);

    item.rootView.setOnScrollListener(mScrollListener);
    return view;
  }

  public void removeItem(int position) {
    dataList.remove(position);
    notifyDataSetChanged();
  }


  //儲存item畫面
  private class ViewHolder {
    public TextView title;
    public TextView delete;
  }

  //儲存item資料
  public static class DataHolder {
    public String title;
    public MyLinearLayout rootView;
  }
}
