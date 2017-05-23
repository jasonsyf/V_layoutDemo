package com.jason.tndgdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.jason.tndgdemo.VlayoutUtils.MyAdapter;
import com.jason.tndgdemo.VlayoutUtils.VLayoutViewHolder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.demo_recyclerView)
    RecyclerView mDemoRecyclerView;
    MyAdapter<Integer> Adapter_ColumnLayout, Adapter_SingleLayout;
    MyAdapter<String> Adapter_StickyLayout, Adapter_linearLayout;
    List<Integer> mBannerList = new ArrayList<>();
    List<Integer> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initAdapter();
    }

    private void initAdapter() {
        mList.add(R.mipmap.xinfangshuidian);
        mList.add(R.mipmap.dianluweixiu);
        mList.add(R.mipmap.dianqishigong);
        mBannerList.add(R.mipmap.tndg_banner1);
        mBannerList.add(R.mipmap.tndg_banner2);
        mBannerList.add(R.mipmap.tndg_banner3);
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        DelegateAdapter mAdapter = new DelegateAdapter(virtualLayoutManager);
        mDemoRecyclerView.setLayoutManager(virtualLayoutManager);
        /*
         * 设置组件复用回收池
         */
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mDemoRecyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);
//         设置通栏布局
        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
        // 公共属性
        singleLayoutHelper.setItemCount(1);// 设置布局里Item个数
//        singleLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        singleLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        singleLayoutHelper.setBgColor(Color.WHITE);// 设置背景颜色
        singleLayoutHelper.setAspectRatio(2.5f);// 设置设置布局内每行布局的宽与高的比

        Adapter_SingleLayout = new MyAdapter<Integer>(mBannerList, this, singleLayoutHelper, 1, R.layout.item_banner) {

            @Override
            protected void setViewData(int position, VLayoutViewHolder holder, Integer item) {
                ViewPager mViewPage = holder.getView(R.id.banner_viewpager);
                LinearLayout linearLayout = holder.getView(R.id.home_banner_tips);
                ScrollViewForBanner scrollViewForBanner =
                        new ScrollViewForBanner(MainActivity.this, mBannerList, linearLayout, mViewPage) {
                            @Override
                            public void imagesClick(int position) {
                                Toast.makeText(MainActivity.this, "我是banner" + position, Toast.LENGTH_SHORT).show();
                            }
                        };
                scrollViewForBanner.adder();


            }

            @Override
            protected void setListeners(VLayoutViewHolder holder, View view, int position) {
                view.setOnClickListener(holder);
            }

            @Override
            public void onItemClick(View view, int postion, RecyclerView.ViewHolder holder) {

            }
        };

        /*设置栏格布局*/
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
        Adapter_ColumnLayout = new MyAdapter<Integer>(mList, this, columnLayoutHelper, 3, R.layout.item_weixiu) {
            @Override
            protected void setViewData(int position, VLayoutViewHolder holder, Integer item) {
                holder.setImageRes(R.id.item_weixiu_img, mList.get(position));
            }

            @Override
            protected void setListeners(VLayoutViewHolder holder, View view, int position) {
                view.setOnClickListener(holder);
            }

            @Override
            public void onItemClick(View view, int postion, RecyclerView.ViewHolder holder) {
                Toast.makeText(MainActivity.this, "我是栏格布局" + postion, Toast.LENGTH_SHORT).show();
            }
        };

//         设置吸边布局
        StickyLayoutHelper stickyLayoutHelper = new StickyLayoutHelper();
        // 公共属性
        stickyLayoutHelper.setItemCount(10);// 设置布局里Item个数
        stickyLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        stickyLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        stickyLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        stickyLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
        // 特有属性
        stickyLayoutHelper.setStickyStart(true);
        // true = 组件吸在顶部
        // false = 组件吸在底部
//        stickyLayoutHelper.setOffset(100);// 设置吸边位置的偏移量
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            titles.add("附近的电工" + i);
        }

        Adapter_StickyLayout = new MyAdapter<String>(titles, this, stickyLayoutHelper, 10, R.layout.item_sticky) {
            @Override
            protected void setViewData(int position, VLayoutViewHolder holder, String item) {
                holder.setText(R.id.sticky_text, item);
            }


            @Override
            protected void setListeners(VLayoutViewHolder holder, View view, int position) {
                view.setOnClickListener(holder);
            }

            @Override
            public void onItemClick(View view, int postion, RecyclerView.ViewHolder holder) {
                Toast.makeText(MainActivity.this, "我是吸顶的布局哦", Toast.LENGTH_SHORT).show();
            }
        };
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        // 公共属性
        linearLayoutHelper.setItemCount(20);// 设置布局里Item个数
        linearLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        linearLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        // linearLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        linearLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // linearLayoutHelper特有属性
        linearLayoutHelper.setDividerHeight(10);
        // 设置间隔高度
        // 设置的间隔会与RecyclerView的addItemDecoration（）添加的间隔叠加.

        linearLayoutHelper.setMarginBottom(100);
        // 设置布局底部与下个布局的间隔

        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add(i + "");
        }
        Adapter_linearLayout = new MyAdapter<String>(strings, this, linearLayoutHelper, 20, R.layout.item_sticky) {
            @Override
            protected void setViewData(int position, VLayoutViewHolder holder, String item) {
                holder.setText(R.id.sticky_text, strings.get(position));

            }

            @Override
            protected void setListeners(VLayoutViewHolder holder, View view, int position) {
                view.setOnClickListener(holder);
            }

            @Override
            public void onItemClick(View view, int postion, RecyclerView.ViewHolder holder) {
                Toast.makeText(MainActivity.this, "我是线性的布局哦" + postion, Toast.LENGTH_SHORT).show();
            }
        };
        // 1. 设置Adapter列表（同时也是设置LayoutHelper列表）
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
        adapters.add(Adapter_SingleLayout);
        adapters.add(Adapter_ColumnLayout);
        adapters.add(Adapter_StickyLayout);
        adapters.add(Adapter_linearLayout);
        mAdapter.addAdapters(adapters);
        // 设置每个Item的点击事件
        mDemoRecyclerView.setAdapter(mAdapter);
    }
}
