package com.jason.tndgdemo.VlayoutUtils;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by jason_syf on 2017/5/22.
 * Email: jason_sunyf@163.com
 */
public interface MyItemClickListener {
     void onItemClick(View view, int postion, RecyclerView.ViewHolder holder);
}