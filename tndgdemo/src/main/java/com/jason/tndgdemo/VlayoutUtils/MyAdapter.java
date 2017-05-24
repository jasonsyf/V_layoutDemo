package com.jason.tndgdemo.VlayoutUtils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;

import java.util.ArrayList;
import java.util.List;

import static com.jason.tndgdemo.R.id.parent;

/**
 * Created by jason_syf on 2017/5/22.
 * Email: jason_sunyf@163.com
 */

public abstract class MyAdapter<T> extends DelegateAdapter.Adapter<VLayoutViewHolder> implements MyItemClickListener{
    // 使用DelegateAdapter首先就是要自定义一个它的内部类Adapter，让LayoutHelper和需要绑定的数据传进去
    // 此处的Adapter和普通RecyclerView定义的Adapter只相差了一个onCreateLayoutHelper()方法，其他的都是一样的做法.

    //    private ArrayList<HashMap<String, Object>> listItem;
    private List<T> listItem = new ArrayList<>();
    // 用于存放数据列表
    private Context context;
    private LayoutHelper layoutHelper;
    private RecyclerView.LayoutParams layoutParams;
    private int count = 0;
    private int layoutId;
    private LayoutInflater inflater;
    // 用于设置Item点击事件

    public MyAdapter(List<T>  listItem, Context context,
                     LayoutHelper layoutHelper, RecyclerView.LayoutParams layoutParams,
                     int count, int layoutId) {
        this.listItem = listItem;
        this.context = context;
        this.layoutHelper = layoutHelper;
        this.layoutParams = layoutParams;
        this.count = count;
        this.layoutId = layoutId;
        inflater = LayoutInflater.from(context);
    }
    //构造函数(传入每个的数据列表 & 展示的Item数量)
    public MyAdapter(List<T>  listItem, Context context,
                     LayoutHelper layoutHelper,
                     int count, int layoutId) {
        this(listItem,context, layoutHelper,  new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300), count,layoutId);
    }
    //构造函数(传入每个的数据列表 & 展示的Item数量)
    public MyAdapter(Context context, LayoutHelper layoutHelper, int count, List<T>  listItem) {
        this(context, layoutHelper, count, new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300), listItem);
    }

    public MyAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull RecyclerView.LayoutParams layoutParams, List<T>  listItem) {
        this.context = context;
        this.layoutHelper = layoutHelper;
        this.count = count;
        this.layoutParams = layoutParams;
        this.listItem = listItem;
        inflater = LayoutInflater.from(context);
    }

    // 把ViewHolder绑定Item的布局
    @Override
    public VLayoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VLayoutViewHolder(inflater.inflate(layoutId, parent, false),this);
    }


    // 此处的Adapter和普通RecyclerView定义的Adapter只相差了一个onCreateLayoutHelper()方法
    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }


    // 绑定Item的数据
    @Override
    public void onBindViewHolder(VLayoutViewHolder holder, int position) {
        setViewData(position, holder, listItem.get(position));
        setListeners(holder, holder.itemView, position);
    }

    // 返回Item数目
    @Override
    public int getItemCount() {
        return count;
    }

    // 设置Item的点击事件
    // 绑定MainActivity传进来的点击监听器

    protected abstract void setViewData(int position, VLayoutViewHolder holder, T item);
    protected abstract void setListeners(VLayoutViewHolder holder, View view, int position);
}
