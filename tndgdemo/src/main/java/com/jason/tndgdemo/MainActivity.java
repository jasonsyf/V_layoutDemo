package com.jason.tndgdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.jason.tndgdemo.VlayoutUtils.MyAdapter;
import com.jason.tndgdemo.VlayoutUtils.MyItemClickListener;
import com.jason.tndgdemo.VlayoutUtils.VLayoutViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MyItemClickListener {

    @BindView(R.id.demo_recyclerView)
    RecyclerView mDemoRecyclerView;
    MyAdapter<Integer> Adapter_ColumnLayout ;
    List<Integer> mList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initAdapter();
        setData();
    }

    private void initAdapter() {
        mList.add(R.mipmap.xinfangshuidian);
        mList.add(R.mipmap.dianluweixiu);
        mList.add(R.mipmap.dianqishigong);
        VirtualLayoutManager virtualLayoutManager=new VirtualLayoutManager(this);
        DelegateAdapter mAdapter=new DelegateAdapter(virtualLayoutManager);
        mDemoRecyclerView.setLayoutManager(virtualLayoutManager);

        /*
         * 设置组件复用回收池
         */
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mDemoRecyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);
        /*
         设置栏格布局
         */
        ColumnLayoutHelper columnLayoutHelper = new ColumnLayoutHelper();
        // 创建对象

        // 公共属性
        columnLayoutHelper.setItemCount(3);// 设置布局里Item个数
        columnLayoutHelper.setPadding(5, 5, 5, 5);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        columnLayoutHelper.setMargin(10, 15, 10, 15);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        columnLayoutHelper.setBgColor(Color.WHITE);// 设置背景颜色
        columnLayoutHelper.setAspectRatio(5);// 设置设置布局内每行布局的宽与高的比

        // columnLayoutHelper特有属性
        columnLayoutHelper.setWeights(new float[]{(float) 33.33, (float) 33.33, (float) 33.33});// 设置该行每个Item占该行总宽度的比例
        Adapter_ColumnLayout = new MyAdapter<Integer>(mList,this, columnLayoutHelper, 3,R.layout.item_weixiu) {
            @Override
            protected void setViewData(int position, VLayoutViewHolder holder, Integer item) {
                holder.setImageRes(R.id.item_weixiu_img, mList.get(position));
            }

            @Override
            protected void setListeners(VLayoutViewHolder holder, View view, int position) {
                view.setOnClickListener(holder);
            }
        };

        Adapter_ColumnLayout.setOnItemClickListener(this);
        mAdapter.addAdapter(Adapter_ColumnLayout);

        // 设置每个Item的点击事件
        mDemoRecyclerView.setAdapter(mAdapter);

    }


    private void setData() {

    }

    @Override
    public void onItemClick(View view, int postion, RecyclerView.ViewHolder holder) {
        System.out.println("点击了第"+postion+"行");
        Toast.makeText(this, mList.get(postion)+"", Toast.LENGTH_SHORT).show();
    }
}
